package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import controllers.StatisticsController;
import wrappers.BestSellerProductsListWrapper;
import wrappers.SalesOfProductListWrapper;
import wrappers.StatisticsDateWrapper;
import wrappers.StatisticsProductDateWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class StatisticsResource {
    
    private StatisticsController statisticsController;

    @Autowired
    public void setStatisticsController(StatisticsController statisticsController) {
        this.statisticsController = statisticsController;
    }

    @RequestMapping(value = Uris.TOTAL_SALES, method = RequestMethod.POST)
    public int countTicketsBetweenDates(@RequestBody StatisticsDateWrapper statisticsDateWrapper) {
        return statisticsController.countTicketsBetweenDates(statisticsDateWrapper);
    }
    
    @RequestMapping(value = Uris.BEST_SELLERS,method = RequestMethod.POST)
    public BestSellerProductsListWrapper findBestSellersBetweenDates(@RequestBody StatisticsDateWrapper statisticsDateWrapper) {
        return statisticsController.getBestSellerProductsByDate(statisticsDateWrapper);
    }
    
    @RequestMapping(value = Uris.PRODUCT_SALES,method = RequestMethod.POST)
    public SalesOfProductListWrapper findSalesOfProductBetweenDates(@RequestBody StatisticsProductDateWrapper statisticsProductDateWrapper) {
        System.out.println(statisticsProductDateWrapper);
        return statisticsController.getSalesOfProductByDate(statisticsProductDateWrapper);
    }

}
