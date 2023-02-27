package com.example.qmatic.tolltaxcalculator.domain;



public class Motorcycle implements Vehicle {
    @Override
    public VehicleType getVehicle() {
        return VehicleType.MOTORCYCLE;
    }
}
