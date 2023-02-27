package com.example.qmatic.tolltaxcalculator.controller;

import com.example.qmatic.tolltaxcalculator.dto.GetRequestBodyDto;
import com.example.qmatic.tolltaxcalculator.service.TollTaxCalculatorService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tollCalculator")
public class TollTaxController {

    @Autowired
    private TollTaxCalculatorService calculator;

    @GetMapping("/test")
    String test() {
        return "Hello, Welcome to Congestion Tax Calculator";
    }


    @PostMapping(value = "/tax", consumes = {"application/json"})
    int getTax(@RequestBody GetRequestBodyDto dto) {
        log.info("Received Http request with request body Vehicle Type: " + dto.getVehicleType() + " date and time:" + dto.getDateTimes());
        return calculator.getTollTax(dto.getVehicleType(), dto.getDateTimes());
    }

}
