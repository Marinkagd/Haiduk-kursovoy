package db.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import request.tdo.UserTDO;

@Table
@Entity(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "password", nullable = false)
    private byte[] password;

    @Column(name = "firstname", nullable = false, length = 255)
    private String firstname;

    @Column(name = "secondname", nullable = false, length = 255)
    private String secondname;

    @Column(name = "phonenumber", nullable = true, length = 20)
    private String phonenumber;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "address", nullable = true, length = 100)
    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartElement> cartelementlist;
    // если только merge, то при удалении пользователя, заказы не удаляются из кода.
    //НО она удаляется в бд, т.к там они стоят cascade на удалении!
    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<UserOrder> orderlist;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ServicedOrder> servicedorderlist;

    public User(){}

    public User(byte[] password, String firstname, String secondname, String phonenumber, String email,
            String address) {
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
    }

    public User(UserTDO usertdo)
    {
        updateInformation(usertdo);
    }

    public void updateInformation(UserTDO usertdo)
    {
        this.id = usertdo.getId();
        this.password = usertdo.getPassword();
        this.firstname = usertdo.getFirstname();
        this.secondname = usertdo.getSecondname();
        this.email = usertdo.getEmail();
        this.phonenumber = usertdo.getPhonenumber();
        this.address = usertdo.getAddress();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
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
    
    public List<CartElement> getCartelementList() {
        return cartelementlist;
    }

    public void setCartelementList(List<CartElement> cartelements) {
        this.cartelementlist = cartelements;
    }

    public List<UserOrder> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<UserOrder> orderlist) {
        this.orderlist = orderlist;
    }

       
    public List<ServicedOrder> getServicedorderlist() {
        return servicedorderlist;
    }

    public void setServicedorderlist(List<ServicedOrder> servicedorderlist) {
        this.servicedorderlist = servicedorderlist;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstname=" + firstname + ", secondname=" + secondname
                + ", phonenumber=" + phonenumber + ", email=" + email + ", address=" + address + "]";
    }

}
