package com.example.cathayjob.service;


import com.example.cathayjob.dao.ForexModel;
import com.example.cathayjob.dto.ForexPostDto;
import com.example.cathayjob.exception.MongoDbSaveErrorException;
import com.example.cathayjob.exception.QueryParameterErrorException;
import com.example.cathayjob.vo.ForexResultVO;

import javax.swing.text.StyledEditorKit;

public interface ForexService {
    ForexResultVO getForexResultsByPara(ForexPostDto forexPostDto) throws MongoDbSaveErrorException, QueryParameterErrorException;
    Boolean insertForexData(ForexModel forexModel) throws MongoDbSaveErrorException;

}
