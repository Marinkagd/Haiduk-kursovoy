package com.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import db.dao.FurnitureCategoryDao;
import db.entity.FurnitureCategory;

public class DBGenericDAOTest {

    private FurnitureCategory furnitureCategory;

    @Before
    public void beforeFurnitureInitization()
    {
        furnitureCategory = new FurnitureCategory();
        furnitureCategory.setName("test");
    }


    @Test
    public void whenAddAndRemoveToDBFurnitureCategoryThenReturnTrue()
    {
       var furnitureCategoryDao = new FurnitureCategoryDao(FurnitureCategory.class);
       var addResult = furnitureCategoryDao.save(furnitureCategory);
       var deleteResult = furnitureCategoryDao.remove(furnitureCategoryDao.getFurnitureCategoryByName("test"));
       assertTrue(addResult);
       assertTrue(deleteResult);
    }

    @Test
    public void whenUpdateThenReturnTrue()
    {
        var furnitureCategoryDao = new FurnitureCategoryDao(FurnitureCategory.class);
        furnitureCategoryDao.save(furnitureCategory);

        var updatedFurnitureCategory = furnitureCategoryDao.getFurnitureCategoryByName("test");
        updatedFurnitureCategory.setName("changedTest!");

        furnitureCategoryDao.update(updatedFurnitureCategory);

        var updatedName = furnitureCategoryDao.getFurnitureCategoryByName("changedTest!").getName();
        furnitureCategoryDao.remove(updatedFurnitureCategory);
        assertEquals(updatedName, "changedTest!");
    }
    
}
