package db.entity;

import jakarta.persistence.*;
import request.tdo.FurnitureTDO;

@Entity
@Table(name = "furniture")
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    // @Column(name = "furniturecategory", nullable = false)
    // private int furniturecategory;
    @ManyToOne
    @JoinColumn(name = "furniturecategory", nullable = false)
    private FurnitureCategory furniturecategory;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "manufactorer", nullable = false, length = 45)
    private String manufactorer;

    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @Column(name = "warranty", nullable = true)
    private int warranty;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "firstcost", nullable = false)
    private float firstcost;

    @Column(name = "amount", nullable = true)
    private int amount;

    public Furniture() {
    }

    public Furniture(FurnitureCategory furniturecategory, String name, String manufactorer, String description, int warranty,
                     float price, float firstcost, int amount) {
        this.furniturecategory = furniturecategory;
        this.name = name;
        this.manufactorer = manufactorer;
        this.description = description;
        this.warranty = warranty;
        this.price = price;
        this.firstcost = firstcost;
        this.amount = amount;
    }

    public Furniture(FurnitureTDO furnitureTDO, FurnitureCategory furniturecategory) {
        this.id = furnitureTDO.getId();
        this.furniturecategory = furniturecategory;
        this.name = furnitureTDO.getName();
        this.manufactorer = furnitureTDO.getManufactorer();
        this.description = furnitureTDO.getDescription();
        this.warranty = furnitureTDO.getWarranty();
        this.price = furnitureTDO.getPrice();
        this.firstcost = furnitureTDO.getFirstcost();
        this.amount = furnitureTDO.getAmount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FurnitureCategory getFurniturecategory() {
        return furniturecategory;
    }

    public void setFurniturecategory(FurnitureCategory furniturecategory) {
        this.furniturecategory = furniturecategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufactorer() {
        return manufactorer;
    }

    public void setManufactorer(String manufactorer) {
        this.manufactorer = manufactorer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getFirstcost() {
        return firstcost;
    }

    public void setFirstcost(float firstcost) {
        this.firstcost = firstcost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int ammount) {
        this.amount = ammount;
    }

    @Override
    public String toString() {
        return "Furniture [id=" + id + ", furniturecategory=" + furniturecategory + ", name=" + name + ", manufactorer="
                + manufactorer + ", description=" + description + ", warranty=" + warranty + ", price=" + price
                + ", firstcost=" + firstcost + ", ammount=" + amount + "]";
    }

}
