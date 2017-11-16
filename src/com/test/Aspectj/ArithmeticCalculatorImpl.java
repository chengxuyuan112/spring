package com.test.Aspectj;

import com.test.Logginer.ArithmeticCalculator;
import org.springframework.stereotype.Component;

@Component(value ="aaaaaaaa")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j) {
        int result=i+j;
        return result;
    }

    @Override
    public int sub(int i, int j) {
        int result=i+j;
        return result;
    }
}
