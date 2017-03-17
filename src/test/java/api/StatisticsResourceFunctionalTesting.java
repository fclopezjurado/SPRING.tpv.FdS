package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wrappers.BestSellerProductsListWrapper;
import wrappers.SalesOfProductListWrapper;
import wrappers.StatisticsDateWrapper;
import wrappers.StatisticsProductDateWrapper;

public class StatisticsResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;
    
    private Calendar inicio;
    
    private Calendar fin;
    
    StatisticsDateWrapper statisticsDateWrapper;

    StatisticsProductDateWrapper statisticsProductDateWrapper;

    @Before
    public void seedDataBase(){
        new RestService().deleteAll();
        new RestService().seedDatabase();
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
    public void countTicketsBetweenDates() {
        StatisticsDateWrapper statisticsDateWrapper = new StatisticsDateWrapper(inicio, fin);
        int result = new RestBuilder<Integer>(URL).path(Uris.TOTAL_SALES).body(statisticsDateWrapper).clazz(Integer.class).post().build();
        assertEquals(3, result);
    }
    
    @Test
    public void getBestSellersBetweenDates() {
        BestSellerProductsListWrapper bestSellerProductsListWrapper = new RestBuilder<BestSellerProductsListWrapper>(URL).path(Uris.BEST_SELLERS).body(statisticsDateWrapper).clazz(BestSellerProductsListWrapper.class).post().build();
        assertTrue(bestSellerProductsListWrapper.getBestSellersList().size() > 0);
    }
    
    @Test
    public void getSalesOfProductBetweenDates() {
        SalesOfProductListWrapper salesOfProductListWrapper = new RestBuilder<SalesOfProductListWrapper>(URL).path(Uris.PRODUCT_SALES).body(statisticsProductDateWrapper).clazz(SalesOfProductListWrapper.class).post().build();
        assertTrue(salesOfProductListWrapper.getSalesOfProductList().size() > 0);
    }
    
    @After
    public void after() {
        new RestService().deleteAll();
    }
}
