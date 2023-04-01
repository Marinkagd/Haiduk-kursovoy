package controllers;

import java.util.List;

import request.commands.ConfirmCommands;
import request.commands.UserCommands;
import request.controller.BaseRequestController;
import request.tdo.CartElementTDO;
import request.tdo.FurnitureTDO;

public class CartController {
    
    public static boolean addCartElement(FurnitureTDO furnitureTDO)
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.ADD_CART_ELEMENT);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return false;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   try {
                    BaseRequestController.getObjectOutputStream().writeObject(furnitureTDO);
                    var result = (Boolean)BaseRequestController.getObjectInputStream().readObject();
                    return result;
                } catch (Exception e) {
                    e.getStackTrace();
                    return false;
                }
            }
            default:
                break;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static List<CartElementTDO> getCartElementsList()
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.GET_CART_ELEMENT_LIST);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   try {
                    var result = (List<CartElementTDO>)BaseRequestController.getObjectInputStream().readObject();
                    return result;
                } catch (Exception e) {
                    e.getStackTrace();
                    return null;
                }
            }
            default:
                break;
        }
        return null;
    }

    public static void deleteFromCart(CartElementTDO cartElementTDO)
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.DELETE_FROM_CART);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   try {
                    BaseRequestController.getObjectOutputStream().writeObject(cartElementTDO);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            default:
                break;
        }
    }

    public static void clearCart()
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.CLEAR_CART);
    }

    public static void changeCartElementAmount(CartElementTDO cartElementTDO){
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.CART_ELEMENT_CHANGE_AMOUNT);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   try {
                    BaseRequestController.getObjectOutputStream().writeObject(cartElementTDO);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            default:
                break;
        }
    }
}
