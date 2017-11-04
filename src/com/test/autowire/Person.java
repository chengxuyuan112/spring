package com.test.autowire;

public class Person {

    private String name;

    private Adress adress;

    private  Car car;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "name="+name+"Car-----="+car.toString()+"-----adress="+adress.toString();
    }
}
