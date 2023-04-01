package request.tdo;

import java.io.Serializable;

public class FurnitureTDO implements Serializable {
    private int id;
    private String furniturecategory;
    private String name;
    private String manufactorer;
    private String description;
    private int warranty;
    private float price;
    private float firstcost;
    private int amount;

    public FurnitureTDO(){}

    public FurnitureTDO(String furniturecategory, String name, String manufactorer, String description, int warranty,
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFurniturecategory() {
        return furniturecategory;
    }

    public void setFurniturecategory(String furniturecategory) {
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
        return name;
    }

}
