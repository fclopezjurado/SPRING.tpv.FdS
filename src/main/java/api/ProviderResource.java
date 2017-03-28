package api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistProviderFieldException;
import api.exceptions.MalformedHeaderException;
import api.exceptions.NotFoundProviderIdException;
import api.exceptions.ProviderWithArticlesException;
import controllers.ProviderController;
import wrappers.ProviderWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class ProviderResource {

    private ProviderController providerController;

    @Autowired
    public void setProviderController(ProviderController providerController) {
        this.providerController = providerController;
    }

    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ProviderWrapper providerRegistration(@RequestBody ProviderWrapper providerWrapper)
            throws MalformedHeaderException, AlreadyExistProviderFieldException {
        validateFields(providerWrapper);
        ProviderWrapper wrapper = this.providerController.registration(providerWrapper);
        return wrapper;
    }

    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.GET)
    public List<ProviderWrapper> getAll() {
        return providerController.getAll();
    }

    @RequestMapping(value = Uris.PROVIDERS, method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ProviderWrapper providerUpdate(@RequestBody ProviderWrapper providerWrapper) throws NotFoundProviderIdException {
        ProviderWrapper wrapper = providerController.editProvider(providerWrapper);
        return wrapper;
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @RequestMapping(value = Uris.PROVIDERS+Uris.ID, method = RequestMethod.DELETE)
    public void providerDelete(@PathVariable(value = "id") String id) throws ProviderWithArticlesException {
        providerController.delete(id);
    }

    private void validateFields(ProviderWrapper providerWrapper) throws MalformedHeaderException {
        if (providerWrapper.getCompany() == null || providerWrapper.getMobile() == 0L) {
            throw new MalformedHeaderException();
        }
    }
}
