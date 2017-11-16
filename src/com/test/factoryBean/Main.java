package com.test.factoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-beansfactory.xml");
        Car cc = (Car) context.getBean("car");
        System.out.println(cc.toString());
    }
}
