package com.example.qmatic.tolltaxcalculator.domain;




public class Military implements Vehicle {
    @Override
    public VehicleType getVehicle(){
        return VehicleType.MILITARY;
    }

}
