package com.test.Aspectj;

import org.springframework.stereotype.Component;
@Component
public class ArithmeIml implements Arithme {

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int mul(int a, int b) {
        return a-b;
    }

    @Override
    public String get(String a,String b ) {
        return a+b;
    }
}
