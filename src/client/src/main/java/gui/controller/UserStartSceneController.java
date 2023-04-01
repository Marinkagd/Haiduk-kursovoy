package gui.controller;

import java.io.IOException;
import java.util.List;

import controllers.CartController;
import controllers.FurnitureController;
import gui.GUI;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import request.commands.FurnitureCommands;
import request.commands.UserCommands;
import request.controller.BaseRequestController;
import request.tdo.FurnitureCategoryTDO;
import request.tdo.FurnitureTDO;

public class UserStartSceneController {

    @FXML private TableView<FurnitureTDO> mainFurnitureViewTable;
    @FXML private TableColumn<FurnitureTDO, String> furnitureColumnName;
    @FXML private TableColumn<FurnitureTDO, String> furnitureColumnManufactorer;
    @FXML private TableColumn<FurnitureTDO, Double> furnitureColumnPrice;

    @FXML private TextField filterPriceFrom, filterPriceTo, filterWarrantyFrom, filterWarrantyTo, filterName;
    @FXML private ChoiceBox<FurnitureCategoryTDO> filterCategory; 

    @FXML private Text filterWarning;

    @FXML private Text furnitureNameText, furnitureManufactorerText, furnitureWarrantyText, furniturePriceText;
    @FXML private TextArea furnitureDescriptionArea;

    @FXML private AnchorPane furnitureFullDescription;

    private List<FurnitureCategoryTDO> categoryList;

    @FXML private Text alreadyInCart;

    @FXML private void initialize()
    {
        furnitureColumnName.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, String>("name"));
        furnitureColumnManufactorer.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, String>("manufactorer"));
        furnitureColumnPrice.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, Double>("price"));

        mainFurnitureViewTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                SetDescription(mainFurnitureViewTable.getSelectionModel().getSelectedItem());
            }
        });
      
        setCategoryList();
        freeFilters();
    }

    @FXML private void addCartElement()
    {
        boolean addResult = false;
        if(mainFurnitureViewTable.getSelectionModel().getSelectedItem() != null){
            addResult = CartController.addCartElement(mainFurnitureViewTable.getSelectionModel().getSelectedItem());
        }
        if(addResult)
            alreadyInCart.setText("Успешно добавлено!");
        else
            alreadyInCart.setText("Товар уже в корзине!");
        alreadyInCart.setVisible(true);
    }

    private void RefreshFurnitureTable()
    {
        furnitureFullDescription.setVisible(false);
        List<FurnitureTDO> furnitureList = FurnitureController.getFurnitureList(FurnitureCommands.GET_FURNITURE_LIST_USER);
        mainFurnitureViewTable.getItems().clear();
        if(furnitureList == null)
        {
            return;
        }
        mainFurnitureViewTable.setItems(FXCollections.observableArrayList(furnitureList));  
    }

    private void setCategoryList()
    {
        categoryList = FurnitureController.getFurnitureCategoryList(FurnitureCommands.GET_FURNITURE_CATEGORY_USER);
        filterCategory.getItems().clear();
        filterCategory.getItems().addAll(categoryList);
    }

    private void SetDescription(FurnitureTDO furnituretdo)
    {
        if(furnituretdo != null)
        {
            furnitureNameText.setText(furnituretdo.getName());
            furnitureManufactorerText.setText(furnituretdo.getManufactorer());
            furnitureWarrantyText.setText(String.valueOf(furnituretdo.getWarranty()) + " лет");
            furniturePriceText.setText(String.valueOf(furnituretdo.getPrice()) + "р.");
            furnitureDescriptionArea.setText(furnituretdo.getDescription());
            furnitureFullDescription.setVisible(true);
            alreadyInCart.setVisible(false);
        }   
    }

    @FXML
    private void applyFilters()
    {
        filterWarning.setVisible(false);
        FurnitureController.setFilters(null);
        if((!filterPriceFrom.getText().isBlank() && filterPriceTo.getText().isBlank()
            || filterPriceFrom.getText().isBlank() && !filterPriceTo.getText().isBlank()) ||
        (!filterWarrantyFrom.getText().isBlank() && filterWarrantyTo.getText().isBlank()
        || filterWarrantyFrom.getText().isBlank() && !filterWarrantyTo.getText().isBlank()))
        {
            filterWarning.setText("Неверные фильры!");
            filterWarning.setVisible(true);
            return;
        }
        if(!filterPriceFrom.getText().isBlank() && !filterPriceTo.getText().isBlank())
        {
            try {
                float priceFrom = Float.parseFloat(filterPriceFrom.getText());
                float priceTo = Float.parseFloat(filterPriceTo.getText());
                if(priceFrom >= priceTo || priceFrom < 0 || priceTo < 0)
                    throw new NumberFormatException();
                FurnitureController.setPriceFilter(priceFrom, priceTo);
            } catch (NumberFormatException  e) {
                filterWarning.setText("Неверные фильры!");
                filterWarning.setVisible(true);
                return;
            }
        }
        if(!filterName.getText().isBlank())
            FurnitureController.setNameFilter(filterName.getText());
        if(!filterWarrantyFrom.getText().isBlank() && !filterWarrantyTo.getText().isBlank())
        {
            try {
                int warrantyFrom = Integer.parseInt(filterWarrantyFrom.getText());
                int warrantyTo = Integer.parseInt(filterWarrantyTo.getText());
                if(warrantyFrom >= warrantyTo || warrantyFrom < 0 || warrantyTo < 0)
                    throw new NumberFormatException();
                FurnitureController.setWarrantyFilter(warrantyFrom, warrantyTo);
            } catch (NumberFormatException  e) {
                filterWarning.setText("Неверные фильры!");
                filterWarning.setVisible(true);
                return;
            }
        }
        if(filterCategory.getSelectionModel().getSelectedItem() != null)
            FurnitureController.setCategoryFilter(filterCategory.getSelectionModel().getSelectedItem().getId());
        RefreshFurnitureTable();
    }

    @FXML
    private void freeFilters()
    {
        FurnitureController.setFilters(null);
        filterWarning.setVisible(false);
        filterCategory.getSelectionModel().clearSelection();
        filterName.clear();
        filterPriceFrom.clear();
        filterPriceTo.clear();
        filterWarrantyFrom.clear();
        filterWarrantyTo.clear();
        RefreshFurnitureTable();
    }

    @FXML
    private void exitToStartScene() throws IOException
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.EXIT);
        GUI.setRoot("startscene");
    }

    @FXML
    private void exitToOrderScene() throws IOException
    {
        GUI.setRoot("userorderscene");
    }
    
    @FXML
    private void exitToUserInformation() throws IOException
    {
        GUI.setRoot("userinformationscene");
    }

    @FXML
    private void exitToCartScene() throws IOException
    {
        GUI.setRoot("usercartscene");
    }
}
