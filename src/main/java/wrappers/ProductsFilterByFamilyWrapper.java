package wrappers;

import entities.core.Product;

public class ProductsFilterByFamilyWrapper extends ProductWrapper {

    public ProductsFilterByFamilyWrapper() {
        // TODO Auto-generated constructor stub
    }

    public ProductsFilterByFamilyWrapper(Product product) {
        super(product.getId(), product.getReference(), product.getDescription(), product.getRetailPrice());
    }
}
