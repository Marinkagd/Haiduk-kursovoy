package db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import request.tdo.CartElementTDO;

@Entity
@Table(name = "cartelement")
public class CartElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "user")
    private String user;
    // @ManyToOne
    // @JoinColumn(name = "user", nullable = false)
    // private User user;

    @ManyToOne
    @JoinColumn(name = "furniture", nullable = false)
    private Furniture furniture;

    @Column(name = "amount", nullable = false)
    private int amount;

    public CartElement(String user, Furniture furniture, int amount) {
        this.user = user;
        this.furniture = furniture;
        this.amount = amount;
    }

    public CartElement(CartElementTDO cartTDO, Furniture furniture)
    {
        this.id = cartTDO.getId();
        this.user = cartTDO.getUser();
        this.amount = cartTDO.getAmount();
        this.furniture = furniture;
    }

    public CartElement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartElement [id=" + id + ", user=" + user + ", furniture=" + furniture + ", amount=" + amount + "]";
    }
}
