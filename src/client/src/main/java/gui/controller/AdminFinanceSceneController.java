package gui.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controllers.FinanceController;
import gui.GUI;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AdminFinanceSceneController {

    @FXML private LineChart<String, Float> profitLineChart;
    @FXML private CategoryAxis profitXAxis;
    @FXML private NumberAxis profitYAxis;

    @FXML private TextField fullProfitField, profitPerPerioField;
    @FXML private DatePicker lowDatePicker, highDatePicker;

    @FXML private ChoiceBox<Integer> analysisPerMonthYearChoice, analysisPerMonthMonthChoice,
                                        analysisPerYearYearChoice;

    @FXML private TextField fullProfitByChart;

    @FXML private Button  reportButton;
    @FXML private Text reportConfirm;

    private List<Float> reportList;

    @FXML
    public void initialize()
    {
       RefreshFullProfit();
       FillChoiceBoxes();
       reportButton.setVisible(false);
       reportConfirm.setVisible(false);
    }

    private void RefreshFullProfit()
    {
        float fullProfit = FinanceController.getFullProfit();
        fullProfitField.setText(String.format("%.2f", fullProfit));
    }

    private void FillChoiceBoxes()
    {
        var yearList = new ArrayList<Integer>();
        for(int i = 1990; i < 2023; i++)
            yearList.add(i);
        var monthList = new ArrayList<Integer>();
        for(int i = 1; i < 13; i++)
            monthList.add(i);
        analysisPerMonthYearChoice.getItems().setAll(yearList);
        analysisPerYearYearChoice.getItems().setAll(yearList);
        analysisPerMonthMonthChoice.getItems().setAll(monthList);
    }


    @FXML
    private void getProfitPerPeriod()
    {
       var lowDate = lowDatePicker.getValue();
       var highDate = highDatePicker.getValue();
       if(lowDate == null || highDate == null)
         return;
       var profitPerPeriod = FinanceController.getProfitPerPeriod(lowDate, highDate);
       profitPerPerioField.setText(String.format("%.2f", profitPerPeriod));
    }

    @FXML
    private void analysisPerMonth()
    {
        reportConfirm.setVisible(false);
        if(analysisPerMonthYearChoice.getValue() == null ||
           analysisPerMonthMonthChoice.getValue() == null)
            return;
        
        var monthLocalDate = LocalDate.of(analysisPerMonthYearChoice.getValue(),
                                          analysisPerMonthMonthChoice.getValue(),
                                          1);

        var monthDayProfitList = FinanceController.getAnalysisPerMonth(monthLocalDate);
        updateChart(monthDayProfitList, "День", "Прибыль за месяц");

        reportList = monthDayProfitList;
        reportButton.setVisible(true);
        reportButton.setOnAction(action ->{
            FinanceController.createReportOnMonth(reportList, monthLocalDate);
            reportConfirm.setVisible(true);
        });
    }

    @FXML
    private void analysisPerYear()
    {
        reportConfirm.setVisible(false);
        if(analysisPerYearYearChoice.getValue() == null)
            return;
        var yearLocalDate = LocalDate.of(analysisPerYearYearChoice.getValue(), 1, 1);
        var monthProfitList = FinanceController.getAnalysisPerYear(yearLocalDate);
        updateChart(monthProfitList, "Месяц", "Прибыль за год");

        reportList = monthProfitList;
        reportButton.setVisible(true);
        reportButton.setOnAction(action ->{
            FinanceController.createReportOnYear(reportList, analysisPerYearYearChoice.getValue());
            reportConfirm.setVisible(true);
        });
    }
    
    private void updateChart(List<Float> periodList, String xLabel, String seriesName)
    {
        Series<String, Float> periodSeries = new Series<String, Float>();
        for(int i = 0; i < periodList.size() - 1; i++)
        {
            periodSeries.getData().add(new Data<String, Float>(String.valueOf(i + 1), periodList.get(i)));
        }
        profitXAxis.setLabel(xLabel);
        profitYAxis.setLabel("Рубли");
        periodSeries.setName(seriesName);
        profitLineChart.getData().clear();
        profitLineChart.getData().addAll(periodSeries);
        fullProfitByChart.setText(String.format("%.2f", periodList.get(periodList.size() - 1)));
    }


    @FXML
    private void exitToAdminStartScene() throws IOException
    {
        GUI.setRoot("adminstartscene");
    }
}
