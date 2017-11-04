package com.test.beans;

public class Car {

    private String brank;

    private int price;

     private  String MaxSpeed;

    public Car(String brank, int price, String maxSpeed) {
        this.brank = brank;
        this.price = price;
        MaxSpeed = maxSpeed;
    }

    public Car() {
    }

    public Car(String brank, int price) {
        this.brank = brank;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.brank+"------"+this.price;
    }

    public String getBrank() {
        return brank;
    }

    public void setBrank(String brank) {
        this.brank = brank;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        MaxSpeed = maxSpeed;
    }
}
