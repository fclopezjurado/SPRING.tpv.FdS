package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wrappers.ProviderWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class ProviderResource {

    // TODO: Definir excepciones
    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.POST)
    public String providerRegistration(@RequestBody ProviderWrapper providerWrapper) throws Exception {
        return "El método para la creación de proveedores está en construcción";
    }

    // TODO: Definir excepciones
    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.GET)
    public List<ProviderWrapper> providerRecover(String id) throws Exception {
        List<ProviderWrapper> providers = new ArrayList<>();
        long mobile = 6;
        ProviderWrapper providerMock = new ProviderWrapper(1, "company", "address", mobile, "",
                "El método para la obtención de proveedores está en construcción");
        providers.add(providerMock);
        return providers;
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
}
