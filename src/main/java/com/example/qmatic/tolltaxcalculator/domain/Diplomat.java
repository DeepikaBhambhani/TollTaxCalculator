package com.example.qmatic.tolltaxcalculator.domain;



public class Diplomat implements Vehicle{
    @Override
    public VehicleType getVehicle() {
        return VehicleType.DIPLOMAT;
    }
}
