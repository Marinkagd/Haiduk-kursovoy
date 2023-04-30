package menu;

import controllers.FurnitureController;
import controllers.OrderController;
import db.entity.Assistant;
import request.commands.AssistantCommands;
import request.controller.BaseRequestController;

public class AssistantMenu extends Menu{
    private Assistant assistant;
    private OrderController orderController;
    private FurnitureController furnitureController;
    protected AssistantMenu(BaseRequestController contr, Assistant assistantinformation) {
        super(contr);
        assistant = assistantinformation;
        orderController = new OrderController(contr);
        furnitureController = new FurnitureController(contr);
    }

    @Override
    public void start() {
        boolean toExit = false;
        while (!toExit) {
            AssistantCommands command = null;
            try {
                command = commandController.getCommand(AssistantCommands.class);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Неверная команда. Ожидалась команда " + AssistantCommands.class.getName());
            }

            switch (command) {
                case GET_ORDER_LIST                 -> orderController.sendOrderList();
                case ORDER_ANSWER                   -> orderController.orderAnswer(assistant);
                case GET_FURNITURE_LIST             -> furnitureController.sendFurnitureList();
                case GET_FURNITURE_CATEGORY_LIST    -> furnitureController.sendFurnitureCategoryList();
                case CHANGE_FURNITURE_DATA          -> furnitureController.changeFurnitureData();
                case DELETE_FURNITURE               -> furnitureController.deleteFurniture();
                case ADD_FURNITURE                  -> furnitureController.addFurniture();
                case EXIT                           -> {return;}
                default                             -> {break;}
            }
            
        }
        
    }
    
}
