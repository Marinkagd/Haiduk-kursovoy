package request.tdo.constructors;

import db.entity.CartElement;
import request.tdo.CartElementTDO;

public class CartElementTDOConstructor {
    public CartElementTDO cartElementTDOConversion(CartElement cartElement)
    {
        CartElementTDO cartElementTDO = new CartElementTDO();
        cartElementTDO.setId(cartElement.getId());
        cartElementTDO.setAmount(cartElement.getAmount());
        cartElementTDO.setUser(cartElement.getUser());
        var tempFurnConstr = new FurnitureTDOConstructor();
        cartElementTDO.setFurniture(tempFurnConstr.furnituretdoconversion(cartElement.getFurniture()));
        return cartElementTDO;
    }
}
