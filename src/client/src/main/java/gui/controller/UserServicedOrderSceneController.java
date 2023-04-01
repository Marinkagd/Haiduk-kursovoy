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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import request.tdo.FurnitureTDO;
import request.tdo.ServicedOrderElementTDO;
import request.tdo.ServicedOrderTDO;

public class UserServicedOrderSceneController {
    
    @FXML private TableView<ServicedOrderTDO> servicedOrderTableView;

    @FXML private TableColumn<ServicedOrderTDO, Integer> orderNumberColumn;
    @FXML private TableColumn<ServicedOrderTDO, String> orderAssistantColumn;
    @FXML private TableColumn<ServicedOrderTDO, String> orderAcceptedColumn;
    @FXML private TableColumn<ServicedOrderTDO, Date> orderDateAnswerColumn;
    @FXML private TableColumn<ServicedOrderTDO, Date> orderDateOrderColumn;
    @FXML private TableColumn<ServicedOrderTDO, Float> orderPriceColumn;

    @FXML private TableView<ServicedOrderElementTDO> servicedOrderElementsTableView;

    @FXML private TableColumn<ServicedOrderElementTDO, FurnitureTDO> orderElementNameColumn;
    @FXML private TableColumn<ServicedOrderElementTDO, Float> orderElementPriceColumn;
    @FXML private TableColumn<ServicedOrderElementTDO, Integer> orderElementAmountColumn;
    @FXML private TextArea orderDescriptionArea;

    @FXML private AnchorPane servicedOrderDescriptionPane;

    @FXML
    private void initialize()
    {
        servicedOrderTableInitialization();
        servicedOrderElementTableInitialization();
        servicedOrderDescriptionPane.setVisible(false);
        servicedOrderTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
               SetServicedOrderDescription(servicedOrderTableView.getSelectionModel().getSelectedItem());
            }
            
        });
        RefreshServicedOrderTable();
    }

    private void RefreshServicedOrderTable()
    {
        servicedOrderDescriptionPane.setVisible(false);
        List<ServicedOrderTDO> servicedOrderList = OrderController.getServicedOrderList();
        servicedOrderTableView.getItems().clear();
        if(servicedOrderList == null)
        {
            return;
        }
        servicedOrderTableView.setItems(FXCollections.observableArrayList(servicedOrderList));  
    }

    private void SetServicedOrderDescription(ServicedOrderTDO servicedOrderTDO)
    {
        if(servicedOrderTDO == null)
            return;
        servicedOrderElementsTableView.getItems().clear();
        servicedOrderElementsTableView.getItems().setAll(servicedOrderTDO.getOrderelementlist());
        orderDescriptionArea.setText(servicedOrderTDO.getDescription());
        servicedOrderDescriptionPane.setVisible(true);
    }

    protected void servicedOrderTableInitialization()
    {
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderTDO, Integer>("id"));
        orderAssistantColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderTDO, String>("assistantFIO"));
        orderAcceptedColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderTDO, String>("isacceptedString"));
        orderDateAnswerColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderTDO, Date>("answerdate"));
        orderDateOrderColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderTDO, Date>("orderdate"));
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderTDO, Float>("orderprice"));
    }

    private void servicedOrderElementTableInitialization()
    {
        orderElementNameColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderElementTDO, FurnitureTDO>("furniture"));
        orderElementPriceColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderElementTDO, Float>("furnitureprice"));
        orderElementAmountColumn.setCellValueFactory(new PropertyValueFactory<ServicedOrderElementTDO, Integer>("amount"));
    }

    @FXML
    private void exitToUserOrderScene() throws IOException
    {
        GUI.setRoot("userorderscene");
    }
}
