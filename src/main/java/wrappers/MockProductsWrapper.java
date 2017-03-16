package wrappers;

import java.util.List;

import entities.core.Article;

public class MockProductsWrapper {

    private List<Article> products;

    public MockProductsWrapper(List<Article> products) {
        super();
        this.products = products;
    }

    public MockProductsWrapper() {
        // TODO Auto-generated constructor stub
    }

    public List<Article> getProducts() {
        return products;
    }

    public void setProducts(List<Article> products) {
        this.products = products;
    }
    
    
    
}
