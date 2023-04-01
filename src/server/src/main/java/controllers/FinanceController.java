package controllers;

import java.time.LocalDate;

import request.commands.ConfirmCommands;
import request.controller.BaseRequestController;
import service.ProfitService;

public class FinanceController extends BaseRequestController{

    private ProfitService profitService;

    public FinanceController(BaseRequestController contr) {
        super(contr);
        profitService = new ProfitService();
    }
    

    public void sendFullProfit()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            float fullProfit = profitService.getTotalProfit();
            outputObjectStream.writeObject(fullProfit);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void sendProfitPerPeriod()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var lowLocalDate = (LocalDate)inputObjectStream.readObject();
            var highLocalDate = (LocalDate)inputObjectStream.readObject();
            var profitPerPeriod = profitService.getProfitPerPeriod(lowLocalDate, highLocalDate);
            outputObjectStream.writeObject(profitPerPeriod);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void sendAnalysisPerMonth()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var monthLocalDate = (LocalDate)inputObjectStream.readObject();
            var monthDayProfitList = profitService.getProfitPerMonth(monthLocalDate);
            outputObjectStream.writeObject(monthDayProfitList);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void sendAnalysisPerYear()
    {
        sendResponse(ConfirmCommands.class, ConfirmCommands.SUCCESSFULLY);
        try {
            var yearLocalDate = (LocalDate)inputObjectStream.readObject();
            var monthProfitList = profitService.getProfitPerYear(yearLocalDate);
            outputObjectStream.writeObject(monthProfitList);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    
}
