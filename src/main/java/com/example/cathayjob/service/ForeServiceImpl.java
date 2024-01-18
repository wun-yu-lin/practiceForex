package com.example.cathayjob.service;

import com.example.cathayjob.dao.ForexDao;
import com.example.cathayjob.dao.ForexModel;
import com.example.cathayjob.dto.ForexPostDto;
import com.example.cathayjob.exception.MongoDbSaveErrorException;
import com.example.cathayjob.exception.QueryParameterErrorException;
import com.example.cathayjob.vo.ForexDataVO;
import com.example.cathayjob.vo.ForexResultVO;
import com.example.cathayjob.vo.StatusVO;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Component
public class ForeServiceImpl implements ForexService {

    private final ForexDao forexDao;

    public ForeServiceImpl(ForexDao forexDao) {
        this.forexDao = forexDao;
    }


    @Override
    public ForexResultVO getForexResultsByPara(ForexPostDto forexPostDto) throws QueryParameterErrorException {

        //轉換 String to LocalDate 型別
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        if(forexPostDto.getStartDate().length() != 10 || forexPostDto.getEndDate().length() != 10){
            throw new QueryParameterErrorException("日期格式不符");
        }
        LocalDate startDate = LocalDate.parse(forexPostDto.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(forexPostDto.getEndDate(), formatter);

        //檢查日期區間
        if (startDate.isAfter(endDate)) {
            throw new QueryParameterErrorException("日期區間不符");
        }
        if (startDate.isAfter(LocalDate.now().minusDays(1)) || endDate.isAfter(LocalDate.now().minusDays(1))) {
            throw new QueryParameterErrorException("日期區間不符");
        }
        if (startDate.isBefore(LocalDate.now().minusYears(1)) || endDate.isBefore(LocalDate.now().minusYears(1))) {
            throw new QueryParameterErrorException("日期區間不符");
        }


        //call dao layer to get data
        List<ForexModel> forexModelList =  forexDao.getForexDataListByDateAndCurrency(startDate, endDate, forexPostDto.getCurrency());

        //轉換資料格式為 VO 需要的格式
        ArrayList<ForexDataVO> forexModelArrayList = new ArrayList<>(forexModelList.size());
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        for (ForexModel forexModel: forexModelList) {
            ForexDataVO forexDataVO = new ForexDataVO();
            String parseDate = forexModel.getDate().format(formatter2);
            forexDataVO.setDate(parseDate);
            forexDataVO.setUsd(String.valueOf(forexModel.getValue()));
            forexModelArrayList.add(forexDataVO);
        }
        ForexResultVO forexResultVO = new ForexResultVO();
        StatusVO statusVO = new StatusVO();
        statusVO.setCode("0000");
        statusVO.setMessage("成功");
        forexResultVO.setError(statusVO);
        forexResultVO.setCurrency(forexModelArrayList);


        return forexResultVO;
    }

    @Override
    public Boolean insertForexData(ForexModel forexModel) throws MongoDbSaveErrorException {
        return forexDao.postForexData(forexModel);
    }
}
