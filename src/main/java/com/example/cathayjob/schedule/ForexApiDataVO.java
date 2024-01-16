package com.example.cathayjob.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ForexApiDataVO {
    private String date;
    private String usdntd;
    private String rmbntd;
    private String eurusd;
    private String usdjpy;
    private String gbpusd;
    private String audusd;
    private String usdhkd;
    private String usdrmb;
    private String usdzar;
    private String nzdusd;

}
