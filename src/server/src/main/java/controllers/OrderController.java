package controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import menu.db.dao.GenericDao;
import menu.db.dao.UserRefersDao;
import menu.db.entity.Assistant;
import menu.db.entity.CartElement;
import menu.db.entity.Furniture;
import menu.db.entity.OrderElement;
import menu.db.entity.ServicedOrder;
import menu.db.entity.ServicedOrderElement;
import menu.db.entity.User;
import menu.db.entity.UserOrder;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import request.tdo.CartElementTDO;
import request.tdo.OrderAnswerTDO;
import request.tdo.OrderElementTDO;
import request.tdo.ServicedOrderTDO;
import request.tdo.UserOrderTDO;
import request.tdo.constructors.OrderTDOConstructor;

public class OrderController extends BaseRequestController{

    public OrderController(BaseRequestController contr) {
        super(contr);
    }

    @SuppressWarnings("unchecked")
    public void createOrder(User user){
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var cartElementTDOList = (List<CartElementTDO>)inputObjectStream.readObject();
            var order = new UserOrder();
            order.setUser(user.getId());
            order.setOrderdate(new Date(System.currentTimeMillis()));
        
            var furnituredao = new GenericDao<>(Furniture.class);
            var cartElementList = new ArrayList<CartElement>();
            for (CartElementTDO cartElementTDO : cartElementTDOList) {
                cartElementList.add(new CartElement(cartElementTDO,
                furnituredao.getById(cartElementTDO.getFurniture().getId())));
            }
            var orderElementList = new ArrayList<OrderElement>();
            for (CartElement cartElement : cartElementList) {
                orderElementList.add(new OrderElement(order, cartElement));
            }
            
            order.setPriceAndFirstCost(orderElementList);
            var orderdao = new GenericDao<>(UserOrder.class);
            if(orderdao.save(order))
            {
                var orderelementdao = new GenericDao<>(OrderElement.class);
                orderElementList.forEach(orderelementdao::save);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


    public void sendUserOrderList(User user)
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var userOrderDao = new UserRefersDao<>(UserOrder.class);
            var userOrderList =  userOrderDao.getElementsRefersToUser(user);

            var userOrderTDOList = new ArrayList<UserOrderTDO>();
            var orderTDOConstructor = new OrderTDOConstructor();

            for (UserOrder userOrder : userOrderList) {
                userOrderTDOList.add(orderTDOConstructor.userOrderTDOConversion(userOrder));
            }
            outputObjectStream.writeObject(userOrderTDOList);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void sendOrderList()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try{
            var userOrderDao = new GenericDao<>(UserOrder.class);
            var userOrderList = userOrderDao.getAll();

            var userOrderTDOList = new ArrayList<UserOrderTDO>();
            var orderTDOConstructor = new OrderTDOConstructor();

            for (UserOrder userOrder : userOrderList) {
                userOrderTDOList.add(orderTDOConstructor.userOrderTDOConversion(userOrder));
            }
            outputObjectStream.writeObject(userOrderTDOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(User user)
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var userOrderTDO = (UserOrderTDO) inputObjectStream.readObject();
            UserOrder userOrder = new UserOrder(userOrderTDO);

            //проверка на то, что заказ уже обработали
            var servicedOrderDao = new GenericDao<>(ServicedOrder.class);
            if(servicedOrderDao.getById(userOrder.getId()) != null)
                return;

            var userOrderDao = new GenericDao<>(UserOrder.class);
            userOrderDao.remove(userOrder);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void orderAnswer(Assistant assistant)
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var orderAnswerTDO = (OrderAnswerTDO)inputObjectStream.readObject();
            
            //проверка на то, что заказ не был отменен пользователем
            var userOrderDao = new GenericDao<>(UserOrder.class);
            if(userOrderDao.getById(orderAnswerTDO.getUserOrderTDO().getId()) == null)
                return; // т.к. заказ был отменен

            ServicedOrder servicedOrder = new ServicedOrder(orderAnswerTDO, assistant.getId());

            var servicedOrderElementsList = new ArrayList<ServicedOrderElement>();
            var furnitureDao = new GenericDao<>(Furniture.class);
            for (OrderElementTDO orderElementTDO : orderAnswerTDO.getUserOrderTDO().getOrderelementlist()) {
                servicedOrderElementsList.add(new ServicedOrderElement(
                                                                    servicedOrder,
                                                                    orderElementTDO,
                                                                    furnitureDao.getById(orderElementTDO.getFurniture().getId()))
                                                                    );
            }
            var servicedOrderDao = new GenericDao<>(ServicedOrder.class);
            var servicedOrderElementDao = new GenericDao<>(ServicedOrderElement.class);

            //повторная проверка на количество мебели в базы данных
            if(orderAnswerTDO.isIsaccepted())
                for (OrderElementTDO orderElementTdo : orderAnswerTDO.getUserOrderTDO().getOrderelementlist()) {
                    if(orderElementTdo.getAmount() > furnitureDao.getById(orderElementTdo.getFurniture().getId()).getAmount())
                        return;
                }

            //проверка на то, что кто-то уже ответил на заказ
            if(servicedOrderDao.getById(servicedOrder.getId()) != null)
                return;

            if(servicedOrderDao.save(servicedOrder))
            {
                servicedOrderElementsList.forEach(servicedOrderElementDao::save);

                if(orderAnswerTDO.isIsaccepted())
                {
                    for (ServicedOrderElement servicedOrderElement : servicedOrderElementsList) {
                        Furniture furnitureToChangeAmount;
                        furnitureToChangeAmount = servicedOrderElement.getFurniture();
                        furnitureToChangeAmount.setAmount(furnitureToChangeAmount.getAmount() - servicedOrderElement.getAmount());
                        furnitureDao.update(furnitureToChangeAmount);
                    }
                }

                var orderElementDao = new GenericDao<>(UserOrder.class);
                orderElementDao.remove(new UserOrder(orderAnswerTDO.getUserOrderTDO()));
            }
        } catch (Exception e) {
           e.getStackTrace();
        }
    }

    public void sendServicedOrderList(User user)
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var servicedOrderDao = new UserRefersDao<>(ServicedOrder.class);
            var servicedOrderList = servicedOrderDao.getElementsRefersToUser(user);
            
            var servicedOrderTDOList = new ArrayList<ServicedOrderTDO>();
            var orderTDOConstructor = new OrderTDOConstructor();
            for (ServicedOrder servicedOrder : servicedOrderList) {
                servicedOrderTDOList.add(orderTDOConstructor.servicedOrderTDOConversion(servicedOrder));
            }
            outputObjectStream.writeObject(servicedOrderTDOList);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
}
