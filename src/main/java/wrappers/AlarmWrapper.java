package wrappers;

import java.util.List;

import entities.core.AlarmType;
import entities.core.Article;

public class AlarmWrapper {

    private int id;
    
    private String name;

    private AlarmType type;
    
    private List<Article> productsList;
    
    private int numProducts;

    public AlarmWrapper() {}

    public AlarmWrapper(String name, AlarmType type, List<Article> products, int numProducts) {
        super();
        this.name = name;
        this.type = type;
        this.productsList = products;
        this.numProducts = numProducts;
    }
    
    public AlarmWrapper(int id, String name, List<Article> productsList, AlarmType type, int numProducts) {
        super();
        this.id = id;
        this.name = name;
        this.productsList = productsList;
        this.type = type;
        this.numProducts = numProducts;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlarmType getType() {
        return type;
    }

    public void setType(AlarmType type) {
        this.type = type;
    }
    
    public List<Article> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Article> productsList) {
        this.productsList = productsList;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }

    @Override
    public String toString() {
        return "AlarmWrapper [name=" + name + ", type=" + type + ", productsList=" + productsList + ", numProducts=" + numProducts + "]";
    }
    
}
