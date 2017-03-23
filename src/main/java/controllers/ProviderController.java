package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ProviderDao;
import entities.core.Provider;
import wrappers.ProviderWrapper;

@Controller
public class ProviderController {

    private ProviderDao providerDao;

    @Autowired
    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    public boolean registration(ProviderWrapper providerWrapper) {
        if (null == providerDao.findByMobile(providerWrapper.getMobile())) {
            Provider provider = new Provider(providerWrapper.getCompany(), providerWrapper.getAddress(), providerWrapper.getMobile(),
                    providerWrapper.getMobile(), providerWrapper.getPaymentConditions(), providerWrapper.getNote());
            providerDao.save(provider);
            return true;
        } else {
            return false;
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

    public void delete(String id) {
        Integer providerId = Integer.parseInt(id);
        providerDao.delete(providerId);
    }
}
