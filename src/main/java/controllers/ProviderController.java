package controllers;

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
}
