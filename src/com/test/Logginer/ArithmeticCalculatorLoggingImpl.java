package com.test.Logginer;

public class ArithmeticCalculatorLoggingImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j) {
        System.out.println("-----before----->add");
        int result=i+j;
        System.out.println("-----end----->add");
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("-----before----->sub");
        int result=i+j;
        System.out.println("-----end----->sub");
        return result;
    }
}
