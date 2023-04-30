package controllers;

import java.util.ArrayList;
import java.util.List;

import db.dao.FurnitureCategoryDao;
import db.dao.FurnitureDao;
import db.dao.GenericDao;
import db.entity.Furniture;
import db.entity.FurnitureCategory;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import request.tdo.FurnitureCategoryTDO;
import request.tdo.FurnitureFilterTDO;
import request.tdo.FurnitureTDO;
import request.tdo.constructors.FurnitureTDOConstructor;

public class FurnitureController extends BaseRequestController {
    
    public FurnitureController(BaseRequestController contr) {
        super(contr);
    }

    public void sendFurnitureList()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var filters = (FurnitureFilterTDO)inputObjectStream.readObject();
            var furnituredao = new FurnitureDao(Furniture.class);
            var furniturelist = furnituredao.getFurnitureListByFiltes(filters);
            FurnitureTDOConstructor furnconstructor = new FurnitureTDOConstructor();
            List<FurnitureTDO> furnituretdolist = new ArrayList<>();
            for (Furniture furniture : furniturelist) {
                furnituretdolist.add(furnconstructor.furnituretdoconversion(furniture));
            }
            outputObjectStream.writeObject(furnituretdolist);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void sendFurnitureCategoryList()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var furnitureCategoryDao = new GenericDao<>(FurnitureCategory.class);

            var furnitureCategoryList = furnitureCategoryDao.getAll();
            FurnitureTDOConstructor furnConstructor = new FurnitureTDOConstructor();
            List<FurnitureCategoryTDO> furnitureTDOList = new ArrayList<>();
            for (FurnitureCategory furniturecategory : furnitureCategoryList) {
                furnitureTDOList.add(furnConstructor.furnituteCategotyTDOConversion(furniturecategory));
            }
            outputObjectStream.writeObject(furnitureTDOList);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void changeFurnitureData()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var changedFurnitureTDO = (FurnitureTDO)inputObjectStream.readObject();
            var furnitureCategoryDao = new FurnitureCategoryDao(FurnitureCategory.class);
            Furniture newFurniture = new Furniture(changedFurnitureTDO,
                    furnitureCategoryDao.getFurnitureCategoryByName(changedFurnitureTDO.getFurniturecategory()));
            var furnitureDao = new GenericDao<>(Furniture.class);
            furnitureDao.update(newFurniture);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void deleteFurniture()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var changedFurnitureTDO = (FurnitureTDO)inputObjectStream.readObject();
            var furnitureCategoryDao = new FurnitureCategoryDao(FurnitureCategory.class);
            Furniture newFurniture = new Furniture(changedFurnitureTDO,
                    furnitureCategoryDao.getFurnitureCategoryByName(changedFurnitureTDO.getFurniturecategory()));
            var furnitureDao = new GenericDao<>(Furniture.class);
            furnitureDao.remove(newFurniture);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addFurniture()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var changedFurnitureTDO = (FurnitureTDO)inputObjectStream.readObject();
            var furnitureCategoryDao = new FurnitureCategoryDao(FurnitureCategory.class);
            Furniture newFurniture = new Furniture(changedFurnitureTDO,
                    furnitureCategoryDao.getFurnitureCategoryByName(changedFurnitureTDO.getFurniturecategory()));
            var furnitureDao = new GenericDao<>(Furniture.class);
            furnitureDao.save(newFurniture);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
