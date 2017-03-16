package wrappers;

import java.util.ArrayList;
import java.util.List;

public class SalesOfProductListWrapper {

    private List<SaleOfProductWrapper> salesOfProductList;

    public SalesOfProductListWrapper() {
        this.salesOfProductList = new ArrayList<SaleOfProductWrapper>();
    }

    public List<SaleOfProductWrapper> getSalesOfProductList() {
        return salesOfProductList;
    }

    public void setSalesOfProductList(List<SaleOfProductWrapper> salesOfProductList) {
        this.salesOfProductList = salesOfProductList;
    }

    @Override
    public String toString() {
        return "SalesOfProductListWrapper [salesOfProductList=" + salesOfProductList + "]";
    }

}

