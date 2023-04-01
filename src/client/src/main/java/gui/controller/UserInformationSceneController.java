package gui.controller;

import java.io.IOException;

import controllers.UserController;
import gui.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.tdo.UserTDO;

public class UserInformationSceneController {
    private static UserTDO userinformation;
    public static void setUserinformation(UserTDO userinformation) {
        UserInformationSceneController.userinformation = userinformation;
    }

    @FXML private TextField firstnameField, secondnameField, phonenumberField, emailField, addressField;
    @FXML private Text changeWarning;
    
    @FXML
    public void initialize(){
        firstnameField.setText(userinformation.getFirstname());
        secondnameField.setText(userinformation.getSecondname());
        phonenumberField.setText(userinformation.getPhonenumber());
        emailField.setText(userinformation.getEmail());
        addressField.setText(userinformation.getAddress());
    }
  
    


    @FXML
    private void exitToUserStartScene() throws IOException
    {
        GUI.setRoot("userstartscene");
    }
    
    @FXML
    private void changePersonalData()
    {
        if(firstnameField.getText().equals(userinformation.getFirstname())  &&
            secondnameField.getText().equals(userinformation.getSecondname()) &&
            phonenumberField.getText().equals(userinformation.getPhonenumber()) &&
            emailField.getText().equals(userinformation.getEmail())&&
            addressField.getText().equals(userinformation.getAddress())
        )
        {
            changeWarning.setText("Данные совпадают!");
            changeWarning.setVisible(true);
            return;
        }

        if(firstnameField.getText().isBlank() ||
            secondnameField.getText().isBlank() ||
            phonenumberField.getText().isBlank() ||
            emailField.getText().isBlank() ||
            addressField.getText().isBlank())
        {
            changeWarning.setText("Данные введены некорректно!");
            changeWarning.setVisible(true);
            return;
        }
        
        userinformation.setFirstname(firstnameField.getText());
        userinformation.setSecondname(secondnameField.getText());
        userinformation.setPhonenumber(phonenumberField.getText());
        userinformation.setEmail(emailField.getText());
        userinformation.setAddress(addressField.getText());
        
        userinformation = UserController.changePersonalData(userinformation);
        changeWarning.setText("Данные  изменены!");
        changeWarning.setVisible(true);
    }
}
