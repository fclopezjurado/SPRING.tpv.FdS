package daos.core;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;

import entities.core.Product;
import wrappers.ProductFilterWrapper;

public class ProductDaoImpl implements ProductExtended {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findProductsByFilter(ProductFilterWrapper producto) {
        String consulta = "select p  " + "from Product p " + " WHERE 1=1 ";
        
        consulta += this.componerConsulta(producto);
        
        TypedQuery<Product> query = this.getEntityManager().createQuery(consulta, Product.class);
        
        this.setearCampos(query, producto);
        
        List<Product> productos = query.getResultList();
        
        return productos;
    }

    private String componerConsulta(ProductFilterWrapper producto) {
        String consulta = " ";
        if (!"".equals(producto.getReference())) {
            consulta += " And p.reference like ?1";
        }
        if (!producto.getDescription().isEmpty()) {
            consulta += " And p.description like ?2";
        }

        if ((!producto.getMinRetailPrice().equals(BigDecimal.ZERO)) && (!producto.getMaxRetailPrice().equals(BigDecimal.ZERO))) {
            consulta += " And p.retailPrice BETWEEN ?3 and ?4";
        } else {
            if (!producto.getMinRetailPrice().equals(BigDecimal.ZERO)) {
                consulta += " And p.retailPrice > ?3 ";
            }
            if (!producto.getMaxRetailPrice().equals(BigDecimal.ZERO)) {
                consulta += " And p.retailPrice < ?4";
            }
        }
        return consulta;
    }

    private void setearCampos(TypedQuery<Product> query, ProductFilterWrapper producto) {
        if (!producto.getReference().isEmpty()) {
            query.setParameter(1, "%" + producto.getReference() + "%");
        }
        if (!producto.getDescription().isEmpty()) {
            query.setParameter(2, "%" + producto.getDescription() + "%");
        }

        if ((!producto.getMinRetailPrice().equals(BigDecimal.ZERO)) && (!producto.getMaxRetailPrice().equals(BigDecimal.ZERO))) {
            query.setParameter(3, producto.getMinRetailPrice());
            query.setParameter(4, producto.getMaxRetailPrice());
        } else {
            if (!producto.getMinRetailPrice().equals(BigDecimal.ZERO)) {
                query.setParameter(3, producto.getMinRetailPrice());
            }
            if (!producto.getMaxRetailPrice().equals(BigDecimal.ZERO)) {
                query.setParameter(4, producto.getMaxRetailPrice());
            }
        }
    }

}
