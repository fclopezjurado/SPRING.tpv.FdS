package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ShoppingDao;
import daos.core.TicketDao;
import services.StatisticsDateParserService;
import wrappers.BestSellerProductWrapper;
import wrappers.BestSellerProductsListWrapper;
import wrappers.SaleOfProductWrapper;
import wrappers.SalesOfProductListWrapper;
import wrappers.StatisticsDateWrapper;
import wrappers.StatisticsProductDateWrapper;

@Controller
public class StatisticsController {

    private ShoppingDao shoppingDao;

    private TicketDao ticketDao;

    private StatisticsDateParserService statisticsDateParserService = new StatisticsDateParserService();

    @Autowired
    public void setShoppingDao(ShoppingDao shoppingDao) {
        this.shoppingDao = shoppingDao;
    }

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public int countTicketsBetweenDates(StatisticsDateWrapper statisticsDateWrapper) {

        StatisticsDateParserService statisticsDateParserService = new StatisticsDateParserService();

        Calendar inicio = statisticsDateParserService.getCalendarDateFromString(statisticsDateWrapper.getInicio());
        Calendar fin = statisticsDateParserService.getCalendarDateFromString(statisticsDateWrapper.getFin());

        return ticketDao.countTicketsBetweenDates(inicio, fin);
    }

    public BestSellerProductsListWrapper getBestSellerProductsByDate(StatisticsDateWrapper statisticsDateWrapper) {

        Calendar inicio = statisticsDateParserService.getCalendarDateFromString(statisticsDateWrapper.getInicio());
        Calendar fin = statisticsDateParserService.getCalendarDateFromString(statisticsDateWrapper.getFin());

        BestSellerProductsListWrapper bestSellerListWrapper = new BestSellerProductsListWrapper();
        for (Object[] row : shoppingDao.findBestSellersBetweenDates(inicio, fin)) {
            BestSellerProductWrapper bestSellerProduct = new BestSellerProductWrapper((long) row[0], row[1].toString(),
                    Integer.parseInt(row[2].toString()));
            bestSellerListWrapper.add(bestSellerProduct);
        }
        return bestSellerListWrapper;
    }

    public SalesOfProductListWrapper getSalesOfProductByDate(StatisticsProductDateWrapper statisticsProductDateWrapper) {
        SalesOfProductListWrapper salesOfProductByDate = new SalesOfProductListWrapper();

        Calendar inicio = statisticsDateParserService.getCalendarDateFromString(statisticsProductDateWrapper.getInicio());
        Calendar fin = statisticsDateParserService.getCalendarDateFromString(statisticsProductDateWrapper.getFin());

        for (Object[] row : shoppingDao.findSalesOfProductBetweenDates(statisticsProductDateWrapper.getProductId(), inicio, fin)) {
            SaleOfProductWrapper salesOfProduct = new SaleOfProductWrapper((Calendar) row[0], (long) row[1], row[2].toString(),
                    Integer.parseInt(row[3].toString()));
            salesOfProductByDate.add(salesOfProduct);
        }
        return salesOfProductByDate;
    }

}
