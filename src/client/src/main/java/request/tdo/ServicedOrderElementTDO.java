package request.tdo;

import java.io.Serializable;

public class ServicedOrderElementTDO implements Serializable{
    private int id;
    private int servicedorder;
    private FurnitureTDO furniture;
    private float furnitureprice;
    private int amount;

    public ServicedOrderElementTDO(int servicedorder, FurnitureTDO furniture, int amount) {
        this.servicedorder = servicedorder;
        this.furniture = furniture;
        this.amount = amount;
        this.furnitureprice = furniture.getPrice();
    }

    public ServicedOrderElementTDO() {}

    public ServicedOrderElementTDO(int servicedorder, OrderElementTDO orderElement) {
        this.id = orderElement.getId();
        this.furniture = orderElement.getFurniture();
        this.amount = orderElement.getAmount();
        this.servicedorder = servicedorder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServicedorder() {
        return servicedorder;
    }

    public void setServicedorder(int servicedorder) {
        this.servicedorder = servicedorder;
    }

    public FurnitureTDO getFurniture() {
        return furniture;
    }
    
    public void setFurniture(FurnitureTDO furniture) {
        this.furniture = furniture;
    }

    public float getFurnitureprice() {
        return furnitureprice;
    }

    public void setFurnitureprice(float furnitureprice) {
        this.furnitureprice = furnitureprice;
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
