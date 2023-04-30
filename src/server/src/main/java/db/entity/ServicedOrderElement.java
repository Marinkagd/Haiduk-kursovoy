package db.entity;

import jakarta.persistence.*;
import request.tdo.OrderElementTDO;

@Entity
@Table(name = "servicedorderelement")
public class ServicedOrderElement {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "servicedorder", nullable = false)
    private ServicedOrder servicedorder;

    @ManyToOne
    @JoinColumn(name = "furniture", nullable = true)
    private Furniture furniture;

    @Column(name = "amount", nullable = false)
    private int amount;

    public ServicedOrderElement(ServicedOrder servicedorder, Furniture furniture, int amount) {
        this.servicedorder = servicedorder;
        this.furniture = furniture;
        this.amount = amount;
    }

    public ServicedOrderElement() {
    }

    public ServicedOrderElement(ServicedOrder servicedorder, OrderElement orderElement) {
        this.id = orderElement.getId();
        this.furniture = orderElement.getFurniture();
        this.amount = orderElement.getAmount();
        this.servicedorder = servicedorder;
    }

    public ServicedOrderElement(ServicedOrder servicedOrder, OrderElementTDO orderElementTDO, Furniture furniture) {
        this.id = orderElementTDO.getId();
        this.servicedorder = servicedOrder;
        this.furniture = furniture;
        this.amount = orderElementTDO.getAmount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServicedOrder getServicedorder() {
        return servicedorder;
    }

    public void setServicedorder(ServicedOrder servicedorder) {
        this.servicedorder = servicedorder;
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
        return "ServicedOrderElement [id=" + id + ", furniture=" + furniture + ", amount=" + amount + "]";
    }
}
