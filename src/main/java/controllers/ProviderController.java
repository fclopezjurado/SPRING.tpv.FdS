package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.ProviderWithArticlesException;
import daos.core.ArticleDao;
import daos.core.ProviderDao;
import entities.core.Article;
import entities.core.Provider;
import wrappers.ProviderWrapper;

@Controller
public class ProviderController {

    private ProviderDao providerDao;
    private ArticleDao articleDao;
    
    @Autowired
    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}



	public ProviderWrapper registration(ProviderWrapper providerWrapper) {
        if (null == providerDao.findByMobile(providerWrapper.getMobile())) {
            Provider provider = new Provider(providerWrapper.getCompany(), providerWrapper.getAddress(), providerWrapper.getMobile(),
                    providerWrapper.getMobile(), providerWrapper.getPaymentConditions(), providerWrapper.getNote());
            return new ProviderWrapper(providerDao.save(provider));
        } else {
            return null;
        }
    }

    public List<ProviderWrapper> getAll() {
        List<ProviderWrapper> providerWrapperList = new ArrayList<ProviderWrapper>();
        for(Provider provider : providerDao.findAll()){
            providerWrapperList.add(new ProviderWrapper(provider));
        }
         return  providerWrapperList; 
    }

    public ProviderWrapper editProvider(ProviderWrapper providerWrapper) {
        Provider provider = providerDao.findById(providerWrapper.getId());
        if (provider != null) {
            provider.setAddress(providerWrapper.getAddress());
            provider.setCompany(providerWrapper.getCompany());
            provider.setMobile(providerWrapper.getMobile());
            provider.setNote(providerWrapper.getNote());
            provider.setPaymentConditions(providerWrapper.getPaymentConditions());
            provider.setPhone(providerWrapper.getMobile());
            return new ProviderWrapper(providerDao.save(provider));
        } else
            return null;
    }

    public void delete(String id) throws ProviderWithArticlesException {
        Integer providerId = Integer.parseInt(id);
        int provId = Integer.parseInt(id);
        List<Article> articlesWithProvider = articleDao.findByProvider(providerDao.findById(provId));
        if(articlesWithProvider.size()>0){
        	throw new ProviderWithArticlesException(articlesWithProvider.toString());
        }
        providerDao.delete(providerId);
    }
}
