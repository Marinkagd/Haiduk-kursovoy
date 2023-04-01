package request.tdo;

import java.io.Serializable;

public class FurnitureFilterTDO implements Serializable {
    private float priceFrom;
    private float priceTo;
    private boolean isPrice; 
    private int warrantyFrom;
    private int warrantyTo;
    private boolean isWarranty;
    private int furniturecategory;    
    private boolean isCategory;
    private String name;
    private boolean isName;
    
    public FurnitureFilterTDO() {
        isPrice = false;
        isWarranty = false;
        isCategory = false;
        isName = false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPriceFrom() {
        return priceFrom;
    }
    public void setPriceFrom(float priceFrom) {
        this.priceFrom = priceFrom;
    }
    public float getPriceTo() {
        return priceTo;
    }
    public void setPriceTo(float priceTo) {
        this.priceTo = priceTo;
    }
    public int getWarrantyFrom() {
        return warrantyFrom;
    }
    public void setWarrantyFrom(int warrantyFrom) {
        this.warrantyFrom = warrantyFrom;
    }
    public int getWarrantyTo() {
        return warrantyTo;
    }
    public void setWarrantyTo(int warrantyTo) {
        this.warrantyTo = warrantyTo;
    }
    public int getCategory() {
        return furniturecategory;
    }
    public void setCategory(int furniturecategory) {
        this.furniturecategory = furniturecategory;
    }

    public boolean isPrice() {
        return isPrice;
    }
    
    public boolean isWarranty() {
        return isWarranty;
    }

    public boolean isCategory() {
        return isCategory;
    }

    public boolean isName() {
        return isName;
    }

    public void setisPrice(boolean isPrice) {
        this.isPrice = isPrice;
    }

    public void setisWarranty(boolean isWarranty) {
        this.isWarranty = isWarranty;
    }

    public void setisCategory(boolean isCategory) {
        this.isCategory = isCategory;
    }

    public void setisName(boolean isName) {
        this.isName = isName;
    }
    
}
