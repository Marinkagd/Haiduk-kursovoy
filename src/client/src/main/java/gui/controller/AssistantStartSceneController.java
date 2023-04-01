package gui.controller;

import java.io.IOException;
import java.util.List;

import controllers.OrderController;
import gui.GUI;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import request.commands.AssistantCommands;
import request.commands.OrderCommands;
import request.controller.BaseRequestController;
import request.tdo.AssistantTDO;
import request.tdo.OrderAnswerTDO;
import request.tdo.OrderElementTDO;
import request.tdo.UserOrderTDO;

public class AssistantStartSceneController extends UserOrderSceneController{
    private static AssistantTDO assistantinformation;

    public static void setAssistantinformation(AssistantTDO assistantinformation) {
        AssistantStartSceneController.assistantinformation = assistantinformation;
    }

    @FXML private TableColumn<UserOrderTDO, String> orderUserColumn;
    @FXML private TableColumn<OrderElementTDO, Integer> orderElementStorageAmountColumn;
    @FXML private Text assistantNameText;

    @FXML private Button acceptOrderButton;
    @FXML private TextArea orderAnswerArea;

    @FXML
    public void initialize()
    {
        setStartInitialization();

        activeOrderTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                GetOrderDescriptionAndButtons();
            }
        });

        orderUserColumn.setCellValueFactory(new PropertyValueFactory<UserOrderTDO, String>("user"));
        orderElementStorageAmountColumn.setCellValueFactory(new PropertyValueFactory<OrderElementTDO, Integer>("furnitureStorageAmount"));
        assistantNameText.setText(assistantinformation.getSecondname() + " " + assistantinformation.getFirstname());
        RefreshOrderTable();
    }

    @FXML
    private void RefreshOrderTable()
    {
        orderDescriptionPane.setVisible(false);
        List<UserOrderTDO> userOrderList = OrderController.getOrderList(OrderCommands.GET_FULL_ORDER_LIST);
        activeOrderTableView.getItems().clear();
        if(userOrderList == null)
        {
            return;
        }
        activeOrderTableView.setItems(FXCollections.observableArrayList(userOrderList));  
    }

    private void GetOrderDescriptionAndButtons()
    {
        if(activeOrderTableView.getSelectionModel().getSelectedItem() == null)
            return;
        GetOrderDescription(activeOrderTableView.getSelectionModel().getSelectedItem());
        orderAnswerArea.clear();
        acceptOrderButton.setDisable(!orderIsAcceptable(activeOrderTableView.getSelectionModel().getSelectedItem()));
    }

    private boolean orderIsAcceptable(UserOrderTDO userOrder)
    {
        if(userOrder == null)
            return false;
        for (OrderElementTDO orderElement : userOrder.getOrderelementlist()) {
            if(orderElement.getAmount() > orderElement.getFurniture().getAmount())
                return false;
        }
        return true;
    }

    @FXML
    private void exitToStartScene() throws IOException
    {
        BaseRequestController.sendRequest(AssistantCommands.class, AssistantCommands.EXIT);
        GUI.setRoot("startscene");
    }

    @FXML
    private void exitToFurnitureScene() throws IOException
    {
        GUI.setRoot("assistantfurniturescene");
    }


    @FXML
    private void acceptOrder()
    {
       answerOrder(true);
    }


    @FXML
    private void defeatOrder()
    {
        answerOrder(false);
    }
  
    private void answerOrder(boolean answer)
    {
        if(activeOrderTableView.getSelectionModel().getSelectedItem() == null)
        return;
        OrderAnswerTDO orderAnswer = new OrderAnswerTDO(activeOrderTableView.getSelectionModel().getSelectedItem(),
                                                        answer,
                                                        orderAnswerArea.getText().strip()
                                                        );
        OrderController.answerToOrder(orderAnswer);
        RefreshOrderTable();
    }
   
}
