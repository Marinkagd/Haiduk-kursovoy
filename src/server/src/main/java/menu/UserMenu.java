package menu;

import controllers.CartController;
import controllers.FurnitureController;
import controllers.UserController;
import controllers.OrderController;
import menu.db.entity.User;
import request.commands.UserCommands;
import request.controller.BaseRequestController;

public class UserMenu extends Menu{

    private User mainUserInformation;
    private FurnitureController furnitureController;
    private CartController cartController;
    private UserController userController;
    private OrderController orderController;

    public UserMenu(BaseRequestController contr, User user)
    {
        super(contr);
        mainUserInformation = user;
        furnitureController = new FurnitureController(contr);
        cartController = new CartController(contr);
        userController = new UserController(contr);
        orderController = new OrderController(contr);
    }

    @Override
    public void start() {
        boolean toExit = false;
        while (!toExit) {
            UserCommands command = null;
            try {
                command = commandController.getCommand(UserCommands.class);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Неверная команда. Ожидалась команда " + UserCommands.class.getName());
            }

            switch (command) {
                case GET_FURNITURE_LIST          -> furnitureController.sendFurnitureList();
                case GET_FURNITURE_CATEGORY_LIST -> furnitureController.sendFurnitureCategoryList();

                case CHANGE_PERSONAL_DATA        -> userController.changePersonalData(mainUserInformation);

                case GET_CART_ELEMENT_LIST       -> cartController.sendCartElementList(mainUserInformation);
                case ADD_CART_ELEMENT            -> cartController.addCartElement(mainUserInformation.getId(), 1);
                case DELETE_FROM_CART            -> cartController.deleteCartElement();
                case CART_ELEMENT_CHANGE_AMOUNT  -> cartController.changeCartelementAmount();
                case CLEAR_CART                 -> cartController.clearCart(mainUserInformation);

                case CREATE_ORDER                -> orderController.createOrder(mainUserInformation);
                case GET_USER_ORDER_LIST         -> orderController.sendUserOrderList(mainUserInformation);
                case DELETE_ORDER                -> orderController.deleteOrder(mainUserInformation);

                case GET_SERVICED_ORDER_LIST     -> orderController.sendServicedOrderList(mainUserInformation);

                case EXIT                        -> {return;}
                default                          -> {break;}
            }
            
        }
        
    }
    
}
