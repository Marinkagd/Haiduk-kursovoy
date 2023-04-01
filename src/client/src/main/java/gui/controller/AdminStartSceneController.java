package gui.controller;

import java.io.IOException;
import java.util.List;

import controllers.AssistantController;
import controllers.AuthorisationController;
import controllers.UserController;
import gui.GUI;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import request.commands.AdminCommands;
import request.controller.BaseRequestController;
import request.tdo.AdminTDO;
import request.tdo.AssistantTDO;
import request.tdo.UserTDO;

@SuppressWarnings("unused")
public class AdminStartSceneController {
    private static AdminTDO admininformation;
    public static void setAdmininformation(AdminTDO admininf) {
        AdminStartSceneController.admininformation = admininf;
    }

    @FXML private TableView<UserTDO> userTableView;
    @FXML private TableColumn<UserTDO, String> userLoginColumn, userFirstnameColumn, userSecondnameColumn,
                                                userPhonenumberColumn, userEmailColumn,userAddressColumn;

    @FXML private TableView<AssistantTDO> assistantTableView;
    @FXML private TableColumn<AssistantTDO, String> assistantLoginColumn, assistantFirstnameColumn, assistantSecondnameColumn;

    @FXML private Button userDeleteButton, assistantDeleteButton;

    @FXML private TextField assistantLoginField, assistantFirstnameField, assistantSecondnameField;
    @FXML private PasswordField assistantPasswordField;

    @FXML
    public void initialize()
    {
        userLoginColumn.setCellValueFactory(new PropertyValueFactory<UserTDO, String>("id"));
        userFirstnameColumn.setCellValueFactory(new PropertyValueFactory<UserTDO, String>("firstname"));
        userSecondnameColumn.setCellValueFactory(new PropertyValueFactory<UserTDO, String>("secondname"));
        userPhonenumberColumn.setCellValueFactory(new PropertyValueFactory<UserTDO, String>("phonenumber"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<UserTDO, String>("email"));
        userAddressColumn.setCellValueFactory(new PropertyValueFactory<UserTDO, String>("address"));

        assistantLoginColumn.setCellValueFactory(new PropertyValueFactory<AssistantTDO, String>("id"));
        assistantFirstnameColumn.setCellValueFactory(new PropertyValueFactory<AssistantTDO, String>("firstname"));
        assistantSecondnameColumn.setCellValueFactory(new PropertyValueFactory<AssistantTDO, String>("secondname"));
        
        userDeleteButton.setVisible(false);
        assistantDeleteButton.setVisible(false);

        userTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvene) {
               if(userTableView.getSelectionModel().getSelectedItem() != null)
                userDeleteButton.setVisible(true);
            }
            
        });

        assistantTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvene) {
               if(assistantTableView.getSelectionModel().getSelectedItem() != null)
                assistantDeleteButton.setVisible(true);
            }
            
        });

        refreshUserTable();
        refreshAssistantTable();
    }

    @FXML
    private void refreshUserTable()
    {
        userDeleteButton.setVisible(false);
        List<UserTDO> userList = UserController.getUserList();
        userTableView.getItems().clear();
        if(userList == null)
        {
            return;
        }
        userTableView.setItems(FXCollections.observableArrayList(userList));  
    }

    @FXML
    private void refreshAssistantTable()
    {
        assistantDeleteButton.setVisible(false);
        List<AssistantTDO> assistantList = AssistantController.getAssistantList();
        assistantTableView.getItems().clear();
        if(assistantList == null)
        {
            return;
        }
        assistantTableView.setItems(FXCollections.observableArrayList(assistantList));  
    }


    @FXML
    private void deleteUser()
    {
        if(userTableView.getSelectionModel().getSelectedItem() == null)
            return;
        UserController.deleteUser(userTableView.getSelectionModel().getSelectedItem());
        refreshUserTable();
    }

    @FXML
    private void deleteAssistant()
    {
        if(assistantTableView.getSelectionModel().getSelectedItem() == null)
            return;
        AssistantController.deleteAssistant(assistantTableView.getSelectionModel().getSelectedItem());
        refreshAssistantTable();
    }

    @FXML
    private void addAssistant()
    {
        if(assistantLoginField.getText().isBlank() ||
            assistantPasswordField.getText().isEmpty() ||
            assistantFirstnameField.getText().isBlank() ||
            assistantSecondnameField.getText().isBlank())
        return;

        AssistantTDO newAssistant = new AssistantTDO();
        newAssistant.setId(assistantLoginField.getText().strip());
        newAssistant.setPassword(AuthorisationController.encrypt(assistantPasswordField.getText()));
        newAssistant.setFirstname(assistantFirstnameField.getText().strip());
        newAssistant.setSecondname(assistantSecondnameField.getText().strip());
        AssistantController.addAssistant(newAssistant);

        assistantLoginField.clear();
        assistantPasswordField.clear();
        assistantFirstnameField.clear();
        assistantSecondnameField.clear();
        refreshAssistantTable();
    }

    @FXML
    private void exitToAdminFinanceScene() throws IOException
    {
        GUI.setRoot("adminfinancescene");
    }

    @FXML
    private void exitToStartScene() throws IOException
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.EXIT);
        GUI.setRoot("startscene");
    }

}
