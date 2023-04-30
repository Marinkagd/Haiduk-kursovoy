package db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin{
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "password", nullable = false, length = 255)
    private byte[] password;

    public Admin(byte[] password) {
        this.password = password;
    }

    public Admin(){}

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
