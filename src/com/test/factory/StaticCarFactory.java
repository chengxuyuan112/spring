package com.test.factory;

public class StaticCarFactory {

      //private  static Car car=new Car();

    public  static  Car getCar( ){
        Car car=new Car();
        car.setBrank("aodi");
        car.setPrice(300000);
        car.setMaxSpeed("250");
        return car ;
    }
}
