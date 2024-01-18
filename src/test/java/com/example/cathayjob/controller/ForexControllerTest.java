package com.example.cathayjob.controller;

import com.example.cathayjob.dto.ForexPostDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class ForexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get forex by parameter, normal case1, 測試取前一天到前十天的資料")
    public void testGetForexNor1() throws Exception {
        //測試取前一天到前十天的資料
        ObjectMapper mapper = new ObjectMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDate = LocalDate.now().minusDays(10).format(formatter);
        String endDate = LocalDate.now().minusDays(1).format(formatter);
        ForexPostDto forexPostDto = new ForexPostDto();
        forexPostDto.setCurrency("usd");
        forexPostDto.setStartDate(startDate);
        forexPostDto.setEndDate(endDate);

        //建立一個RequestBuilder, 可以發起的request與相關的設定
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/forex")
                .content(mapper.writeValueAsString(forexPostDto))
                .contentType("application/json");

        //發起request, 並驗證回傳的狀態碼是否為200(ok)
        MvcResult mvcResult =  mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println("Response:" + body);
    }

    @Test
    @DisplayName("Get forex by parameter, normal case2,測試取前一天到一年的資料")
    public void testGetForexNor2() throws Exception {
        //測試取前一天到一年的資料
        ObjectMapper mapper = new ObjectMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDate = LocalDate.now().minusYears(1).format(formatter);
        String endDate = LocalDate.now().minusDays(1).format(formatter);
        ForexPostDto forexPostDto = new ForexPostDto();
        forexPostDto.setCurrency("usd");
        forexPostDto.setStartDate(startDate);
        forexPostDto.setEndDate(endDate);

        //建立一個RequestBuilder, 可以發起的request與相關的設定
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/forex")
                .content(mapper.writeValueAsString(forexPostDto))
                .contentType("application/json");

        //發起request, 並驗證回傳的狀態碼是否為200(ok)
        MvcResult mvcResult =  mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println("Response:" + body);
    }
    @Test
    @DisplayName("Get forex by parameter, normal case3, 測試取前一天的資料")
    public void testGetForexNor3() throws Exception {
        //測試取前一天的資料
        ObjectMapper mapper = new ObjectMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDate = LocalDate.now().minusDays(1).format(formatter);
        String endDate = LocalDate.now().minusDays(1).format(formatter);
        ForexPostDto forexPostDto = new ForexPostDto();
        forexPostDto.setCurrency("usd");
        forexPostDto.setStartDate(startDate);
        forexPostDto.setEndDate(endDate);

        //建立一個RequestBuilder, 可以發起的request與相關的設定
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/forex")
                .content(mapper.writeValueAsString(forexPostDto))
                .contentType("application/json");

        //發起request, 並驗證回傳的狀態碼是否為200(ok)
        MvcResult mvcResult =  mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println("Response:" + body);
    }

    @Test
    @DisplayName("Get forex by parameter, error case1, 測試startDate > endDate 的情況")
    public void testGetForexError1() throws Exception {
        //測試startDate > endDate 的情況
        ObjectMapper mapper = new ObjectMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDate = LocalDate.now().minusDays(1).format(formatter);
        String endDate = LocalDate.now().minusDays(10).format(formatter);
        ForexPostDto forexPostDto = new ForexPostDto();
        forexPostDto.setCurrency("usd");
        forexPostDto.setStartDate(startDate);
        forexPostDto.setEndDate(endDate);

        //建立一個RequestBuilder, 可以發起的request與相關的設定
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/forex")
                .content(mapper.writeValueAsString(forexPostDto))
                .contentType("application/json");

        //發起request, 並驗證回傳的狀態碼是否為400(bad request)
        MvcResult mvcResult =  mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println("Response:" + body);
    }

    @Test
    @DisplayName("Get forex by parameter, error case2, 測試搜尋時間大於一年的情況")
    public void testGetForexError2() throws Exception {
        //測試搜尋時間大於一年的情況
        ObjectMapper mapper = new ObjectMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDate = LocalDate.now().minusYears(1).minusDays(1).format(formatter);
        String endDate = LocalDate.now().minusDays(1).format(formatter);
        ForexPostDto forexPostDto = new ForexPostDto();
        forexPostDto.setCurrency("usd");
        forexPostDto.setStartDate(startDate);
        forexPostDto.setEndDate(endDate);

        //建立一個RequestBuilder, 可以發起的request與相關的設定
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/forex")
                .content(mapper.writeValueAsString(forexPostDto))
                .contentType("application/json");

        //發起request, 並驗證回傳的狀態碼是否為400(bad request)
        MvcResult mvcResult =  mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println("Response:" + body);
    }

    @Test
    @DisplayName("Get forex by parameter, error case3, 測試 endDate 為當天的情況")
    public void testGetForexError3() throws Exception {
        //測試 endDate 為當天的情況
        ObjectMapper mapper = new ObjectMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDate = LocalDate.now().minusDays(10).format(formatter);
        String endDate = LocalDate.now().format(formatter);
        ForexPostDto forexPostDto = new ForexPostDto();
        forexPostDto.setCurrency("usd");
        forexPostDto.setStartDate(startDate);
        forexPostDto.setEndDate(endDate);

        //建立一個RequestBuilder, 可以發起的request與相關的設定
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/forex")
                .content(mapper.writeValueAsString(forexPostDto))
                .contentType("application/json");

        //發起request, 並驗證回傳的狀態碼是否為400(bad request)
        MvcResult mvcResult =  mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println("Response:" + body);
    }
}