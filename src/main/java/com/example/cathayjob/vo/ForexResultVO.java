package com.example.cathayjob.vo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForexResultVO {
    StatusVO error;
    List<ForexDataVO> currency;

}
