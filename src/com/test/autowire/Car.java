package com.test.autowire;

public class Car {

    private String brank;

    private String price;

    public String getBrank() {
        return brank;
    }

    public void setBrank(String brank) {
        this.brank = brank;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "-brank="+brank+"--price="+price;
    }
}
