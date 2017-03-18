package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.BestSellerProductsListWrapper;
import wrappers.SalesOfProductListWrapper;
import wrappers.StatisticsDateWrapper;
import wrappers.StatisticsProductDateWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})

public class StatisticsControllerIT {

    private Calendar inicio;

    private Calendar fin;

    StatisticsDateWrapper statisticsDateWrapper;

    StatisticsProductDateWrapper statisticsProductDateWrapper;

    @Autowired
    StatisticsController statisticsController;

    @Before
    public void before() {
        inicio = Calendar.getInstance();
        fin = Calendar.getInstance();
        int diaInicio = inicio.get(Calendar.DAY_OF_MONTH);
        inicio.set(Calendar.DAY_OF_MONTH, diaInicio - 1);
        int diaFin = fin.get(Calendar.DAY_OF_MONTH);
        fin.set(Calendar.DAY_OF_MONTH, diaFin + 1);
        statisticsDateWrapper = new StatisticsDateWrapper(inicio, fin);
        statisticsProductDateWrapper = new StatisticsProductDateWrapper(84000001111L, inicio, fin);
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
