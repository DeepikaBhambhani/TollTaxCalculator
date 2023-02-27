package com.example.qmatic.tolltaxcalculator.domain;


public class Tractor implements Vehicle{

    @Override
    public VehicleType getVehicle() {
        return VehicleType.TRACTOR;
    }
}
