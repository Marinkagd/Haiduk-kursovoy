package controllers;

import menu.db.dao.GenericDao;
import menu.db.entity.User;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import request.tdo.UserTDO;
import request.tdo.constructors.AccountTDOConstructor;

public class UserController extends BaseRequestController{

    public UserController(BaseRequestController contr) {
        super(contr);
    }

    public void changePersonalData(User userInformation){
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            UserTDO newUserInformation = (UserTDO)inputObjectStream.readObject();
            userInformation.updateInformation(newUserInformation);
            var userdao = new GenericDao<>(User.class);
            if(userdao.update(userInformation))
            {
                AccountTDOConstructor accountTdoConst = new AccountTDOConstructor();
                outputObjectStream.writeObject(accountTdoConst.usertdoconversion(userInformation));
            }
            else
                outputObjectStream.writeObject(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
