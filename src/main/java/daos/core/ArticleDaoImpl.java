package daos.core;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.core.Article;
import wrappers.ArticleFilterWrapper;


public class ArticleDaoImpl implements ArticleExtended {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Article> findArticlesByFilter(ArticleFilterWrapper article) {
        String consulta = "select a  " + "from Article a " + " WHERE 1=1 ";

        consulta += this.componerConsulta(article);

        TypedQuery<Article> query = this.getEntityManager().createQuery(consulta, Article.class);

        this.setearCampos(query, article);

        List<Article> articles = query.getResultList();
        return articles;
    } 

    private String componerConsulta(ArticleFilterWrapper article) {
        String consulta = " ";

        if (!article.getReference().isEmpty()) {
            consulta += " And a.reference like ?1";
        }
        if (!article.getDescription().isEmpty()) {
            consulta += " And a.description like ?2";
        }

        if ((!article.getMinRetailPrice().equals(BigDecimal.ZERO)) && (!article.getMaxRetailPrice().equals(BigDecimal.ZERO))) {
            consulta += " And a.retailPrice BETWEEN ?3 and ?4";
        } else {
            if (!article.getMinRetailPrice().equals(BigDecimal.ZERO)) {
                consulta += " And a.retailPrice > ?3 ";
            }
            if (!article.getMaxRetailPrice().equals(BigDecimal.ZERO)) {
                consulta += " And a.retailPrice < ?4";
            }
        }

        if ((!article.getMinWholesalePrice().equals(BigDecimal.ZERO)) && (!article.getMaxWholesalePrice().equals(BigDecimal.ZERO))) {
            consulta += " And a.wholesalePrice BETWEEN ?5 and ?6";
        } else {
            if (!article.getMinWholesalePrice().equals(BigDecimal.ZERO)) {
                consulta += " And a.wholesalePrice > ?5 ";
            }
            if (!article.getMaxWholesalePrice().equals(BigDecimal.ZERO)) {
                consulta += " And a.wholesalePrice < ?6";
            }
        }
        if (article.getStock()>=0){
            consulta += " And a.stock = ?7";
        }

        return consulta;
    }

    private void setearCampos(TypedQuery<Article> query, ArticleFilterWrapper article) {
        if (!article.getReference().isEmpty()) {
            query.setParameter(1, "%" + article.getReference() + "%");
        }
        if (!article.getDescription().isEmpty()) {
            query.setParameter(2, "%" + article.getDescription() + "%");
        }

        if ((!article.getMinRetailPrice().equals(BigDecimal.ZERO)) && (!article.getMaxRetailPrice().equals(BigDecimal.ZERO))) {
            query.setParameter(3, article.getMinRetailPrice());
            query.setParameter(4, article.getMaxRetailPrice());
        } else {
            if (!article.getMinRetailPrice().equals(BigDecimal.ZERO)) {
                query.setParameter(3, article.getMinRetailPrice());
            }
            if (!article.getMaxRetailPrice().equals(BigDecimal.ZERO)) {
                query.setParameter(4, article.getMaxRetailPrice());
            }
        }
        if ((!article.getMinWholesalePrice().equals(BigDecimal.ZERO)) && (!article.getMaxWholesalePrice().equals(BigDecimal.ZERO))) {
            query.setParameter(5, article.getMinWholesalePrice());
            query.setParameter(6, article.getMaxWholesalePrice());
        } else {
            if (!article.getMinWholesalePrice().equals(BigDecimal.ZERO)) {
                query.setParameter(5, article.getMinWholesalePrice());
            }
            if (!article.getMaxWholesalePrice().equals(BigDecimal.ZERO)) {
                query.setParameter(6, article.getMaxWholesalePrice());
            }
        }
        if (article.getStock()>=0){
            query.setParameter(7, article.getStock());
            
        }

    }

}
