package wrappers;

import entities.core.Product;

public class ProductsOutFilterWrapper {

    private long id;

    private String reference;

    private String description;

    public ProductsOutFilterWrapper() {

    }

    public ProductsOutFilterWrapper(long id, String reference, String description) {
        super();
        this.id = id;
        this.reference = reference;
        this.description = description;
    }

    public ProductsOutFilterWrapper(Product producto) {
        super();
        this.id = producto.getId();
        this.reference = producto.getReference();
        this.description = producto.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
