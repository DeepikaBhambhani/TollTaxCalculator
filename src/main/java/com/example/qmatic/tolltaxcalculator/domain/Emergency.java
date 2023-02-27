package com.example.qmatic.tolltaxcalculator.domain;



public class Emergency implements Vehicle {
    @Override
    public VehicleType getVehicle() {
        return VehicleType.EMERGENCY;
    }
}
