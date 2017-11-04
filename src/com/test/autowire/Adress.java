package com.test.autowire;

public class Adress {
 private String addr;

 private String city;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return  "city="+city+"--addr="+addr;
    }
}
