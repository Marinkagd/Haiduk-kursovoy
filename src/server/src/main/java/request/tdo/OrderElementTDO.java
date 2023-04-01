package request.tdo;

import java.io.Serializable;

public class OrderElementTDO implements Serializable{
    private int id;
    private int userorder;
    private FurnitureTDO furniture;
    private float furnitureprice;
    private int amount;

    private int furnitureStorageAmount;

    public OrderElementTDO() {
    }

    public OrderElementTDO(int userorder, FurnitureTDO furniture, int amount) {
        this.userorder = userorder;
        this.furniture = furniture;
        this.amount = amount;
        this.furnitureprice = furniture.getPrice();
        this.furnitureStorageAmount = furniture.getAmount();
    }

    public OrderElementTDO(int userorder, CartElementTDO cartElement) {
        this.userorder = userorder;
        this.furniture = cartElement.getFurniture();
        this.amount = cartElement.getAmount();
        this.furnitureprice = cartElement.getFurniture().getPrice();
        this.furnitureStorageAmount = cartElement.getFurniture().getAmount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return userorder;
    }

    public void setOrder(int order) {
        this.userorder = order;
    }

    public int getFurnitureStorageAmount() {
        return furnitureStorageAmount;
    }

    public void setFurnitureStorageAmount(int furnitureStorageAmount) {
        this.furnitureStorageAmount = furnitureStorageAmount;
    }


    public FurnitureTDO getFurniture() {
        return furniture;
    }

    public void setFurniture(FurnitureTDO furniture) {
        this.furniture = furniture;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getFurnitureprice() {
        return furnitureprice;
    }

    public void setFurnitureprice(float furnitureprice) {
        this.furnitureprice = furnitureprice;
    }

    @Override
    public String toString() {
        return "OrderElement [id=" + id  + ", furniture=" + furniture + ", amount=" + amount + "]";
    }
    
}
