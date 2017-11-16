package com.test.factory;

public class IntanceCarFactory {
    public    Car getCar( ){
        Car car1=new Car();
        car1.setBrank("aodi1");
        car1.setPrice(400000);
        car1.setMaxSpeed("250");
        return car1 ;
    }
}
