package wrappers;

import java.util.ArrayList;
import java.util.List;

public class BestSellerProductsListWrapper {
    private List<BestSellerProductWrapper> bestSellersList;

    public BestSellerProductsListWrapper() {
        this.bestSellersList = new ArrayList<BestSellerProductWrapper>();
    }

    public List<BestSellerProductWrapper> getBestSellersList() {
        return bestSellersList;
    }

    public void setBestSellersList(List<BestSellerProductWrapper> bestSellersList) {
        this.bestSellersList = bestSellersList;
    }

    @Override
    public String toString() {
        return "BestSellerProductsListWrapper [bestSellersList=" + bestSellersList + "]";
    }

}
