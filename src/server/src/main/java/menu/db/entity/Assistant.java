package menu.db.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import request.tdo.AssistantTDO;

@Entity
@Table(name = "assistant")
public class Assistant{
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "password", nullable = false)
    private byte[]  password;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "secondname", nullable = false, length = 100)
    private String secondname;
    
    @OneToMany(mappedBy = "assistant")
    private List<ServicedOrder> servicedorderlist;

    public Assistant() {
    }

    public Assistant(byte[]  password, String firstname, String secondname) {
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
    }

    public Assistant(AssistantTDO assistanttdo)
    {
        this.id = assistanttdo.getId();
        this.password = assistanttdo.getPassword();
        this.firstname = assistanttdo.getFirstname();
        this.secondname = assistanttdo.getSecondname();
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
    
    public List<ServicedOrder> getServicedorderlist() {
        return servicedorderlist;
    }

    public void setServicedorderlist(List<ServicedOrder> servicedorderlist) {
        this.servicedorderlist = servicedorderlist;
    }


    @Override
    public String toString() {
        return "Assistant [id=" + id + ", password=" + password + ", firstname=" + firstname + ", secondname="
                + secondname + "]";
    }

}
