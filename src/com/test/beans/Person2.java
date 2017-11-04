package com.test.beans;

import java.util.List;
import java.util.Map;

public class Person2 {
       private String name;

       private Car car;


       private Map<String ,Car> cars;

    public Map<String, Car> getCars() {
        return cars;
    }

    public void setCars(Map<String, Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
