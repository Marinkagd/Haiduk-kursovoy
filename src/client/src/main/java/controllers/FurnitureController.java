package controllers;

import java.util.List;

import request.commands.AssistantCommands;
import request.commands.ConfirmCommands;
import request.commands.FurnitureCommands;
import request.commands.UserCommands;
import request.controller.BaseRequestController;
import request.tdo.FurnitureCategoryTDO;
import request.tdo.FurnitureFilterTDO;
import request.tdo.FurnitureTDO;

public class FurnitureController {
    private static FurnitureFilterTDO filters = null;
    
    @SuppressWarnings("unchecked")
    public static List<FurnitureTDO> getFurnitureList(FurnitureCommands type)
    {   
        switch (type) {
            case GET_FURNITURE_LIST_USER:
                BaseRequestController.sendRequest(UserCommands.class, UserCommands.GET_FURNITURE_LIST);
                break;
            case GET_FURNITURE_LIST_ASSISTANT:
                BaseRequestController.sendRequest(AssistantCommands.class, AssistantCommands.GET_FURNITURE_LIST);
                break;
            default:
                return null;
        }
       
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(filters);
                    var furnitureList = (List<FurnitureTDO>)BaseRequestController.getObjectInputStream().readObject();
                    return furnitureList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            default:
                break;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<FurnitureCategoryTDO> getFurnitureCategoryList(FurnitureCommands type)
    {
        switch (type) {
            case GET_FURNITURE_CATEGORY_USER:
                BaseRequestController.sendRequest(UserCommands.class, UserCommands.GET_FURNITURE_CATEGORY_LIST);
                break;
            case GET_FURNITURE_CATEGORY_ASSISTANT:
                BaseRequestController.sendRequest(AssistantCommands.class, AssistantCommands.GET_FURNITURE_CATEGORY_LIST);
                break;
            default:
                return null;
        }
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   
                try {
                    var furnitureCategoryList = (List<FurnitureCategoryTDO>)BaseRequestController.getObjectInputStream().readObject();
                    return furnitureCategoryList;
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

    public static void changeFurnitureData(FurnitureTDO changedFurnitureTDO)
    {
        BaseRequestController.sendRequest(AssistantCommands.class, AssistantCommands.CHANGE_FURNITURE_DATA);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            e.getStackTrace();
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(changedFurnitureTDO);
                } catch (Exception e) {
                    e.getStackTrace();
                    return;
                }
            }
            default:
                break;
        }
        return;
    }

    public static void deleteFurniture(FurnitureTDO changedFurnitureTDO)
    {
        BaseRequestController.sendRequest(AssistantCommands.class, AssistantCommands.DELETE_FURNITURE);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            e.getStackTrace();
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(changedFurnitureTDO);
                } catch (Exception e) {
                    e.getStackTrace();
                    return;
                }
            }
            default:
                break;
        }
        return;
    }

    public static void addFurniture(FurnitureTDO newFurnitureTDO)
    {
        BaseRequestController.sendRequest(AssistantCommands.class, AssistantCommands.ADD_FURNITURE);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            e.getStackTrace();
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {   
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(newFurnitureTDO);
                } catch (Exception e) {
                    e.getStackTrace();
                    return;
                }
            }
            default:
                break;
        }
        return;
    }

    public static void setPriceFilter(float priceFrom, float priceTo)
    {
        if(filters == null)
            filters = new FurnitureFilterTDO();
        filters.setPriceFrom(priceFrom);
        filters.setPriceTo(priceTo);
        filters.setisPrice(true);
    }

    public static void setWarrantyFilter(int warrantyFrom, int warrantyTo)
    {
        if(filters == null)
            filters = new FurnitureFilterTDO();
        filters.setWarrantyFrom(warrantyFrom);
        filters.setWarrantyTo(warrantyTo);
        filters.setisWarranty(true);
    }

    public static void setCategoryFilter(int categoryId)
    {
        if(filters == null)
            filters = new FurnitureFilterTDO();
        filters.setCategory(categoryId);
        filters.setisCategory(true);
    }
    
    public static void setNameFilter(String name)
    {
        if(filters == null)
            filters = new FurnitureFilterTDO();
       filters.setName(name);
       filters.setisName(true);
    }

    public static void setFilters(FurnitureFilterTDO filter)
    {
        filters = filter;
    }
}
