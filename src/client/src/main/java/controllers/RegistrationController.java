package controllers;

import request.commands.ConfirmCommands;
import request.commands.StartMenuCommands;
import request.controller.BaseRequestController;
import request.tdo.UserTDO;

public class RegistrationController {
    
    public static ConfirmCommands registration(String login, String password, String firstname, String secondname,
                                    String phonenumber, String email, String address)
    {
        BaseRequestController.sendRequest(StartMenuCommands.class, StartMenuCommands.USER_REGISTRATION);

        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return ConfirmCommands.FAILED;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try
                {
                UserTDO userreginformation = new UserTDO(login, AuthorisationController.encrypt(password), firstname,
                                                        secondname, phonenumber, email, address);
                BaseRequestController.getObjectOutputStream().writeObject(userreginformation);
                return BaseRequestController.getCommand(ConfirmCommands.class);
                } catch (Exception e) {
                    return ConfirmCommands.FAILED;
                }
            }
            default: break;
        }
        return confirm;
        
    }
}
