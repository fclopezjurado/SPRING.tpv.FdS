package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.NotFoundTicketReferenceException;
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

    public boolean userExistsByEmail(String email) {
        User user = this.userDao.findByEmail(email);

        if (user != null)
            return user.getEmail().equals(email);

        return false;
    }

    public UserWrapper getByTicketReference(String ticketReference) throws NotFoundTicketReferenceException {
        User user = this.userDao.findByTicketReference(ticketReference);

        if (user == null)
            throw new NotFoundTicketReferenceException();

        return new UserWrapper(user.getMobile(), user.getUsername(), user.getPassword());
    }

    public UserForEditListWrapper findAll() {
        UserForEditListWrapper usersWrapper = new UserForEditListWrapper();
        usersWrapper.wrapUsers(this.userDao.findAll());
        return usersWrapper;
    }

    public void deleteUser(long mobile) throws Exception {
        User user = this.userDao.findByMobile(mobile);
        for (Authorization auth : this.authorizationDao.findAll()) {
            if (auth.getId() == user.getId()) {
                this.authorizationDao.delete(auth);
            }
        }
        this.userDao.delete(user);
    }

    public void updateUser(UserForEditWrapper userWrapper) {
        User user = this.userDao.findByMobile(userWrapper.getMobile());

        user.setActive(userWrapper.isActive());
        user.setAddress(userWrapper.getAddress());
        user.setDni(userWrapper.getDni());
        user.setEmail(userWrapper.getEmail());
        user.setUsername(userWrapper.getUsername());

        userDao.save(user);
    }

}
