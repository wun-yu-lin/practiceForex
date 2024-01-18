package com.example.cathayjob.schedule;
import com.example.cathayjob.dao.ForexDao;
import com.example.cathayjob.model.ForexModel;
import com.example.cathayjob.service.ForexService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class ForexScheduleTest {



    @Autowired
    private ForexSchedule forexSchedule;


    @MockBean
    private RestTemplate mockRestTemplate;

    @MockBean
    private ForexService mockForexService;

    @Autowired
    private ForexDao forexDao;


    @Test
    @DisplayName("Scheduled Forex API,Mock unit 測試 Forex Schedule. Normal case1")
    public void testForexSchedule() throws Exception {

        String mockApiResponse = "[{\"date\": \"20240117\", \"usdntd\": \"28.5\"}]";
        Mockito.when(mockRestTemplate.getForObject(any(String.class), any(Class.class)))
                .thenReturn(mockApiResponse);
        Mockito.when(mockRestTemplate.getForObject(any(String.class), any(Class.class)))
                .thenReturn(null).thenThrow(new RuntimeException("date is null from api"));

        // Mock the ForexService
        ForexModel mockForexModel = new ForexModel();
        mockForexModel.setDate(LocalDate.now());
        mockForexModel.setValue(28.5);
        mockForexModel.setCurrency("usd");

        Mockito.when(mockForexService.insertForexData(any(ForexModel.class)))
                .thenReturn(true);

        // Perform the scheduled task
        assertDoesNotThrow(() -> forexSchedule.getDairyForexData());


    }

    @Test
    @DisplayName("Scheduled Forex API, Integration 測試 Forex Schedule. Normal case1")
    public void testForexSchedule2() throws Exception {
//      Integration test
        //run schedule, 確認無 throw exception
        assertDoesNotThrow(() -> forexSchedule.getDairyForexData());

        //check data
        LocalDate startDate = LocalDate.now().minusDays(2);
        LocalDate endDate = LocalDate.now().minusDays(2);
        List<ForexModel> forexModelList = forexDao.getForexDataListByDateAndCurrency(startDate, endDate, "usd");

        assert(!forexModelList.isEmpty());


    }

}