package db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderelement")
public class OrderElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userorder", nullable = false)
    private UserOrder userorder;

    @ManyToOne
    @JoinColumn(name = "furniture", nullable = false)
    private Furniture furniture;

    @Column(name = "amount", nullable = false)
    private int amount;

    public OrderElement() {
    }

    public OrderElement(UserOrder order, Furniture furniture, int amount) {
        this.userorder = order;
        this.furniture = furniture;
        this.amount = amount;
    }

    public OrderElement(UserOrder order, CartElement cartElement) {
        this.userorder = order;
        this.furniture = cartElement.getFurniture();
        this.amount = cartElement.getAmount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserOrder getOrder() {
        return userorder;
    }

    public void setOrder(UserOrder order) {
        this.userorder = order;
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
        return "OrderElement [id=" + id  + ", furniture=" + furniture + ", amount=" + amount + "]";
    }
    
}
