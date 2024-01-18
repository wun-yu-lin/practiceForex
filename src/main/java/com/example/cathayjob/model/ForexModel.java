package com.example.cathayjob.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "forex")
public class ForexModel {
    String currency;
    Double value;
    LocalDate date;
}
