package gui.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import controllers.OrderController;
import gui.GUI;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import request.commands.OrderCommands;
import request.tdo.OrderElementTDO;
import request.tdo.UserOrderTDO;

public class UserOrderSceneController {

    @FXML protected TableView<UserOrderTDO> activeOrderTableView;

    @FXML protected TableColumn<UserOrderTDO, Integer> orderNumberColumn;
    @FXML protected TableColumn<UserOrderTDO, Date> orderDateColumn;
    @FXML protected TableColumn<UserOrderTDO, Float> orderPriceColumn;

    @FXML protected TableView<OrderElementTDO> orderElementsTableView;

    @FXML protected TableColumn<OrderElementTDO, String> orderElementNameColumn;
    @FXML protected TableColumn<OrderElementTDO, Float> orderElementPriceColumn;
    @FXML protected TableColumn<OrderElementTDO, Integer> orderElementAmountColumn;

    @FXML protected AnchorPane orderDescriptionPane;

    @FXML
    public void initialize()
    {  
        activeOrderTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                GetOrderDescription(activeOrderTableView.getSelectionModel().getSelectedItem());
            }
        });
        setStartInitialization();
        RefreshOrderTable();

    }

    protected void setStartInitialization()
    {
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<UserOrderTDO, Integer>("id"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<UserOrderTDO, Date>("orderdate"));
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<UserOrderTDO, Float>("orderprice"));

        orderElementNameColumn.setCellValueFactory(new PropertyValueFactory<OrderElementTDO, String>("furniture"));
        orderElementPriceColumn.setCellValueFactory(new PropertyValueFactory<OrderElementTDO, Float>("furnitureprice"));
        orderElementAmountColumn.setCellValueFactory(new PropertyValueFactory<OrderElementTDO, Integer>("amount"));

        orderDescriptionPane.setVisible(false);
    }

    protected void GetOrderDescription(UserOrderTDO userOrder)
    {
        if(userOrder == null)
            return;
        orderElementsTableView.setItems(FXCollections.observableArrayList(userOrder.getOrderelementlist()));  
        orderDescriptionPane.setVisible(true);
    }


    private void RefreshOrderTable()
    {   
        orderDescriptionPane.setVisible(false);
        List<UserOrderTDO> userOrderList = OrderController.getOrderList(OrderCommands.GET_USER_ORER_LIST);
        activeOrderTableView.getItems().clear();
        if(userOrderList == null)
        {
            return;
        }
        activeOrderTableView.setItems(FXCollections.observableArrayList(userOrderList));  
    }

    @FXML
    private void deleteUserOrder()
    {
        if(activeOrderTableView.getItems().isEmpty() || activeOrderTableView.getSelectionModel().getSelectedItem() == null)
        {
            return;
        }
        OrderController.deleteOrder(activeOrderTableView.getSelectionModel().getSelectedItem());
        RefreshOrderTable();
    }

    @FXML
    private void exitToUserStartScene() throws IOException
    {
        GUI.setRoot("userstartscene");
    }

    @FXML
    private void exitToServicedOrderScene() throws IOException
    {
        GUI.setRoot("userservicedorderscene");
    }
}
