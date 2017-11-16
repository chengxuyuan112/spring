package com.test.Aspectj;

public class NaiveWaiter implements Waiter {
    @Override
    public void greetTo(String name) {
        System.out.println("NaiveWaiter：greet to " + name + "...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("NaiveWaiter：serving to " + name + "...");
    }

    @Override
    public String lend(String name) {
        return name;
    }


}
