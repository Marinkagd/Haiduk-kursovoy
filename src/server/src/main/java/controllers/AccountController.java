package controllers;

import java.util.ArrayList;

import db.dao.GenericDao;
import db.entity.Assistant;
import db.entity.User;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import request.tdo.AssistantTDO;
import request.tdo.UserTDO;
import request.tdo.constructors.AccountTDOConstructor;

public class AccountController extends BaseRequestController{

    public AccountController(BaseRequestController contr) {
        super(contr);
    }
    
    public <T> void sendAccountList(Class<T> accountType)
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var accountDao = new GenericDao<>(accountType);
            var accountList = accountDao.getAll();
            var accountTDOConstructor = new AccountTDOConstructor();
            if(accountType == User.class)
            {
                var userTDOList = new ArrayList<UserTDO>();
                for (T t : accountList) {
                    userTDOList.add(accountTDOConstructor.usertdoconversion((User)t));
                }
                outputObjectStream.writeObject(userTDOList);
            }
            else if(accountType == Assistant.class)
            {
                var assistantTDOList = new ArrayList<AssistantTDO>();
                for (T t : accountList) {
                    assistantTDOList.add(accountTDOConstructor.assistanttdoconversion((Assistant)t));
                }
                outputObjectStream.writeObject(assistantTDOList);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void deleteUser()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
           var usertdo = (UserTDO)inputObjectStream.readObject();
           var user = new User(usertdo);
           var userDao = new GenericDao<>(User.class);
           userDao.remove(user);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void deleteAssistant()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
           var assistanttdo = (AssistantTDO)inputObjectStream.readObject();
           var assistant = new Assistant(assistanttdo);
           var assistantDao = new GenericDao<>(Assistant.class);
           assistantDao.remove(assistant);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void addAssistant()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
           var assistanttdo = (AssistantTDO)inputObjectStream.readObject();
           var assistant = new Assistant(assistanttdo);
           var assistantDao = new GenericDao<>(Assistant.class);
           assistantDao.save(assistant);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
}
