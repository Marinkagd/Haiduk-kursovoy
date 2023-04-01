package start;


import java.io.IOException;

import gui.GUI;
import request.controller.BaseRequestController;
import server.ServerConnection;

public class Enter {

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ServerConnection.Connect(3333);
        BaseRequestController.setStreams();
        GUI.main(args);
        ServerConnection.breakConnection();
    }
}
