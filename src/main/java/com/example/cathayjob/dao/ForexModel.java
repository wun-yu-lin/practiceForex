package com.example.cathayjob.dao;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document(collection = "forex")
public class ForexModel {
    String currency;
    Double value;
    LocalDate date;
}
