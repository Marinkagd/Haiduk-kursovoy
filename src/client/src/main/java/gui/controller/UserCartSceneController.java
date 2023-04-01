package gui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controllers.CartController;
import controllers.OrderController;
import gui.GUI;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import request.tdo.CartElementTDO;

public class UserCartSceneController {
    
    @FXML private TableView<CartElementTDO> cartelementsTable;
    
    @FXML private TableColumn<CartElementTDO, String> cartTableName;
    @FXML private TableColumn<CartElementTDO, Integer> cartTableAmount;

    @FXML private Text furnitureNameText, furnitureManufactorerText,
                        furnitureWarrantyText, furniturePriceText, furnitureAmountText;
    @FXML private TextArea furnitureDescriptionArea;

    @FXML private AnchorPane furnitureFullDescription;

    @FXML private TextField fieldAmountToChange;
    @FXML private Text amountCheckWarning, cartelementlistEmptyWarning;

    @FXML
    public void initialize(){
        cartTableName.setCellValueFactory(new PropertyValueFactory<>("furniture"));
        cartTableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        cartelementsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                SetDescription(cartelementsTable.getSelectionModel().getSelectedItem());
            }
        });
        ResreshCartElementsTable();
    }

    @FXML
    private void deleteFromCart()
    {
        if(cartelementsTable.getSelectionModel().getSelectedItem() != null)
        {
            CartController.deleteFromCart(cartelementsTable.getSelectionModel().getSelectedItem());
            ResreshCartElementsTable();
        }
    }

    @FXML
    private void clearCart()
    {
        if(cartelementsTable.getItems().isEmpty())
            return;
        CartController.clearCart();
        ResreshCartElementsTable();
    }

    private void SetDescription(CartElementTDO selectedCartElement)
    {
        if(selectedCartElement != null)
        {
            furnitureNameText.setText(selectedCartElement.getFurniture().getName());
            furnitureManufactorerText.setText(selectedCartElement.getFurniture().getManufactorer());
            furnitureWarrantyText.setText(String.valueOf(selectedCartElement.getFurniture().getWarranty()) + " лет");
            furniturePriceText.setText(String.valueOf(selectedCartElement.getFurniture().getPrice()) + "р.");
            furnitureDescriptionArea.setText(selectedCartElement.getFurniture().getDescription());
            furnitureAmountText.setText(String.valueOf(selectedCartElement.getAmount()));
            fieldAmountToChange.setText(String.valueOf(selectedCartElement.getAmount()));
            amountCheckWarning.setVisible(false);
            furnitureFullDescription.setVisible(true);
        }
    }

    @FXML
    private void changeCartElementAmount()
    {
        if(cartelementsTable.getSelectionModel().getSelectedItem() != null)
        {
            try {
                int newamount = Integer.parseInt(fieldAmountToChange.getText());
                if(newamount < 1 || newamount > 999)
                    throw new NumberFormatException();
                cartelementsTable.getSelectionModel().getSelectedItem().setAmount(newamount);
                CartController.changeCartElementAmount(cartelementsTable.getSelectionModel().getSelectedItem());
            } catch (NumberFormatException e) {
                amountCheckWarning.setVisible(true);
                return;
            }
            ResreshCartElementsTable();
        }
    }
    private void ResreshCartElementsTable()
    {
        furnitureFullDescription.setVisible(false);
        List<CartElementTDO> cartelementsList = CartController.getCartElementsList();
        cartelementsTable.getItems().clear();
        if(cartelementsList == null)
        {
            return;
        }
        cartelementsTable.setItems(FXCollections.observableArrayList(cartelementsList));  
    }

    @FXML
    private void createOrder()
    {
        if(cartelementsTable.getItems().isEmpty())
        {
            cartelementlistEmptyWarning.setText("Корзина пуста!");
            cartelementlistEmptyWarning.setVisible(true);
            return;
        }
        var cartElementList = new ArrayList<CartElementTDO>();
        cartelementsTable.getItems().forEach(cartElementList::add);
        
        OrderController.createOrder(cartElementList);
        cartelementlistEmptyWarning.setText("Заказ успешен!");
        cartelementlistEmptyWarning.setVisible(true);
    }

    @FXML
    private void exitToUserStartScene() throws IOException{
        GUI.setRoot("userstartscene");
    }
}
