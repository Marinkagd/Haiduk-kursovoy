package request.tdo.constructors;

import db.entity.Furniture;
import db.entity.FurnitureCategory;
import request.tdo.FurnitureCategoryTDO;
import request.tdo.FurnitureTDO;

public class FurnitureTDOConstructor {
    
    public FurnitureTDO furnituretdoconversion(Furniture furnitureentity)
    {
        if(furnitureentity == null)
            return null;
        FurnitureTDO furnituretdo = new FurnitureTDO();
        furnituretdo.setId(furnitureentity.getId());
        furnituretdo.setName(furnitureentity.getName());
        furnituretdo.setManufactorer(furnitureentity.getManufactorer());
        furnituretdo.setDescription(furnitureentity.getDescription());
        furnituretdo.setPrice(furnitureentity.getPrice());
        furnituretdo.setFirstcost(furnitureentity.getFirstcost());
        furnituretdo.setWarranty(furnitureentity.getWarranty());
        furnituretdo.setAmount(furnitureentity.getAmount());
        furnituretdo.setFurniturecategory(furnitureentity.getFurniturecategory().getName());
        return furnituretdo;
    }

    public FurnitureCategoryTDO furnituteCategotyTDOConversion(FurnitureCategory furnitureCategory)
    {
        FurnitureCategoryTDO furnCategoryTDO = new FurnitureCategoryTDO();
        furnCategoryTDO.setId(furnitureCategory.getId());
        furnCategoryTDO.setName(furnitureCategory.getName());
        return furnCategoryTDO;
    }
}
