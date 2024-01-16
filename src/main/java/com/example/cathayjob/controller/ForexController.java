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

    @PostMapping("/insert")
    public ResponseEntity<StatusVO> insertForex(
            @RequestBody ForexModel forexModel
    ) throws MongoDbSaveErrorException {

        if(forexService.insertForexData(forexModel)){
            StatusVO statusVO = new StatusVO();
            statusVO.setCode("0000");
            statusVO.setMessage("success");
            return ResponseEntity.status(HttpStatus.OK).body(statusVO);
        }else{
            StatusVO statusVO = new StatusVO();
            statusVO.setCode("E001");
            statusVO.setMessage("Insert data failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusVO);
        }



    }
}
