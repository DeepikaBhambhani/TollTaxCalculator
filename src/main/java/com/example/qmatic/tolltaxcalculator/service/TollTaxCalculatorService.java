package com.example.qmatic.tolltaxcalculator.service;


import com.example.qmatic.tolltaxcalculator.domain.VehicleType;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.qmatic.tolltaxcalculator.config.Constants.MAX_FEE_FOR_ONE_DAY;
import static javax.management.timer.Timer.ONE_HOUR;


@Service
@Slf4j
public class TollTaxCalculatorService {

    private ValidateTollTaxService calService=new ValidateTollTaxService();


    //this method will valid all the inputs and calculate toll respectively
    public int getTollTax(@NonNull VehicleType vehicle, @NonNull List<LocalDateTime> dates) {

        log.info("getTollTax method started");

        //this method will validate all the input parameters
        if (!calService.validateInputs(vehicle, dates)) {
            return 0;
        }

        List<LocalTime> sortedTimeList = dates.stream()
                .map(LocalDateTime::toLocalTime)
                .sorted()
                .collect(Collectors.toList());

        if (sortedTimeList.isEmpty()) {
            return 0;
        }
        log.info("Calculating toll tax for sorted list");
        LocalTime intervalStart = sortedTimeList.get(0);
        int totalFee = 0;
        for (LocalTime date : sortedTimeList) {
            double nextFee = calService.getFee(date);
            double tempFee = calService.getFee(intervalStart);
            long minutes = Duration.between(intervalStart, date).toMillis();

            if (minutes <= ONE_HOUR) {
                if (totalFee > 0) totalFee -= tempFee;
                if (nextFee >= tempFee) tempFee = nextFee;
                totalFee += tempFee;
            } else {
                intervalStart = date;
                totalFee += nextFee;
            }
        }
        if (totalFee > MAX_FEE_FOR_ONE_DAY) totalFee = MAX_FEE_FOR_ONE_DAY;

        log.debug("Total Fee is " + totalFee);
        log.info("Toll fee calculation ended");
        return totalFee;
    }

}
