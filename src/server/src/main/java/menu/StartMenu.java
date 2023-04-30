package menu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import controllers.AuthorisationController;
import controllers.RegistrationController;
import db.entity.Admin;
import db.entity.Assistant;
import db.entity.User;

import request.commands.StartMenuCommands;

public class StartMenu extends Menu{
    
    private AuthorisationController authorisationController;
    private RegistrationController registrationController;

    public StartMenu(InputStream inputStream, OutputStream outputStream) {
         super(inputStream, outputStream);
         authorisationController = new AuthorisationController(commandController);
         registrationController = new RegistrationController(commandController);
    }

    @Override
    public void start() {
        boolean toExit = false;
        while (!toExit) {
            StartMenuCommands command;
            try {
                command = commandController.getCommand(StartMenuCommands.class);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Неверная команда. Ожидалась команда " + StartMenuCommands.class.getName());
                break;
            }
            switch (command) {
                case USER_AUTHORISATION -> {
                    try {
                        User user = authorisationController.authentication(User.class);
                        if(user != null)
                        {
                            UserMenu usermenu = new UserMenu(commandController, user);
                            usermenu.start();
                        }
                    } catch (IOException e) {
                      e.printStackTrace();
                    }  
                }
                case ASSISTANT_AUTHORISATION -> {
                    try {
                        Assistant assistant = authorisationController.authentication(Assistant.class);
                        if(assistant != null)
                        {
                            AssistantMenu assistantMenu = new AssistantMenu(commandController, assistant);
                            assistantMenu.start();
                        }
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                }
                case ADMIN_AUTHORISATION -> {
                    try
                    {
                        Admin admin = authorisationController.authentication(Admin.class);
                        if(admin != null)
                        {
                            AdminMenu adminMenu = new AdminMenu(commandController, admin);
                            adminMenu.start();
                        }
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                }
                case USER_REGISTRATION -> {
                    try {
                        registrationController.registration(User.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                   
                default -> System.out.println("Такого нет");
            }
            
        }
       
    }
}
