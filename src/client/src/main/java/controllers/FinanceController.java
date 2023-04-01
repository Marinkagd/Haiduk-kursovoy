package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import request.commands.AdminCommands;
import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;

public class FinanceController {

    private static ReportController reportController = ReportController.getReportController();
    public static float getFullProfit()
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.GET_FULL_PROFIT);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return 0.0f;
        }
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    var fullProfit = (Float)BaseRequestController.getObjectInputStream().readObject();
                    return fullProfit;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0.0f;
                }
            }
            default: break;
        }
        return 0.0f;
    }

    public static float getProfitPerPeriod(LocalDate lowDate, LocalDate highDate)
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.GET_PROFIT_PER_PERIOD);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return 0.0f;
        }   
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(lowDate);
                    BaseRequestController.getObjectOutputStream().flush();
                    BaseRequestController.getObjectOutputStream().writeObject(highDate);
                    BaseRequestController.getObjectOutputStream().flush();
                    var profitPerPeriod = (Float)BaseRequestController.getObjectInputStream().readObject();
                    return profitPerPeriod;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0.0f;
                }
            }
            default: break;
        }
        return 0.0f;
    }

    @SuppressWarnings("unchecked")
    public static List<Float> getAnalysisPerMonth(LocalDate monthLocalDate)
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.GET_ANALYSIS_PER_MONTH);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }   
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(monthLocalDate);
                    var monthDayProfitList = (List<Float>)BaseRequestController.getObjectInputStream().readObject();
                    return monthDayProfitList;
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
    public static List<Float> getAnalysisPerYear(LocalDate yearLocalDate)
    {
        BaseRequestController.sendRequest(AdminCommands.class, AdminCommands.GET_ANALYSIS_PER_YEAR);
        ConfirmCommands confirm = null;
        try {
            confirm = BaseRequestController.getCommand(ConfirmCommands.class);
        } catch (Exception e) {
            return null;
        }   
        switch (confirm) {
            case SUCCESSFULLY: {
                try {
                    BaseRequestController.getObjectOutputStream().writeObject(yearLocalDate);
                    var monthDayProfitList = (List<Float>)BaseRequestController.getObjectInputStream().readObject();
                    return monthDayProfitList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;    
                }
            }
            default: break;
        }
        return null;
    }

    public static void createReportOnMonth(List<Float> monthDayProfitList, LocalDate monthLocalDate)
    {
        var month = String.valueOf(monthLocalDate.getYear()) + "_" + String.valueOf(monthLocalDate.getMonthValue());
        try {
            reportController.monthReport(monthDayProfitList, month);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

    public static void createReportOnYear(List<Float> monthDayProfitList, int year)
    {
        try {
            reportController.yearReport(monthDayProfitList, year);
        } catch (Exception e) {
            e.getStackTrace();
            return;
        }

    }
}
