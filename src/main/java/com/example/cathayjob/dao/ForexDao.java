package com.example.cathayjob.dao;

import com.example.cathayjob.dto.ForexPostDto;
import com.example.cathayjob.exception.MongoDbSaveErrorException;
import com.example.cathayjob.vo.ForexResultVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ForexDao {
    boolean postForexData(ForexModel forexModel) throws MongoDbSaveErrorException;
    List<ForexModel> getForexDataListByDateAndCurrency(LocalDate startDate, LocalDate endDate, String currency);

}
