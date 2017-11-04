package com.test.scope;

import com.test.autowire.Adress;
import com.test.autowire.Car;
import com.test.autowire.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("beans-scope.xml");
        Car car = (Car) context.getBean("car");
        Car car2 = (Car) context.getBean("car");
    System.out.println(car==car2);
    }
}
