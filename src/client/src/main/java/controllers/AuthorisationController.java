package controllers;

import java.security.MessageDigest;

import request.commands.ConfirmCommands;
import request.commands.StartMenuCommands;
import request.controller.BaseRequestController;
import request.tdo.AssistantTDO;
import request.tdo.AuthorisationTDO;
import request.tdo.UserTDO;

public class AuthorisationController {
    
    @SuppressWarnings("unchecked")
    public static <T> T authorisation(Class<T> clientType, String login, String password)
    {
        StartMenuCommands authorisationType;
        if(clientType == UserTDO.class)
            authorisationType = StartMenuCommands.USER_AUTHORISATION;
        else if (clientType == AssistantTDO.class)
            authorisationType = StartMenuCommands.ASSISTANT_AUTHORISATION;
        else
            authorisationType = StartMenuCommands.ADMIN_AUTHORISATION;
        
        BaseRequestController.sendRequest(StartMenuCommands.class, authorisationType);

        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY:
            {  
                AuthorisationTDO authorisationTDO = new AuthorisationTDO(login, encrypt(password));
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(authorisationTDO);
                    T clientTDO = (T)BaseRequestController.getObjectInputStream().readObject();
                    return clientTDO;
                } catch (Exception e) {
                    return null;
                }
            }
            case FAILED:
            {
                return null;
            }
            default:
                break;
        }
        return null;
    }

    public static byte[] encrypt(String passwordString) {
        MessageDigest digest = null;
        byte[] encryptedPassword = null;
        try {
            digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            encryptedPassword = digest.digest(passwordString.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

}
