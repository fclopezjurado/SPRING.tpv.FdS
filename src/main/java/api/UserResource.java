package api;

import api.exceptions.AlreadyExistUserFieldException;
import api.exceptions.InvalidUserFieldException;
import api.exceptions.NotFoundTicketReferenceException;
import api.exceptions.NotFoundUserIdException;
import controllers.TicketController;
import controllers.UserController;
import entities.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrappers.UserForEditListWrapper;
import wrappers.UserForEditWrapper;
import wrappers.UserWrapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Uris.VERSION)
public class UserResource {

    private UserController userController;

    private TicketController ticketController;

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @Autowired
    public void setTicketController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @RequestMapping(value = Uris.USERS, method = RequestMethod.POST)
    public void userRegistration(@RequestBody UserWrapper userWrapper) throws InvalidUserFieldException, AlreadyExistUserFieldException {
        validateField(userWrapper.getUsername(), "username");
        if (!this.userController.registration(userWrapper, Role.MANAGER)) {
            throw new AlreadyExistUserFieldException();
        }
    }

    @RequestMapping(value = Uris.CUSTOMERS, method = RequestMethod.POST)
    public void customerRegistration(@RequestBody UserWrapper userWrapper)
            throws InvalidUserFieldException, AlreadyExistUserFieldException {
        validateField(userWrapper.getUsername(), "username");
        if (!this.userController.registration(userWrapper, Role.CUSTOMER)) {
            throw new AlreadyExistUserFieldException();
        }
    }

    @RequestMapping(value = Uris.USERS, method = RequestMethod.GET)
    public UserForEditListWrapper getAllUsers() {
        // TODO use instead of mock: this.userController.findAll();
        List<UserForEditWrapper> list = new ArrayList<UserForEditWrapper>();
        list.add(new UserForEditWrapper(66000000, "Prueba", true, "Calle prueba", "12345678Z", "prueba@mail.com", "25/10/2008"));
        return new UserForEditListWrapper(list);
    }

    @RequestMapping(value = Uris.USERS, method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserForEditWrapper userWrapper) throws InvalidUserFieldException, NotFoundUserIdException {
        validateField(userWrapper.getUsername(), "username");
        // TODO use: this.userController.updateUser(userWrapper);
    }

    @RequestMapping(value = Uris.USERS, method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody long mobile) throws InvalidUserFieldException, NotFoundUserIdException {
        // TODO use: this.userController.deleteUser(mobile);
    }

    private void validateField(String field, String msg) throws InvalidUserFieldException {
        if (field == null || field.isEmpty()) {
            throw new InvalidUserFieldException(msg);
        }
    }

    @RequestMapping(value = Uris.USERS + Uris.REFERENCE, method = RequestMethod.GET)
    public UserWrapper getByTicketReference(@PathVariable(value = "reference") String ticketReference)
            throws NotFoundTicketReferenceException {
        if (!this.ticketController.ticketExistsByReference(ticketReference))
            throw new NotFoundTicketReferenceException();

        return this.userController.getByTicketReference(ticketReference);
    }

}
