package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wrappers.BestSellerProductsListWrapper;
import wrappers.SalesOfProductListWrapper;
import wrappers.StatisticsDateWrapper;
import wrappers.StatisticsProductDateWrapper;

public class StatisticsResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;
        
    StatisticsDateWrapper statisticsDateWrapper;

    StatisticsProductDateWrapper statisticsProductDateWrapper;

    @Before
    public void seedDataBase(){
        new RestService().deleteAll();
        new RestService().seedDatabase();
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date inicio = new Date();
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(inicio); 
        calendar.add(Calendar.DATE, -1);       
        String s_inicio = simpleDateFormat.format(calendar.getTime()); 
        calendar.add(Calendar.DATE, +2);   
        String s_fin = simpleDateFormat.format(calendar.getTime()); 
        
        statisticsDateWrapper = new StatisticsDateWrapper(s_inicio,s_fin);
        statisticsProductDateWrapper = new StatisticsProductDateWrapper(84000001111L,s_inicio,s_fin);
    }
    
    @Test
    public void countTicketsBetweenDates() {
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
