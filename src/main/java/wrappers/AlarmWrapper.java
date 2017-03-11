package wrappers;

import java.util.List;

import entities.core.AlarmType;

public class AlarmWrapper {

    private String name;

    private AlarmType type;

    private List<String> products;
    
    private int numProducts;

    public AlarmWrapper() {}

    public AlarmWrapper(String name, AlarmType type, List<String> products, int numProducts) {
        super();
        this.name = name;
        this.type = type;
        this.products = products;
        this.numProducts = numProducts;
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

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }

    @Override
    public String toString() {
        return "AlarmWrapper [name=" + name + ", type=" + type + ", products=" + products + ", numProducts=" + numProducts + "]";
    }
    
}
