package service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import menu.db.dao.ServicedOrderDao;
import menu.db.entity.ServicedOrder;

public class ProfitService {

    private float calculateProfitPerServicedList(List<ServicedOrder> servicedOrderList)
    {
        float totalProfit = 0.0f;
        float income = 0.0f;
        for (ServicedOrder servicedOrder : servicedOrderList) {
            // налог на доход - 13% 10.03.23
            income += servicedOrder.getOrderprice() * 0.87f - servicedOrder.getOrderfirstcost();
        }
        //налог на прибыль - 20% на 10.03.23
        totalProfit = income * 0.8f;
        return totalProfit;
    }

    public Date calculateDate(LocalDate localDate)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, localDate.getYear());
        cal.set(Calendar.MONTH, localDate.getMonthValue() - 1);
        cal.set(Calendar.DATE, localDate.getDayOfMonth());
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return new Date(cal.getTimeInMillis());                             
    }
    
    public float getTotalProfit()
    {
        var servicedOrderDao = new ServicedOrderDao(ServicedOrder.class);
        float profit = calculateProfitPerServicedList(servicedOrderDao.getProfitServicedOrderList());
        return profit;
    }

    public float getProfitPerPeriod(LocalDate lowLocalDate, LocalDate highLocalDate)
    {
        var servicedOrderDao = new ServicedOrderDao(ServicedOrder.class);
        float profit = calculateProfitPerServicedList(
            servicedOrderDao.getProfitServicedOrderListPerPeriod(calculateDate(lowLocalDate), calculateDate(highLocalDate)));
        return profit;
    }

    public List<Float> getProfitPerMonth(LocalDate monthDate)
    {
        var servicedOrderDao = new ServicedOrderDao(ServicedOrder.class);
        var servicedOrderList = servicedOrderDao.getProfitServicedOrderList();
        
        int year = monthDate.getYear();
        int month = monthDate.getMonthValue();

        var servicedOrderListByMonth = new ArrayList<ServicedOrder>();

        for (ServicedOrder servicedOrder : servicedOrderList) {
            if(servicedOrder.getAnswerdate().toLocalDate().getYear() == year &&
                servicedOrder.getAnswerdate().toLocalDate().getMonthValue()  == month)
            {
                servicedOrderListByMonth.add(servicedOrder);
            }
        }
        servicedOrderList.clear();

        int maxMonthDay = 0;
        if(monthDate.getMonthValue() != 2)
            maxMonthDay = monthDate.getMonth().maxLength();
        else if(monthDate.isLeapYear())
            maxMonthDay = 29;
        else
            maxMonthDay = 28;

        List<Float> monthDayProfitList = new ArrayList<Float>();
        float totalProfitPerMonth = 0.0f;
        for(int i = 0; i < maxMonthDay; i++)
        {
            float profitPerDay = 0.0f;
            for (ServicedOrder servicedOrder : servicedOrderListByMonth) {
                if(servicedOrder.getAnswerdate().toLocalDate().getDayOfMonth() == i + 1)
                    profitPerDay += 0.8f * (servicedOrder.getOrderprice() * 0.87f - servicedOrder.getOrderfirstcost());
            }
            monthDayProfitList.add(profitPerDay);
            totalProfitPerMonth += profitPerDay;
        }
        monthDayProfitList.add(totalProfitPerMonth);
        return monthDayProfitList;
    }

    public List<Float> getProfitPerYear(LocalDate yearDate)
    {
        var servicedOrderDao = new ServicedOrderDao(ServicedOrder.class);
        var servicedOrderList = servicedOrderDao.getProfitServicedOrderList();

        int year = yearDate.getYear();

        var servicedOrderListByYear = new ArrayList<ServicedOrder>();

        for (ServicedOrder servicedOrder : servicedOrderList) {
            if(servicedOrder.getAnswerdate().toLocalDate().getYear() == year)
                servicedOrderListByYear.add(servicedOrder);
        }
        servicedOrderList.clear();

        var monthProfitList = new ArrayList<Float>();
        var totalProfitPerYear = 0.0f;
        for(int i = 0; i < 12; i++)
        {
            float profitPerMonth = 0.0f;
            for (ServicedOrder servicedOrder : servicedOrderListByYear) {
                if(servicedOrder.getAnswerdate().toLocalDate().getMonthValue() == i + 1)
                    profitPerMonth += 0.8f * (servicedOrder.getOrderprice() * 0.87f - servicedOrder.getOrderfirstcost());
            }
            monthProfitList.add(profitPerMonth);
            totalProfitPerYear += profitPerMonth;
        }
        monthProfitList.add(totalProfitPerYear);
        return monthProfitList;
    }
}
