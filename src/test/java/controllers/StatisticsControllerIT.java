package controllers;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.StatisticsDateParserService;
import wrappers.BestSellerProductsListWrapper;
import wrappers.SalesOfProductListWrapper;
import wrappers.StatisticsDateWrapper;
import wrappers.StatisticsProductDateWrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class, TestsMailConfig.class})

public class StatisticsControllerIT {

    StatisticsDateWrapper statisticsDateWrapper;

    StatisticsProductDateWrapper statisticsProductDateWrapper;

    StatisticsDateParserService statisticsDateParserService;

    @Autowired
    StatisticsController statisticsController;

    @Before
    public void before() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy H:m:s");
        Date inicio = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inicio);
        calendar.add(Calendar.DATE, -1);
        String s_inicio = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, +2);
        String s_fin = simpleDateFormat.format(calendar.getTime());

        statisticsDateWrapper = new StatisticsDateWrapper(s_inicio, s_fin);
        statisticsProductDateWrapper = new StatisticsProductDateWrapper(84000001111L, s_inicio, s_fin);
    }

    @Test
    public void testCountTicketsBetweenDates() {
        int ticketCounter = statisticsController.countTicketsBetweenDates(statisticsDateWrapper);
        assertEquals(3, ticketCounter);
    }

    @Test
    public void testFindSalesOfProductBetweenDates() {
        BestSellerProductsListWrapper bestSellerProductsList = statisticsController.getBestSellerProductsByDate(statisticsDateWrapper);
        assertTrue(bestSellerProductsList.getBestSellersList().size() > 0);
    }

    @Test
    public void testGetSalesOfProductByDate() {
        SalesOfProductListWrapper salesOfProductListWrapper = statisticsController.getSalesOfProductByDate(statisticsProductDateWrapper);
        assertTrue(salesOfProductListWrapper.getSalesOfProductList().size() > 0);
    }
}
