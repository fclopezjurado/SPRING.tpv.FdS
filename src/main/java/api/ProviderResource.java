package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistProviderFieldException;
import controllers.ProviderController;
import wrappers.ProviderWrapper;
import wrappers.ProvidersWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class ProviderResource {

    private ProviderController providerController;

    @Autowired
    public void setProviderController(ProviderController providerController) {
        this.providerController = providerController;
    }

    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.POST)
    public void providerRegistration(@RequestBody ProviderWrapper providerWrapper) throws Exception {
        validateFields(providerWrapper);
        if (!this.providerController.registration(providerWrapper)) {
            throw new AlreadyExistProviderFieldException();
        }
    }

    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.GET)
    public ProvidersWrapper getAll() throws Exception {
        ProvidersWrapper wrappers = new ProvidersWrapper(providerController.getAll());
        return wrappers;
    }

    // TODO: Definir excepciones
    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.PUT)
    public List<ProviderWrapper> providerUpdate(@RequestBody ProviderWrapper providerWrapper) throws Exception {
        List<ProviderWrapper> providers = new ArrayList<>();
        long mobile = 6;
        ProviderWrapper providerMock = new ProviderWrapper(1, "company", "address", mobile, "",
                "El método para la actualización de proveedores está en construcción");
        providers.add(providerMock);
        return providers;
    }

    // TODO: Definir excepciones
    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.DELETE)
    public String providerDelete(String id) throws Exception {
        return "No implementado el borrado del proveedor: " + id;
    }

    private void validateFields(ProviderWrapper providerWrapper) {
        if (providerWrapper.getCompany() == null || providerWrapper.getMobile() == 0L) {
            throw new IllegalArgumentException();
        }
    }
}
