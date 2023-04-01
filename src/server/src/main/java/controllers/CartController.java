package controllers;

import java.util.ArrayList;

import menu.db.dao.GenericDao;
import menu.db.dao.UserRefersDao;
import menu.db.entity.CartElement;
import menu.db.entity.Furniture;
import menu.db.entity.User;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import request.tdo.CartElementTDO;
import request.tdo.FurnitureTDO;
import request.tdo.constructors.CartElementTDOConstructor;

public class CartController extends BaseRequestController{

    public CartController(BaseRequestController contr) {
        super(contr);
    }

    public void addCartElement(String user, int amount){
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var furnitureTDO = (FurnitureTDO)inputObjectStream.readObject();
            var furnitureDao = new GenericDao<>(Furniture.class);
            Furniture furniture = furnitureDao.getById(furnitureTDO.getId());
            //если товар удалили
            if(furniture == null)
                return;
            CartElement cartElement = new CartElement(user, furniture, amount);
            var cartElementDao = new GenericDao<>(CartElement.class);
            outputObjectStream.writeObject(cartElementDao.save(cartElement));
        } catch (Exception e) {
           e.getStackTrace();
           return;
        }
    }

    public void sendCartElementList(User user){
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var cartelementdao = new UserRefersDao<>(CartElement.class);
            var cartelementList = cartelementdao.getElementsRefersToUser(user);
            var cartelementTDOConstructor = new CartElementTDOConstructor();
            var tdoList = new ArrayList<CartElementTDO>();
            for (CartElement cartElement : cartelementList) {
                tdoList.add(cartelementTDOConstructor.cartElementTDOConversion(cartElement));
            }
            outputObjectStream.writeObject(tdoList);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void deleteCartElement(){
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try 
        {
            var cartElementTdo = (CartElementTDO)inputObjectStream.readObject();
            var furnitureDao = new GenericDao<>(Furniture.class);
            CartElement cartElement = new CartElement(cartElementTdo,
                                                        furnitureDao.getById(cartElementTdo.getFurniture().getId()));
            var cartelementDao = new GenericDao<>(CartElement.class);
            cartelementDao.remove(cartElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCart(User user)
    {
        try 
        {
            var cartelementDao = new UserRefersDao<>(CartElement.class);
            cartelementDao.removeElementsRefersToUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeCartelementAmount()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try 
        {
            var cartElementTdo = (CartElementTDO)inputObjectStream.readObject();
            var furnitureDao = new GenericDao<>(Furniture.class);
            CartElement cartElement = new CartElement(cartElementTdo,
                                                        furnitureDao.getById(cartElementTdo.getFurniture().getId()));
            var cartelementDao = new GenericDao<>(CartElement.class);
            cartelementDao.update(cartElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
