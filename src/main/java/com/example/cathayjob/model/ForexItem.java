package com.example.cathayjob.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collation = "forex")
@Data
public class ForexItem {
    @Id
    private LocalDate date;

    private Double value;
    private String currency;

//    public ForexItem(Double value, Date date, String currency){
//        super();
//        this.date = date;
//        this.value = value;
//        this.currency = currency;
//
//    }

}
