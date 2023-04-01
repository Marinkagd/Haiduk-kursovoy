package request.tdo;

import java.io.Serializable;

public class CartElementTDO implements Serializable{
 
    private int id;
    private String user;
    private FurnitureTDO furniture;
    private int amount;

    public CartElementTDO(String user, FurnitureTDO furniture, int amount) {
        this.user = user;
        this.furniture = furniture;
        this.amount = amount;
    }
    
    public CartElementTDO() {
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

    @Override
    public String toString() {
        return "CartElement [id=" + id + ", user=" + user + ", furniture=" + furniture + ", amount=" + amount + "]";
    }
}
