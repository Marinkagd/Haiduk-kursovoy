package request.tdo.constructors;

import java.util.ArrayList;
import java.util.List;

import menu.db.dao.AccountDao;
import menu.db.entity.Assistant;
import menu.db.entity.OrderElement;
import menu.db.entity.ServicedOrder;
import menu.db.entity.ServicedOrderElement;
import menu.db.entity.UserOrder;
import request.tdo.OrderElementTDO;
import request.tdo.ServicedOrderElementTDO;
import request.tdo.ServicedOrderTDO;
import request.tdo.UserOrderTDO;

public class OrderTDOConstructor {

    public UserOrderTDO userOrderTDOConversion(UserOrder userOrderEntity)
    {
        UserOrderTDO userOrderTdo = new UserOrderTDO();
        userOrderTdo.setId(userOrderEntity.getId());
        userOrderTdo.setUser(userOrderEntity.getUser());
        userOrderTdo.setOrderdate(userOrderEntity.getOrderdate());
        userOrderTdo.setOrderprice(userOrderEntity.getOrderprice());
        userOrderTdo.setOrderfirstcost(userOrderEntity.getOrdefirstcost());
        List<OrderElementTDO> orderElementTDOlist = new ArrayList<OrderElementTDO>();

        for (OrderElement orderElement : userOrderEntity.getOrderelementlist()) {
            orderElementTDOlist.add(orderElementTDOConversion(orderElement));
        }
        userOrderTdo.setOrderelementlist(orderElementTDOlist);
        return userOrderTdo;
    }

    private OrderElementTDO orderElementTDOConversion(OrderElement orderElement)
    {
        OrderElementTDO orderElementTdo = new OrderElementTDO();
        orderElementTdo.setId(orderElement.getId());
        orderElementTdo.setOrder(orderElement.getOrder().getId());
        orderElementTdo.setAmount(orderElement.getAmount());
        var furnitureTDOConstructor = new FurnitureTDOConstructor();
        orderElementTdo.setFurniture(furnitureTDOConstructor.furnituretdoconversion(orderElement.getFurniture()));
        orderElementTdo.setFurnitureprice(orderElement.getFurniture().getPrice());
        orderElementTdo.setFurnitureStorageAmount(orderElement.getFurniture().getAmount());
        return orderElementTdo;
    }

    public ServicedOrderTDO servicedOrderTDOConversion(ServicedOrder servicedOrder)
    {
        ServicedOrderTDO servicedOrderTDO = new ServicedOrderTDO();
        servicedOrderTDO.setId(servicedOrder.getId());
        servicedOrderTDO.setUser(servicedOrder.getUser());
        var assistantDao = new AccountDao<>(Assistant.class);
        Assistant assistant = assistantDao.getByLogin(servicedOrder.getAssistant());
        if(assistant == null)
            servicedOrderTDO.setAssistantFIO("Удален");
        else
            servicedOrderTDO.setAssistantFIO(assistant.getFirstname() + " " + assistant.getSecondname());
        servicedOrderTDO.setDescription(servicedOrder.getDescription());
        servicedOrderTDO.setIsaccepted(servicedOrder.getIsaccepted() == 1);
        servicedOrderTDO.setOrderprice(servicedOrder.getOrderprice());
        servicedOrderTDO.setOrderfirstcost(servicedOrder.getOrderfirstcost());
        servicedOrderTDO.setAnswerdate(servicedOrder.getAnswerdate());
        servicedOrderTDO.setOrderdate(servicedOrder.getOrderdate());
        var servicedOrderElementList = new ArrayList<ServicedOrderElementTDO>();

        for (ServicedOrderElement servicedOrderElement : servicedOrder.getOrderelementlist()) {
            servicedOrderElementList.add(servicedOrderElementTDOConversion(servicedOrderElement));
        }
        servicedOrderTDO.setOrderelementlist(servicedOrderElementList);
        return servicedOrderTDO;
    }

    private ServicedOrderElementTDO servicedOrderElementTDOConversion(ServicedOrderElement servicedOrderElement)
    {
        var servicedOrderElementTDO = new ServicedOrderElementTDO();
        servicedOrderElementTDO.setId(servicedOrderElement.getId());
        servicedOrderElementTDO.setServicedorder(servicedOrderElement.getServicedorder().getId());
        var furnitureTDOConstructor = new FurnitureTDOConstructor();
        servicedOrderElementTDO.setFurniture(furnitureTDOConstructor.furnituretdoconversion(servicedOrderElement.getFurniture()));
        if(servicedOrderElement.getFurniture() == null)
            servicedOrderElementTDO.setFurnitureprice(0.0f);
        else
            servicedOrderElementTDO.setFurnitureprice(servicedOrderElement.getFurniture().getPrice());
        servicedOrderElementTDO.setAmount(servicedOrderElement.getAmount());
        return servicedOrderElementTDO;
    }
}
