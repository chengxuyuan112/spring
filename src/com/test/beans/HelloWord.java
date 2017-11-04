package com.test.beans;

public class HelloWord {
    private  String name;

    public void setName(String name) {
        this.name = name;
    }

    public void hello(){

        System.out.println("hello:="+name);
    }
}
