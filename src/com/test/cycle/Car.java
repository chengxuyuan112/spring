package com.test.cycle;

public class Car {
     public  Car(){
        System.out.println("car ....construtor");
    }
    private String brank;

    public String getBrank() {
        return brank;
    }

    public void setBrank(String brank) {
        this.brank = brank;
    }

    public void init(){
        System.out.println("----init-----");
    }
    public void  destory(){
        System.out.println("----destory-----");
    }
}
