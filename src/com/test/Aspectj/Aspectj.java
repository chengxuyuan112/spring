package com.test.Aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Aspectj {

    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("beans-aspetJ.xml");
//        Waiter waiter = (Waiter)ctx.getBean("waiter");
//        waiter.greetTo("John");
        //这里一定要注意 动态代理返回的对象一定要是父类对象，就是接口对象
        Arithme atm = (Arithme) ctx.getBean("waiter");
        System.out.println(atm.get("lijialang","very cool"));
    }
}
