package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.MalformedFieldxception;
import controllers.ProductController;
import wrappers.ProductFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Uris.VERSION + Uris.PRODUCTS)
public class ProductResource {

    private ProductController productController;

    @Autowired
    public void setProductController(ProductController productController) {
        this.productController = productController;
    }


    @RequestMapping(value = Uris.FILTER, method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public List<ProductsOutFilterWrapper> getProductsByFilter(@RequestBody ProductFilterWrapper product) throws MalformedFieldxception {
        this.validarCamposApi(product);
        List<ProductsOutFilterWrapper> productosSalida = this.productController.getProductsByFilter(product);
        return productosSalida;
    }

    private void validarCamposApi(ProductFilterWrapper product) throws MalformedFieldxception {
        if (product == null)
            throw new MalformedFieldxception();
        if (product.getDescription() == null)
            throw new MalformedFieldxception();
        if (product.getMaxRetailPrice() == null)
            throw new MalformedFieldxception();
        if (product.getMinRetailPrice() == null)
            throw new MalformedFieldxception();
        if (product.getReference() == null)
            throw new MalformedFieldxception();

    }
}
