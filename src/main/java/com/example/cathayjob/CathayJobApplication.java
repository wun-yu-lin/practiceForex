package com.example.cathayjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class CathayJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(CathayJobApplication.class, args);
    }

}
