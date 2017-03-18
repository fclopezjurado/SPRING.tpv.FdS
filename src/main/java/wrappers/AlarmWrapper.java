package wrappers;

import java.util.List;

import entities.core.AlarmType;

public class AlarmWrapper {

    private int id;
    
    private String name;

    private AlarmType type;
    
    private List<ArticleWrapper> productsList;
    
    private int numProducts;

    public AlarmWrapper() {}

    public AlarmWrapper(String name, AlarmType type, List<ArticleWrapper> products, int numProducts) {
        super();
        this.name = name;
        this.type = type;
        this.productsList = products;
        this.numProducts = numProducts;
    }
    
    public AlarmWrapper(int id, String name, List<ArticleWrapper> productsList, AlarmType type, int numProducts) {
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
    
    public List<ArticleWrapper> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ArticleWrapper> productsList) {
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
