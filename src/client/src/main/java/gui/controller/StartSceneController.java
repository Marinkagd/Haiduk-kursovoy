package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.tdo.AdminTDO;
import request.tdo.AssistantTDO;
import request.tdo.UserTDO;

import java.io.IOException;

import controllers.AuthorisationController;
import gui.GUI;

public class StartSceneController {
    @FXML TextField login;
    @FXML PasswordField password;
    @FXML RadioButton userType, assistantType, adminType;
    @FXML Text warningText;

    @FXML
    private void SwitchToRegistrationScene() throws IOException
    {
        GUI.setRoot("registrationscene");
    }

    @FXML
    private void Authorisation() throws IOException
    {
        if(login.getText().isBlank() || password.getText().isEmpty())
        {
            warningText.setText("Заполните оба поля!");
            warningText.setVisible(true);
            return;
        }

        if(userType.isSelected())
        {
            var userinformation = authorisationLike(UserTDO.class);
            if(userinformation != null)
            {
                UserInformationSceneController.setUserinformation(userinformation);
                System.out.println("заходим как пользователь");
                GUI.setRoot("userstartscene");
            }
        }
        else if(assistantType.isSelected())
        {
            var assistantinformation = authorisationLike(AssistantTDO.class);
            if(assistantinformation != null)
            {
                AssistantStartSceneController.setAssistantinformation(assistantinformation);
                System.out.println("заходим как ассистент");
                GUI.setRoot("assistantstartscene");
            }
        }
        else
        {
            AdminTDO admininformation = authorisationLike(AdminTDO.class);
            if(admininformation != null)
            {
                AdminStartSceneController.setAdmininformation(admininformation);
                System.out.println("заходим как админ");
                GUI.setRoot("adminstartscene");
            }
        }
        
    }

    private <T> T authorisationLike(Class<T> accountType)
    {
        T account = null;
        account = AuthorisationController.authorisation(accountType, login.getText().strip(), password.getText());
        if(account == null)
        {
            warningText.setText("Аккаунта не существует или проверьте правильность введённых данных!");
            warningText.setVisible(true);
        }
        return account;
    }
}
