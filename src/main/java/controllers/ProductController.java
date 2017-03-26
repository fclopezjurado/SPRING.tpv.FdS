package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ProductDao;
import entities.core.Product;
import wrappers.ProductFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

@Controller
public class ProductController {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<ProductsOutFilterWrapper> getProductsByFilter(ProductFilterWrapper productFilter) {
        List<Product> productosDeBusqueda = this.productDao.findProductsByFilter(productFilter);
        List<ProductsOutFilterWrapper> productosSalida = new ArrayList<ProductsOutFilterWrapper>();
        for (Product producto : productosDeBusqueda) {
            ProductsOutFilterWrapper productoOutWrapper = new ProductsOutFilterWrapper(producto);
            productosSalida.add(productoOutWrapper);
        }
        return productosSalida;
    }

}
