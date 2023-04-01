package request.tdo;

import java.io.Serializable;

public class AdminTDO implements Serializable {
  
    private String id;  
    private byte[] password;

    public AdminTDO(String id, byte[] password) {
        this.id = id;
        this.password = password;
    }

    public AdminTDO(){}

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

    @Override
    public String toString() {
        return "Admin [id=" + id + ", password=" + password + "]";
    }
}
