package daos.core;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
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

}
