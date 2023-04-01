package controllers;

import java.util.List;

import request.commands.AssistantCommands;
import request.commands.ConfirmCommands;
import request.commands.OrderCommands;
import request.commands.UserCommands;
import request.controller.BaseRequestController;
import request.tdo.CartElementTDO;
import request.tdo.OrderAnswerTDO;
import request.tdo.ServicedOrderTDO;
import request.tdo.UserOrderTDO;

public class OrderController {
    public static void createOrder(List<CartElementTDO> cartElementList)
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.CREATE_ORDER);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(cartElementList);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ;
                }
            }
            default: break;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<UserOrderTDO> getOrderList(OrderCommands type)
    {   
        switch (type) {
            case GET_USER_ORER_LIST:BaseRequestController.sendRequest(UserCommands.class, UserCommands.GET_USER_ORDER_LIST);break;
            case GET_FULL_ORDER_LIST:BaseRequestController.sendRequest(AssistantCommands.class, AssistantCommands.GET_ORDER_LIST);break;
            default:
                break;
        }
        
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    var userOrderList = (List<UserOrderTDO>)BaseRequestController.getObjectInputStream().readObject();
                    return userOrderList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            default: break;
        }
        return null;
    }

    public static void deleteOrder(UserOrderTDO userOrderTDO)
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.DELETE_ORDER);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(userOrderTDO);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            default: break;
        }
    }

    public static void answerToOrder(OrderAnswerTDO orderAnswer)
    {
        BaseRequestController.sendRequest(AssistantCommands.class, AssistantCommands.ORDER_ANSWER);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(orderAnswer);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            default: break;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<ServicedOrderTDO> getServicedOrderList()
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.GET_SERVICED_ORDER_LIST);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    var servicedOrderTDOList = (List<ServicedOrderTDO>)BaseRequestController.getObjectInputStream().readObject();
                    return servicedOrderTDOList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            default: break;
        }
        return null;
    }

}
