package gui.controller;

import java.io.IOException;
import java.util.List;

import controllers.FurnitureController;
import gui.GUI;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import request.tdo.FurnitureCategoryTDO;
import request.tdo.FurnitureTDO;

public class AssistantFurnitureSceneController {
    
    @FXML private TableView<FurnitureTDO> mainFurnitureTableView;

    @FXML private TableColumn<FurnitureTDO, String> furnitureCategoryColumn;
    @FXML private TableColumn<FurnitureTDO, String> furniturenNameColumn;
    @FXML private TableColumn<FurnitureTDO, String> furniturenManufactorerColumn;

    @FXML private TableColumn<FurnitureTDO, Integer> furniturenWarrantyColumn;
    @FXML private TableColumn<FurnitureTDO, Float> furniturenPriceColumn;
    @FXML private TableColumn<FurnitureTDO, Float> furniturenFirscostColumn;
    @FXML private TableColumn<FurnitureTDO, Integer> furniturenStogeAmountColumn;

    @FXML private AnchorPane furnitureDescriptionPane;

    @FXML private List<FurnitureCategoryTDO> furnitureCategoryList;
    
    @FXML private TextField furnitureDescriptionName;
    @FXML private ChoiceBox<FurnitureCategoryTDO> categoryChoiceBox;
    @FXML private TextField furnitureDescriptionManufactorer,
                            furnitureDescriptionWarranty,
                            furnitureDescriptionPrice,
                            furnitureDescriptionFirstcost,
                            furnitureDescriptionAmount;
    @FXML private TextArea furnitureDescriptionArea;


    @FXML private Button addButton, deleteCancelButton, applyAddButton;
    @FXML private Text descriptionAddText;

    @FXML
    public void initialize()
    {
        furnitureCategoryColumn.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, String>("furniturecategory"));
        furniturenNameColumn.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, String>("name"));
        furniturenManufactorerColumn.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, String>("manufactorer"));
        furniturenWarrantyColumn.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, Integer>("warranty"));
        furniturenPriceColumn.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, Float>("price"));
        furniturenFirscostColumn.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, Float>("firstcost"));
        furniturenStogeAmountColumn.setCellValueFactory(new PropertyValueFactory<FurnitureTDO, Integer>("amount"));

        mainFurnitureTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                swapToChangeMode();
                SetFurnitureDescription(mainFurnitureTableView.getSelectionModel().getSelectedItem());
            }
        });

        RefreshFurnitureTable();
    }


    @FXML
    private void RefreshFurnitureTable()
    {
        furnitureDescriptionPane.setVisible(false);
        swapToChangeMode();
        refreshCategoryList();
        List<FurnitureTDO> furnitureList = FurnitureController.getFurnitureList(FurnitureCommands.GET_FURNITURE_LIST_ASSISTANT);
        mainFurnitureTableView.getItems().clear();
        if(furnitureList == null)
        {
            return;
        }
        mainFurnitureTableView.setItems(FXCollections.observableArrayList(furnitureList));  
    }

    @FXML
    private void applyFurnitureChanges()
    {
        if(mainFurnitureTableView.getSelectionModel().getSelectedItem() == null)
            return;
        FurnitureTDO newFurnitureTDO = fillFurnitureTDO(mainFurnitureTableView.getSelectionModel().getSelectedItem());
        if(newFurnitureTDO == null)
            return;
        FurnitureController.changeFurnitureData(newFurnitureTDO);

        RefreshFurnitureTable();
    }

    @FXML
    private void buttonAddFurniture()
    {
        swapToAddMode();

        refreshCategoryList();
        furnitureDescriptionName.clear();
        categoryChoiceBox.setValue(null);
        furnitureDescriptionManufactorer.clear();
        furnitureDescriptionArea.clear();
        furnitureDescriptionWarranty.clear();
        furnitureDescriptionPrice.clear();
        furnitureDescriptionFirstcost.clear();
        furnitureDescriptionAmount.clear();

        furnitureDescriptionPane.setVisible(true);
    }
    
    private void swapToAddMode()
    {
        addButton.setVisible(false);

        deleteCancelButton.setText("Отменить");
        applyAddButton.setText("Добавить товар");
        descriptionAddText.setText("Добавление");
        
        deleteCancelButton.setOnAction(action -> {
            RefreshFurnitureTable();
        });

        applyAddButton.setOnAction(action ->{
            FurnitureTDO furnitureTDo = new FurnitureTDO();
            furnitureTDo = fillFurnitureTDO(furnitureTDo);
            if(furnitureTDo == null)
                return;
            FurnitureController.addFurniture(furnitureTDo);
            RefreshFurnitureTable();
        });

    }


    private void swapToChangeMode()
    {
        addButton.setVisible(true);
        deleteCancelButton.setText("Удалить товар");
        applyAddButton.setText("Применить изменения");
        descriptionAddText.setText("Описание");
        deleteCancelButton.setOnAction(action -> {
                deleteFurniture();
        });
        applyAddButton.setOnAction(action ->{
                applyFurnitureChanges();
        });
    }

    private FurnitureTDO fillFurnitureTDO(FurnitureTDO furnitureTDO)
    {
        int warranty, amount;
        float price, firstcost;
        try {
            warranty = Integer.parseInt(furnitureDescriptionWarranty.getText().strip());
            price = Float.parseFloat(furnitureDescriptionPrice.getText().strip());
            firstcost = Float.parseFloat(furnitureDescriptionFirstcost.getText().strip());
            amount = Integer.parseInt(furnitureDescriptionAmount.getText().strip());

            if(warranty < 0 || price < 0 || firstcost < 0 || amount < 0)
                throw new NumberFormatException();

            if(furnitureDescriptionName.getText().isBlank() ||
                categoryChoiceBox.getValue() == null ||
                furnitureDescriptionManufactorer.getText().isBlank())
                throw new IOException();

        } catch (NumberFormatException | IOException e) {
            e.getStackTrace();
            return null;
        }
        furnitureTDO.setName(furnitureDescriptionName.getText().strip());
        furnitureTDO.setFurniturecategory(categoryChoiceBox.getValue().getName());
        furnitureTDO.setManufactorer(furnitureDescriptionManufactorer.getText().strip());
        furnitureTDO.setDescription(furnitureDescriptionArea.getText().strip());
        furnitureTDO.setWarranty(warranty);
        furnitureTDO.setPrice(price);        
        furnitureTDO.setFirstcost(firstcost);
        furnitureTDO.setAmount(amount);
        return furnitureTDO;
    }

    @FXML
    private void deleteFurniture()
    {
        if(mainFurnitureTableView.getSelectionModel().getSelectedItem() == null)
            return;
        FurnitureController.deleteFurniture(mainFurnitureTableView.getSelectionModel().getSelectedItem());
        RefreshFurnitureTable();
    }

    private void SetFurnitureDescription(FurnitureTDO furnitureTDO)
    {
        if(furnitureTDO == null)
            return;
        furnitureDescriptionName.setText(furnitureTDO.getName());
        FurnitureCategoryTDO furnitureCategory = null;
        for (FurnitureCategoryTDO furnitureCategoryTDO : furnitureCategoryList) {
            if(furnitureCategoryTDO.getName().equals(furnitureTDO.getFurniturecategory()))
            {
                furnitureCategory = furnitureCategoryTDO;
                break;
            }
        }
        categoryChoiceBox.setValue(furnitureCategory);

        furnitureDescriptionManufactorer.setText(furnitureTDO.getManufactorer());
        furnitureDescriptionArea.setText(furnitureTDO.getDescription());
        furnitureDescriptionWarranty.setText(String.valueOf(furnitureTDO.getWarranty()));
        furnitureDescriptionPrice.setText(String.valueOf(furnitureTDO.getPrice()));
        furnitureDescriptionFirstcost.setText(String.valueOf(furnitureTDO.getFirstcost()));
        furnitureDescriptionAmount.setText(String.valueOf(furnitureTDO.getAmount()));
        furnitureDescriptionPane.setVisible(true);
    }

    private void refreshCategoryList()
    {
        furnitureCategoryList = FurnitureController.getFurnitureCategoryList(FurnitureCommands.GET_FURNITURE_CATEGORY_ASSISTANT);
        categoryChoiceBox.getItems().clear();
        categoryChoiceBox.getItems().addAll(furnitureCategoryList);
    }
    @FXML
    private void exitToAssistantStartScene() throws IOException
    {
        GUI.setRoot("assistantstartscene");
    }
}
