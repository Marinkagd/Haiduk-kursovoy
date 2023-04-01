package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import menu.db.dao.AccountDao;
import menu.db.entity.Assistant;
import menu.db.entity.User;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import request.tdo.AssistantTDO;
import request.tdo.UserTDO;

public class RegistrationController extends BaseRequestController{

    public RegistrationController(InputStream inp, OutputStream outp) {
        super(inp, outp);
    }

    public RegistrationController(BaseRequestController contr)
    {
        super(contr);
    }

    public <T> void registration(Class<T> clientType) throws IOException
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        if(clientType == User.class)
        {
            UserTDO userreginformation = null;
            try {
                userreginformation = (UserTDO)inputObjectStream.readObject();
                User user = new User(userreginformation);
                var userdao = new AccountDao<>(User.class);
                if(userdao.save(user))
                    sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
                else
                    sendResponse(ConfirmCommands.class, ConfirmCommands.FAILED);
            } catch (Exception e) {
                e.getStackTrace();
                return;
            }
        }
        else if(clientType == Assistant.class)
        {
            AssistantTDO assistantreginformation = null;
            try {
                assistantreginformation = (AssistantTDO)inputObjectStream.readObject();
                Assistant assistant = new Assistant(assistantreginformation);
                var assistantdao = new AccountDao<>(Assistant.class);
                if(assistantdao.save(assistant))
                    sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
                else
                    sendResponse(ConfirmCommands.class, ConfirmCommands.FAILED);
            } catch (Exception e) {
                e.getStackTrace();
                return;
            }
        }
            
    }

}
