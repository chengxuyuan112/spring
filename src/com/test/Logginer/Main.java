package com.test.Logginer;

public class Main {
    public static void main(String[] args) {
//         ArithmeticCalculator arithmeticCalculator=new ArithmeticCalculatorLoggingImpl();
//

//            int result=arithmeticCalculator.add(1,2);
//            System.out.println("----->"+result);
        //生成需要代理的对象
       ArithmeticCalculator arithmeticCalculator=new ArithmeticCalculatorImpl();
        //生成代理类
       ArithmeticCalculatorLoggingProxy calculatorLoggingProxy=new ArithmeticCalculatorLoggingProxy(arithmeticCalculator);
       //使用代理类生成一个新的代理对象
         ArithmeticCalculator arithmeticCalculatorproxy=calculatorLoggingProxy.getTarget();

            arithmeticCalculatorproxy.add(1,2);
            arithmeticCalculatorproxy.sub(4,2);
    }
}
