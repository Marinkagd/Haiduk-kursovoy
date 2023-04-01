package request.tdo;

import java.io.Serializable;

public class UserTDO implements Serializable{
    private String id;
    private byte[]  password;
    private String firstname;
    private String secondname;
    private String phonenumber;
    private String email;
    private String address;

    public UserTDO(){}

    public UserTDO(String id, byte[]  password, String firstname, String secondname, String phonenumber, String email,
            String address) {
        this.id = id;
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[]  getPassword() {
        return password;
    }

    public void setPassword(byte[]  password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", firstname=" + firstname + ", secondname=" + secondname + ", phonenumber="
                + phonenumber + ", email=" + email + ", address=" + address + "]";
    }
}
