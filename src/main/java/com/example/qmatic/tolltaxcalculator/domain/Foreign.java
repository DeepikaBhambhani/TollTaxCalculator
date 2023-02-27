package com.example.qmatic.tolltaxcalculator.domain;



public class Foreign implements Vehicle{
    @Override
    public VehicleType getVehicle() {
        return VehicleType.FOREIGN;
    }
}
