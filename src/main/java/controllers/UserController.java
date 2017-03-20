package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.users.AuthorizationDao;
import daos.users.UserDao;
import entities.users.Authorization;
import entities.users.Role;
import entities.users.User;
import wrappers.UserForEditListWrapper;
import wrappers.UserForEditWrapper;
import wrappers.UserWrapper;

@Controller
public class UserController {

    private static final String DATE_FORMAT = "DD-MMM-yyyy";

    private UserDao userDao;

    private AuthorizationDao authorizationDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public boolean registration(UserWrapper userWrapper, Role role) {
        if (null == userDao.findByMobile(userWrapper.getMobile())) {
            User user = new User(userWrapper.getMobile(), userWrapper.getUsername(), userWrapper.getPassword());
            userDao.save(user);
            authorizationDao.save(new Authorization(user, role));
            return true;
        } else {
            return false;
        }
    }

    public boolean userExistsByMobile(long mobile) {
        User user = this.userDao.findByMobile(mobile);

        if (user != null)
            return user.getMobile() == mobile;

        return false;
    }

    public UserForEditListWrapper getByTicketReference(String ticketReference) {
        List<User> users = this.userDao.findByTicketReference(ticketReference);
        List<UserForEditWrapper> usersWrapper = new ArrayList<UserForEditWrapper>();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        for (User user : users)
            usersWrapper.add(new UserForEditWrapper(user.getMobile(), user.getUsername(), user.isActive(), user.getAddress(), user.getDni(),
                    user.getEmail(), formatter.format(user.getRegistrationDate())));

        return new UserForEditListWrapper(usersWrapper);
    }

}
