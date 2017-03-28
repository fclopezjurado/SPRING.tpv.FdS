package daos.core;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})
public class ShoppingDaoIT {
    @Autowired
    private ShoppingDao shoppingDao;

    @Test
    public void findBestSellersBetweenDates() {

        Calendar dateInicio = Calendar.getInstance();
        int diaBase = dateInicio.get(Calendar.DAY_OF_MONTH);
        dateInicio.set(Calendar.DAY_OF_MONTH, diaBase - 1);
        Calendar dateFin = Calendar.getInstance();
        dateFin.set(Calendar.DAY_OF_MONTH, diaBase + 5);

        List<Object[]> bestSellers = shoppingDao.findBestSellersBetweenDates(dateInicio, dateFin);

        assertEquals(12, bestSellers.size());
    }

    @Test
    public void findBestSellersBetweenFutureDates() {

        Calendar dateInicio = Calendar.getInstance();
        int diaBase = dateInicio.get(Calendar.DAY_OF_MONTH);
        dateInicio.set(Calendar.DAY_OF_MONTH, diaBase + 1);
        Calendar dateFin = Calendar.getInstance();
        dateFin.set(Calendar.DAY_OF_MONTH, diaBase + 5);

        List<Object[]> bestSellers = shoppingDao.findBestSellersBetweenDates(dateInicio, dateFin);

        assertEquals(0, bestSellers.size());
    }
    
    @Test
    public void findSalesOfProductBetweenDates() {

        Calendar dateInicio = Calendar.getInstance();
        int diaBase = dateInicio.get(Calendar.DAY_OF_MONTH);
        dateInicio.set(Calendar.DAY_OF_MONTH, diaBase - 2);
        Calendar dateFin = Calendar.getInstance();
        dateFin.set(Calendar.DAY_OF_MONTH, diaBase + 1);
        
        List<Object[]> productSales = shoppingDao.findSalesOfProductBetweenDates(84000001111L,dateInicio, dateFin);
        
        for(Object[] p: productSales){
            System.out.println(p[0]+" ID: "+ p[1] + "Producto: "+p[2] + "Cantidad total: "+p[3]);
        }
        
        assertTrue(productSales.size()>0);
    }
    
    @Test
    public void findSalesOfProductBetweenFutureDates() {

        Calendar dateInicio = Calendar.getInstance();
        int diaBase = dateInicio.get(Calendar.DAY_OF_MONTH);
        dateInicio.set(Calendar.DAY_OF_MONTH, diaBase + 1);
        Calendar dateFin = Calendar.getInstance();
        dateFin.set(Calendar.DAY_OF_MONTH, diaBase + 2);

        List<Object[]> productSales = shoppingDao.findSalesOfProductBetweenDates(84000001111L,dateInicio, dateFin);
        
        assertEquals(0, productSales.size());
    }

}
