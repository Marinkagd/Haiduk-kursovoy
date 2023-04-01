package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportController {
    private static final String directoryPath = "reports/";
    private static final String monthReportBaseName = "month_report";
    private static final String yearReportBaseName = "year_report";
    private static final String[] months = {
        "Январь",
        "Февраль",
        "Март",
        "Апрель",
        "Май",
        "Июнь",
        "Июль",
        "Август",
        "Сентябрь",
        "Октябрь",
        "Ноябрь",
        "Декабрь"
    };

    private static ReportController reportController;
    
    public static ReportController getReportController()
    {
        if(reportController != null)
            return reportController;

        reportController = new ReportController(directoryPath);
        return reportController;
    }

    public File directoryFile;

    public ReportController(String directoryPath)
    {
        directoryFile = new File(directoryPath);
        directoryFile.mkdir();
    }

    public void monthReport(List<Float> monthDayProfitList, String month) throws IOException
    {
        var monthReportFile = new File(directoryFile, monthReportBaseName + "_" + month + ".txt");
        monthReportFile.createNewFile();

        try (var dataFileWriter = new PrintWriter(new FileOutputStream(monthReportFile, false));)
        {
            dataFileWriter.write("Месячный отчет за " + month + "\n");
            dataFileWriter.write("Прибыль за месяц составила: " + String.format("%.2f", monthDayProfitList.get(monthDayProfitList.size() - 1)) + " руб.\n");
            dataFileWriter.write("Прибыль по дням составила:\n");
            for (int i = 0; i < monthDayProfitList.size() - 1; i++) {
                dataFileWriter.write("День " + (i + 1) + ": " + String.format("%.2f", monthDayProfitList.get(i)) + " руб.\n" );
            } 
        } catch (Exception e) {
           e.getStackTrace();
           return;
        }
    }

    public void yearReport(List<Float> yearMonthProfitList, int year) throws IOException
    {
        var yearReportFile = new File(directoryFile, yearReportBaseName + "_" + String.valueOf(year) + ".txt");

        try (var dataFileWriter = new PrintWriter(new FileOutputStream(yearReportFile, false));) {
            dataFileWriter.write("Годовой отчет за " + year + "\n");
            dataFileWriter.write("Прибыль за год составила: " + String.format("%.2f", yearMonthProfitList.get(yearMonthProfitList.size() - 1)) + " руб.\n");
            dataFileWriter.write("Прибыль по месяцам составила:\n");
            for (int i = 0; i < yearMonthProfitList.size() - 1; i++) {
                dataFileWriter.write(months[i] + ": " + String.format("%.2f", yearMonthProfitList.get(i)) + " руб.\n" );
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
