package wrappers;

import java.util.ArrayList;
import java.util.List;

import entities.users.User;

public class UserForEditListWrapper {

    private List<UserForEditWrapper> userList;

    public UserForEditListWrapper() {
        this.userList = new ArrayList<UserForEditWrapper>();
    }

    public UserForEditListWrapper(List<UserForEditWrapper> userList) {
        super();
        this.userList = userList;
    }

    public List<UserForEditWrapper> getUserList() {
        return userList;
    }

    public void setUserList(List<UserForEditWrapper> userList) {
        this.userList = userList;
    }

    public void wrapUsers(List<User> users) {
        for (User user : users)
            this.userList.add(new UserForEditWrapper(user.getMobile(), user.getUsername(), user.isActive(), user.getAddress(),
                    user.getDni(), user.getEmail(), user.getRegistrationDate()));
    }

    public UserForEditWrapper findByMobile(long mobile) {
        if (!this.isEmpty())
            for (UserForEditWrapper user : this.userList)
                if (user.getMobile() == mobile)
                    return user;

        return new UserForEditWrapper();
    }

    public boolean isEmpty() {
        return this.userList.isEmpty();
    }

    @Override
    public String toString() {
        return "UserForEditListWrapper [userList=" + userList + "]";
    }

}
