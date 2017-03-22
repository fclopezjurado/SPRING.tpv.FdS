package wrappers;

import java.util.List;

public class UserForEditListWrapper {
    private List<UserForEditWrapper> userList;

    public UserForEditListWrapper() {
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

    @Override
    public String toString() {
        return "UserForEditListWrapper [userList=" + userList + "]";
    }

}
