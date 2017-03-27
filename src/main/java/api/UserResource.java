package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistUserFieldException;
import api.exceptions.InvalidUserFieldException;
import api.exceptions.NotFoundTicketReferenceException;
import api.exceptions.NotFoundUserIdException;
import controllers.TicketController;
import controllers.UserController;
import entities.users.Role;
import wrappers.UserForEditListWrapper;
import wrappers.UserForEditWrapper;
import wrappers.UserWrapper;

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
        return this.userController.findAll();
    }

    @RequestMapping(value = Uris.USERS, method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserForEditWrapper userWrapper)
            throws InvalidUserFieldException, NotFoundUserIdException {
        validateField(userWrapper.getUsername(), "username");
        // TODO use: this.userController.updateUser(userWrapper);
    }

    @RequestMapping(value = Uris.USERS, method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody long mobile)
            throws InvalidUserFieldException, NotFoundUserIdException {
        //TODO use: this.userController.deleteUser(mobile);
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
