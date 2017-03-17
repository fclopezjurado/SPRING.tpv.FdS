package controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ShoppingDao;
import daos.core.TicketDao;
import wrappers.BestSellerProductWrapper;
import wrappers.BestSellerProductsListWrapper;
import wrappers.SaleOfProductWrapper;
import wrappers.SalesOfProductListWrapper;

@Controller
public class StatisticsController {

    private ShoppingDao shoppingDao;

    private TicketDao ticketDao;

    @Autowired
    public void setShoppingDao(ShoppingDao shoppingDao) {
        this.shoppingDao = shoppingDao;
    }

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public int countTicketsBetweenDates(Calendar inicio, Calendar fin) {
        return ticketDao.countTicketsBetweenDates(inicio, fin);
    }

    public BestSellerProductsListWrapper getBestSellerProductsByDate(Calendar inicio, Calendar fin) {
        BestSellerProductsListWrapper bestSellerListWrapper = new BestSellerProductsListWrapper();
        for (Object[] row : shoppingDao.findBestSellersBetweenDates(inicio, fin)) {
            BestSellerProductWrapper bestSellerProduct = new BestSellerProductWrapper((long) row[0], row[1].toString(),
                    Integer.parseInt(row[2].toString()));
            bestSellerListWrapper.add(bestSellerProduct);
        }
        return bestSellerListWrapper;
    }
    
    public SalesOfProductListWrapper getSalesOfProductByDate(long productId, Calendar inicio, Calendar fin) {
        SalesOfProductListWrapper salesOfProductByDate = new SalesOfProductListWrapper();
        for (Object[] row : shoppingDao.findSalesOfProductBetweenDates(productId, inicio, fin)) {
            SaleOfProductWrapper salesOfProduct = new SaleOfProductWrapper((Calendar)row[0],(long) row[1],row[2].toString(),
                    Integer.parseInt(row[3].toString()));
            salesOfProductByDate.add(salesOfProduct);
        }
        return salesOfProductByDate;
    }

}
