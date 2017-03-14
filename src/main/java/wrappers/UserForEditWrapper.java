package wrappers;

public class UserForEditWrapper {

    private long mobile;

    private String username;
    
    private boolean active;
    
    private String address;
    
    private String dni;
    
    private String email;
    
    private String registrationDate;

    public UserForEditWrapper() {
    }

    public UserForEditWrapper(long mobile, String username, boolean active,
            String address, String dni, String email, String registrationDate) {
        this.mobile = mobile;
        this.username = username;
        this.active = active;
        this.address = address;
        this.dni = dni;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserForEditWrapper [mobile=" + mobile + ", username=" + username + ", active=" + active + ", address=" + address + ", dni="
                + dni + ", email=" + email + ", registrationDate=" + registrationDate + "]";
    }
}
