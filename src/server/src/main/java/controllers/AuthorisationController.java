package controllers;

import java.io.IOException;
import java.security.MessageDigest;
import db.dao.AccountDao;
import db.entity.Admin;
import db.entity.Assistant;
import db.entity.User;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import request.tdo.AuthorisationTDO;
import request.tdo.constructors.AccountTDOConstructor;

public class AuthorisationController extends BaseRequestController {

    public AuthorisationController(BaseRequestController contr) {
        super(contr);
        
    }

    public <T> T authentication(Class<T> clientType) throws IOException
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        AuthorisationTDO authorisationTDO;
        try {
            authorisationTDO = (AuthorisationTDO)inputObjectStream.readObject();
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
        var accountDao = new AccountDao<>(clientType);
        T account = accountDao.getByLoginAndPassword(authorisationTDO.getLogin(),
                authorisationTDO.getEncryptedPassword());
        AccountTDOConstructor tdoConstructor = new AccountTDOConstructor();
        if (clientType == User.class) 
            outputObjectStream.writeObject(tdoConstructor.usertdoconversion((User)account));
        else if(clientType == Assistant.class)
            outputObjectStream.writeObject(tdoConstructor.assistanttdoconversion((Assistant)account));  
        else
            outputObjectStream.writeObject(tdoConstructor.admintdoconversion((Admin)account));
        return account;
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
