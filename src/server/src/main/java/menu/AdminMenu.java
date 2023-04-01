package menu;

import controllers.AccountController;
import controllers.FinanceController;
import menu.db.entity.Admin;
import menu.db.entity.Assistant;
import menu.db.entity.User;
import request.commands.AdminCommands;
import request.controller.BaseRequestController;

@SuppressWarnings("unused")
public class AdminMenu extends Menu{
    private Admin admininformation;
    private AccountController accountController;
    private FinanceController financeController;
   
    protected AdminMenu(BaseRequestController contr, Admin admin) {
        super(contr);
        admininformation = admin;
        accountController = new AccountController(contr);
        financeController = new FinanceController(contr);
    }

    @Override
    public void start() {
        boolean toExit = false;
        while (!toExit) {
            AdminCommands command = null;
            try {
                command = commandController.getCommand(AdminCommands.class);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Неверная команда. Ожидалась команда " + AdminCommands.class.getName());
            }

            switch (command) {
                case GET_USER_LIST                  -> accountController.sendAccountList(User.class);
                case GET_ASSISTANT_LIST             -> accountController.sendAccountList(Assistant.class);
                case DELETE_USER                    -> accountController.deleteUser();
                case DELETE_ASSISTANT               -> accountController.deleteAssistant();
                case ADD_ASSISTANT                  -> accountController.addAssistant();
                case GET_FULL_PROFIT                -> financeController.sendFullProfit();
                case GET_PROFIT_PER_PERIOD          -> financeController.sendProfitPerPeriod();
                case GET_ANALYSIS_PER_MONTH         -> financeController.sendAnalysisPerMonth();
                case GET_ANALYSIS_PER_YEAR          -> financeController.sendAnalysisPerYear();
                case EXIT                           -> {return;}
                default                             -> {break;}
            }
            
        }
        
    }
    
}
