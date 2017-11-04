package com.test.spel;

public class Car {

    private String brank;

    private Integer price;

    public String getBrank() {
        return brank;
    }

    public void setBrank(String brank) {
        this.brank = brank;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "-brank="+brank+"--price="+price;
    }
}
