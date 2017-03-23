package daos.core;

import java.util.List;

import entities.core.Product;
import wrappers.ProductFilterWrapper;

public interface ProductExtended {
    public List<Product> findProductsByFilter(ProductFilterWrapper producto);
}
