package com.example.cathayjob.dao;

import com.example.cathayjob.exception.MongoDbSaveErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


@Component
public class ForexDaoImpl implements ForexDao{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ForexDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public boolean postForexData(ForexModel forexModel) throws MongoDbSaveErrorException {
        if(forexModel == null){throw new MongoDbSaveErrorException("沒有提供數值儲存進 DB, 導致錯誤");}
        Query query = new Query(Criteria.where("date").is(forexModel.getDate()));
        ForexModel result = mongoTemplate.findOne(query, ForexModel.class);
        if (result == null) {
            mongoTemplate.insert(forexModel, "forex");
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<ForexModel> getForexDataListByDateAndCurrency(LocalDate startDate, LocalDate endDate, String currency) {
        Query query = new Query(Criteria.where("date").gte(startDate).lte(endDate));
        List<ForexModel> forexModelList = mongoTemplate.find(query, ForexModel.class);
        return forexModelList;
    }

}
