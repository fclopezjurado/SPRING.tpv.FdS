package daos.core;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.core.TextilePrinting;
import wrappers.TextilePritingFilterWrapper;

public class TextilePrintingDaoImpl implements TextilePritingFExtended{
    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TextilePrinting> findTextilePrintingsByFilter(TextilePritingFilterWrapper textilePriting) {
        String consulta = "select t  " + "from TextilePrinting t " + " WHERE 1=1 ";
        
        consulta += this.componerConsulta(textilePriting);
        
        TypedQuery<TextilePrinting> query = this.getEntityManager().createQuery(consulta, TextilePrinting.class);
        
        this.setearCampos(query, textilePriting);
        
        List<TextilePrinting> textilePritings = query.getResultList();
        
        return textilePritings;
    }

    private String componerConsulta(TextilePritingFilterWrapper textilePriting) {
        String consulta = " ";
        if (!textilePriting.getReference().isEmpty()) {
            consulta += " And t.reference like ?1";
        }
        if (!textilePriting.getDescription().isEmpty()) {
            consulta += " And t.description like ?2";
        }
        if (!textilePriting.getType().isEmpty()) {
            consulta += " And t.type like ?3";
        }

        if ((!textilePriting.getMinRetailPrice().equals(BigDecimal.ZERO)) && (!textilePriting.getMaxRetailPrice().equals(BigDecimal.ZERO))) {
            consulta += " And t.retailPrice BETWEEN ?4 and ?5";
        } else {
            if (!textilePriting.getMinRetailPrice().equals(BigDecimal.ZERO)) {
                consulta += " And t.retailPrice > ?4 ";
            }
            if (!textilePriting.getMaxRetailPrice().equals(BigDecimal.ZERO)) {
                consulta += " And t.retailPrice < ?5";
            }
        }
        return consulta;
    }

    private void setearCampos(TypedQuery<TextilePrinting> query, TextilePritingFilterWrapper textilePriting) {
        if (!textilePriting.getReference().isEmpty()) {
            query.setParameter(1, "%" + textilePriting.getReference() + "%");
        }
        if (!textilePriting.getDescription().isEmpty()) {
            query.setParameter(2, "%" + textilePriting.getDescription() + "%");
        }
        if (!textilePriting.getType().isEmpty()) {
            query.setParameter(3, "%" + textilePriting.getType() + "%");
        }
        
        if ((!textilePriting.getMinRetailPrice().equals(BigDecimal.ZERO)) && (!textilePriting.getMaxRetailPrice().equals(BigDecimal.ZERO))) {
            query.setParameter(4, textilePriting.getMinRetailPrice());
            query.setParameter(5, textilePriting.getMaxRetailPrice());
        } else {
            if (!textilePriting.getMinRetailPrice().equals(BigDecimal.ZERO)) {
                query.setParameter(4, textilePriting.getMinRetailPrice());
            }
            if (!textilePriting.getMaxRetailPrice().equals(BigDecimal.ZERO)) {
                query.setParameter(5, textilePriting.getMaxRetailPrice());
            }
        }
    }

}
