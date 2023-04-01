package controllers;

import java.util.List;

import request.commands.AdminCommands;
import request.commands.ConfirmCommands;
import request.commands.UserCommands;
import request.controller.BaseRequestController;
import request.tdo.UserTDO;

public class UserController {
    public static UserTDO changePersonalData(UserTDO usertdo)
    {
        BaseRequestController.sendRequest(UserCommands.class, UserCommands.CHANGE_PERSONAL_DATA);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(usertdo);
                    UserTDO newUserTDO = (UserTDO)BaseRequestController.getObjectInputStream().readObject();
                    return newUserTDO;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            default: break;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<UserTDO> getUserList()
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.GET_USER_LIST);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    var userList = (List<UserTDO>)BaseRequestController.getObjectInputStream().readObject();
                    return userList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            default: break;
        }
        return null;
    }

    public static void deleteUser(UserTDO userTDO)
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.DELETE_USER);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(userTDO);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            default: break;
        }
    }
}
