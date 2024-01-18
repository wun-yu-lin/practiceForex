package com.example.cathayjob.schedule;


import com.example.cathayjob.dao.ForexModel;
import com.example.cathayjob.exception.MongoDbSaveErrorException;
import com.example.cathayjob.service.ForexService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.tree.RowMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ForexSchedule {

    @Autowired
    private ForexService forexService;



    @Scheduled(cron = "0 0 18 * * ?", zone="Asia/Taipei") // trigger at 18:00 per day.
    public void getDairyForexData() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        //get data from api
        String url = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
        String s =  restTemplate.getForObject(url, String.class);
        assert s != null;
        s = s.replaceAll("/", "");
        s = s.toLowerCase();

        //convert string to object
        ObjectMapper mapper = new ObjectMapper();
        ForexApiDataVO[] forexApiDataVoArr = mapper.readValue(s, ForexApiDataVO[].class);


        //save to db
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        for (ForexApiDataVO forexApiDataVO : forexApiDataVoArr) {
            ForexModel forexModel = new ForexModel();
            forexModel.setDate(LocalDate.parse(forexApiDataVO.getDate(), formatter));
            forexModel.setValue(Double.parseDouble(forexApiDataVO.getUsdntd()));
            forexModel.setCurrency("usd");
            try {
                forexService.insertForexData(forexModel);
            } catch (MongoDbSaveErrorException e) {
                throw new RuntimeException(e);
            }
        }



    }
}
