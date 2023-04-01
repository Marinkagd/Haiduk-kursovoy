package gui.controller;

import java.io.IOException;

import controllers.RegistrationController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.commands.ConfirmCommands;
import gui.GUI;

public class RegistrationSceneController {

    @FXML TextField login, firstname, secondname, phonenumber, email, address;
    @FXML Text warning;
    @FXML PasswordField password;
    
    @FXML
    private void SwitchToStartScene() throws IOException
    {
        GUI.setRoot("startscene");
    }

    @FXML
    private void Registration() throws IOException
    {
        if(login.getText().isBlank() ||
            password.getText().isEmpty() ||
            firstname.getText().isBlank() ||
            secondname.getText().isBlank() ||
            email.getText().isBlank())
        {
            warning.setText("Заполните обязательные поля!");
            warning.setVisible(true);
            return;
        }

        ConfirmCommands result;
        result = RegistrationController.registration(login.getText().strip(), password.getText(), firstname.getText().strip(),
                secondname.getText().strip(), phonenumber.getText().strip(), email.getText().strip(), address.getText().strip());
        switch (result) {
            case SUCCESSFULLY:
                SwitchToStartScene();
                break;
            case FAILED : case SOMETHINGWRONG: 
            {
                warning.setText("Уже существует аккаунт с таким именем!");
                warning.setVisible(true);
                return;
            }
        }

    }
}
