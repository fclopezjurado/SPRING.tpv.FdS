package daos.core;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.core.Embroidery;
import wrappers.EmbroideryFilterWrapper;

public class EmbroideryDaoImpl implements EmbroideryExtended {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Embroidery> findEmbroideryByFilter(EmbroideryFilterWrapper embroidery) {
        String consulta = "select e  " + "from Embroidery e " + " WHERE 1=1 ";

        consulta += this.componerConsulta(embroidery);

        TypedQuery<Embroidery> query = this.getEntityManager().createQuery(consulta, Embroidery.class);

        this.setearCampos(query, embroidery);

        List<Embroidery> embroiderys = query.getResultList();

        return embroiderys;

    }

    private String componerConsulta(EmbroideryFilterWrapper embroidery) {
        String consulta = " ";
        if (!embroidery.getReference().isEmpty()) {
            consulta += " And e.reference like ?1";
        }
        if (!embroidery.getDescription().isEmpty()) {
            consulta += " And e.description like ?2";
        }

        if ((!embroidery.getMinRetailPrice().equals(BigDecimal.ZERO)) && (!embroidery.getMaxRetailPrice().equals(BigDecimal.ZERO))) {
            consulta += " And e.retailPrice BETWEEN ?3 and ?4";
        } else {
            if (!embroidery.getMinRetailPrice().equals(BigDecimal.ZERO)) {
                consulta += " And e.retailPrice > ?3 ";
            }
            if (!embroidery.getMaxRetailPrice().equals(BigDecimal.ZERO)) {
                consulta += " And e.retailPrice < ?4";
            }
        }

        if ((embroidery.getMinStitches() > 0) && (embroidery.getMaxStitches() > 0)) {
            consulta += " And e.stitches BETWEEN ?5 and ?6";
        } else {
            if (embroidery.getMinStitches() > 0) {
                consulta += " And e.stitches > ?5 ";
            }
            if (embroidery.getMaxStitches() > 0) {
                consulta += " And e.stitches < ?6";
            }
        }

        if ((embroidery.getMinColors() > 0) && (embroidery.getMaxColors() > 0)) {
            consulta += " And e.colors BETWEEN ?7 and ?8";
        } else {
            if (embroidery.getMinColors() > 0) {
                consulta += " And e.colors > ?7 ";
            }
            if (embroidery.getMaxColors() > 0) {
                consulta += " And e.colors < ?8";
            }
        }

        if ((embroidery.getMinSquareMillimeters() > 0) && (embroidery.getMaxSquareMillimeters() > 0)) {
            consulta += " And e.squareMillimeters BETWEEN ?9 and ?10";
        } else {
            if (embroidery.getMinSquareMillimeters() > 0) {
                consulta += " And e.squareMillimeters > ?9 ";
            }
            if (embroidery.getMaxSquareMillimeters() > 0) {
                consulta += " And e.squareMillimeters < ?10";
            }
        }
        return consulta;
    }

    private void setearCampos(TypedQuery<Embroidery> query, EmbroideryFilterWrapper embroidery) {
        if (!embroidery.getReference().isEmpty()) {
            query.setParameter(1, "%" + embroidery.getReference() + "%");
        }
        if (!embroidery.getDescription().isEmpty()) {
            query.setParameter(2, "%" + embroidery.getDescription() + "%");
        }

        if ((!embroidery.getMinRetailPrice().equals(BigDecimal.ZERO)) && (!embroidery.getMaxRetailPrice().equals(BigDecimal.ZERO))) {
            query.setParameter(3, embroidery.getMinRetailPrice());
            query.setParameter(4, embroidery.getMaxRetailPrice());
        } else {
            if (!embroidery.getMinRetailPrice().equals(BigDecimal.ZERO)) {
                query.setParameter(3, embroidery.getMinRetailPrice());
            }
            if (!embroidery.getMaxRetailPrice().equals(BigDecimal.ZERO)) {
                query.setParameter(4, embroidery.getMaxRetailPrice());
            }
        }

        if ((embroidery.getMinStitches() > 0) && (embroidery.getMaxStitches() > 0)) {
            query.setParameter(5, embroidery.getMinStitches());
            query.setParameter(6, embroidery.getMaxStitches());
        } else {
            if (embroidery.getMinStitches() > 0) {
                query.setParameter(5, embroidery.getMinStitches());
            }
            if (embroidery.getMaxStitches() > 0) {
                query.setParameter(6, embroidery.getMaxStitches());
            }
        }

        if ((embroidery.getMinColors() > 0) && (embroidery.getMaxColors() > 0)) {
            query.setParameter(7, embroidery.getMinColors());
            query.setParameter(8, embroidery.getMaxColors());
        } else {
            if (embroidery.getMinColors() > 0) {
                query.setParameter(7, embroidery.getMinColors());
            }
            if (embroidery.getMaxColors() > 0) {
                query.setParameter(8, embroidery.getMaxColors());
            }
        }

        if ((embroidery.getMinSquareMillimeters() > 0) && (embroidery.getMaxSquareMillimeters() > 0)) {
            query.setParameter(9, embroidery.getMinSquareMillimeters());
            query.setParameter(10, embroidery.getMaxSquareMillimeters());
        } else {
            if (embroidery.getMinSquareMillimeters() > 0) {
                query.setParameter(9, embroidery.getMinSquareMillimeters());
            }
            if (embroidery.getMaxSquareMillimeters() > 0) {
                query.setParameter(10, embroidery.getMaxSquareMillimeters());
            }
        }
    }

}
