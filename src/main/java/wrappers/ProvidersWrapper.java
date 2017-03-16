package wrappers;

import java.util.ArrayList;
import java.util.List;

import entities.core.Provider;

public class ProvidersWrapper {

    List<ProviderWrapper> providersWrapper;

    public void setProvidersWrapper(List<ProviderWrapper> providersWrapper) {
        this.providersWrapper = providersWrapper;
    }

    public List<ProviderWrapper> getProvidersWrapper() {
        return providersWrapper;
    }

    public ProvidersWrapper() {
    }

    public ProvidersWrapper(List<Provider> providers) {
        providersWrapper = new ArrayList<>();
        for (Provider provider : providers) {
            providersWrapper.add(new ProviderWrapper(provider));
        }
    }

}
