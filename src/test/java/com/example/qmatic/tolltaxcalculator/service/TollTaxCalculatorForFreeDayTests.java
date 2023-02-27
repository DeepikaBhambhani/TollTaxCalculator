package com.example.qmatic.tolltaxcalculator.service;



import com.example.qmatic.tolltaxcalculator.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class TollTaxCalculatorForFreeDayTests {


    public static TollTaxCalculatorService cal;
    private static LocalDate date;

    private static List<LocalDateTime> dates;

    @BeforeAll
    public static void initDate() {
        cal = new TollTaxCalculatorService();

    }

    @Test
    @DisplayName("Calculating Toll on weekends")
    public void weekendTollTest() {
        dates = new ArrayList<>();
        date = LocalDate.of(LocalDateTime.now().getYear(), 8, 06);
        dates.add(LocalDateTime.of(date, LocalTime.of(12, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(13, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(14, 52)));
        assertEquals(0, cal.getTollTax(new Car().getVehicle(), dates));
    }

    @Test
    @DisplayName("Test for toll free vehicles")
    public void tollFreeVehicles() {
        dates = new ArrayList<>();
        date = LocalDate.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth());
        dates.add(LocalDateTime.of(date, LocalTime.of(12, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(13, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(14, 52)));
        assertEquals(0, cal.getTollTax(new Diplomat().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Foreign().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Military().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Motorcycle().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Tractor().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Emergency().getVehicle(), dates));


    }

    @Test
    @DisplayName("Toll calculation on toll free days")
    public void freeDateTollTest() {
        int year = LocalDate.now().getYear();
        date = LocalDate.of(LocalDateTime.now().getYear(), 01, 01);
        dates = new ArrayList<>();
        dates.add(LocalDateTime.of(date, LocalTime.of(12, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(15, 52)));
        assertEquals(0, cal.getTollTax(new Car().getVehicle(), dates));

    }

    @Test
    @DisplayName("Toll calculation on a day before toll free days")
    public void dayBeforeTollFreeDay(){
        int year = LocalDate.now().getYear();
        date = LocalDate.of(LocalDateTime.now().getYear(), 03, 27);
        dates = new ArrayList<>();
        dates.add(LocalDateTime.of(date, LocalTime.of(12, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(15, 52)));
        assertEquals(0, cal.getTollTax(new Car().getVehicle(), dates));

    }


}
