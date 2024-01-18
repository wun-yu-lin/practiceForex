package com.example.cathayjob.dao;

import com.example.cathayjob.exception.MongoDbSaveErrorException;
import com.example.cathayjob.model.ForexModel;
import java.time.LocalDate;
import java.util.List;

public interface ForexDao {
    boolean postForexData(ForexModel forexModel) throws MongoDbSaveErrorException;
    List<ForexModel> getForexDataListByDateAndCurrency(LocalDate startDate, LocalDate endDate, String currency);

}
