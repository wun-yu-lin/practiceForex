package com.example.cathayjob.controller;
import com.example.cathayjob.dao.ForexModel;
import com.example.cathayjob.dto.ForexPostDto;
import com.example.cathayjob.exception.MongoDbSaveErrorException;
import com.example.cathayjob.exception.QueryParameterErrorException;
import com.example.cathayjob.service.ForexService;
import com.example.cathayjob.vo.ForexResultVO;
import com.example.cathayjob.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forex")
public class ForexController {

    private final ForexService forexService;

    @Autowired
    public ForexController(ForexService forexService) {
        this.forexService = forexService;
    }


    @PostMapping("")
    public ResponseEntity<ForexResultVO> getForex(
        @RequestBody ForexPostDto forexPostDto
        ) throws QueryParameterErrorException, MongoDbSaveErrorException {

        ForexResultVO forexResultVO = forexService.getForexResultsByPara(forexPostDto);

        return ResponseEntity.status(HttpStatus.OK).body(forexResultVO);
    }

}
