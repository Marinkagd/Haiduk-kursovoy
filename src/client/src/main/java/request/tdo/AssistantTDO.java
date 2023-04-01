package request.tdo;

import java.io.Serializable;

public class AssistantTDO implements Serializable {
    private String id;
    private byte[]  password;
    private String firstname;
    private String secondname;

    public AssistantTDO() {
    }

    public AssistantTDO(String id, byte[]  password, String firstname, String secondname) {
        this.id = id;
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
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
    
    @Override
    public String toString() {
        return "Assistant [id=" + id + ", firstname=" + firstname + ", secondname="
                + secondname + "]";
    }

}
