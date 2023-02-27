package com.example.qmatic.tolltaxcalculator.service;


import com.example.qmatic.tolltaxcalculator.domain.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


import static com.example.qmatic.tolltaxcalculator.config.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TollTaxCalculatorForNullParamTest {
    private static TollTaxCalculatorService cal;
    private static LocalDate date;



    @BeforeAll
    public static void initDate() {
        cal = new TollTaxCalculatorService();
        date = LocalDate.of(2022, 8, 17);
    }


    @Test
    @DisplayName("Null vehicle parameter test")
    public void nullParamVehicleTest() {
        RuntimeException re = assertThrows(RuntimeException.class,
                () -> cal.getTollTax(null,
                        List.of(LocalDateTime.of(date, LocalTime.of(6, 0)))
                                ));
        assertEquals(VEHICLE_NULL, re.getMessage());
    }


    @Test
    @DisplayName("Date parameter null test")
    public void nullParamDatesTest() {
        RuntimeException re = assertThrows(RuntimeException.class,
                () -> cal.getTollTax(new Car().getVehicle(), null));
        assertEquals(DATE_NULL, re.getMessage());
    }


    @Test
    @DisplayName("Multiple date test")
    public void differentDatesTest() {
        RuntimeException re = assertThrows(RuntimeException.class,
                () -> cal.getTollTax(new Car().getVehicle(),
                        List.of(LocalDateTime.of(2022, 8, 10, 7, 0),
                                        LocalDateTime.of(2022, 8, 11, 7, 0))
                                ));
        assertEquals(MORE_THAN_ONE_DAY_MSG, re.getMessage());
    }
}
