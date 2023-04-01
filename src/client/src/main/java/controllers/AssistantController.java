package controllers;

import java.util.List;

import request.commands.AdminCommands;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import request.tdo.AssistantTDO;

public class AssistantController {
    @SuppressWarnings("unchecked")
    public static List<AssistantTDO> getAssistantList()
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.GET_ASSISTANT_LIST);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    var assistantList = (List<AssistantTDO>)BaseRequestController.getObjectInputStream().readObject();
                    return assistantList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            default: break;
        }
        return null;
    }

    public static void deleteAssistant(AssistantTDO assistantTDO)
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.DELETE_ASSISTANT);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(assistantTDO);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            default: break;
        }
    }

    public static void addAssistant(AssistantTDO assistantTDO)
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.ADD_ASSISTANT);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(assistantTDO);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            default: break;
        }
    }
}
