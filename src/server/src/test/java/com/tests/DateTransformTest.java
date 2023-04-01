package com.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import service.ProfitService;

public class DateTransformTest {
    

    @Test
    public void whenConvertIntoDateTimeMillisTheSame()
    {
        var localDate = LocalDate.now();
        var profitService = new ProfitService();
        var date = profitService.calculateDate(localDate);
        assertEquals(localDate, date.toLocalDate());        
    }
}
