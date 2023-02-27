package com.example.qmatic.tolltaxcalculator.domain;



public class Car implements Vehicle {
    @Override
    public VehicleType getVehicle() {
        return VehicleType.CAR;
    }
}
