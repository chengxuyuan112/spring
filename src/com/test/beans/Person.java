package com.test.beans;

import java.util.List;

public class Person {
       private String name;

       private Car car;


       private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public Person(String name, Car car, List<Car> cars) {
        this.name = name;
        this.car = car;
        this.cars = cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Person() {
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
