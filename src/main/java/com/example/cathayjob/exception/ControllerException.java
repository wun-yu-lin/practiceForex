package com.example.cathayjob.exception;


import com.example.cathayjob.vo.StatusVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(MongoDbSaveErrorException.class)
    public ResponseEntity<StatusVO> prepareResponseForMongoDbSaveErrorException(MongoDbSaveErrorException exception){
        StatusVO statusVO = new StatusVO();
        statusVO.setCode("E001");
        statusVO.setMessage(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(statusVO);

    }

    @ExceptionHandler(QueryParameterErrorException.class)
    public ResponseEntity<StatusVO> prepareResponseForQueryParameterErrorException(QueryParameterErrorException exception){
        StatusVO statusVO = new StatusVO();
        statusVO.setCode("E001");
        statusVO.setMessage(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(statusVO);

    }


}
