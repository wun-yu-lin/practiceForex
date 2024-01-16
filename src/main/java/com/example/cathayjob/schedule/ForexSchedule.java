package com.example.cathayjob.schedule;


import com.example.cathayjob.dao.ForexModel;
import com.example.cathayjob.exception.MongoDbSaveErrorException;
import com.example.cathayjob.service.ForexService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.tree.RowMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ForexSchedule {

    @Autowired
    private ForexService forexService;



    @Scheduled(cron = "0 0 18 * * ?") // trigger at 18:00 per day.
    public void getDairyForexData() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
        String s =  restTemplate.getForObject(url, String.class);
        assert s != null;
        s = s.replaceAll("/", "");
        s = s.toLowerCase();
        ObjectMapper mapper = new ObjectMapper();
        ForexApiDataVO[] forexApiDataVoArr = mapper.readValue(s, ForexApiDataVO[].class);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        //save to db
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
