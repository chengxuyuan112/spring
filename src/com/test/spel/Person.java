package com.test.spel;

public class Person {

    private String name;

    private String adress;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    private Car car;
    /**
     *  >3000000？ 金领 ：白领
     */
    private String info;

    public Person() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Adress getAdress() {
//        return adress;
//    }
//
//    public void setAdress(Adress adress) {
//        this.adress = adress;
//    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "name="+name+"Car-----="+car.toString()+"----info"+info ;
    }
}
