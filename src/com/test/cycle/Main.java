package com.test.cycle;
import com.test.spel.Adress;
import com.test.spel.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-cyle.xml");
        Car car = (Car) context.getBean("car");
        System.out.println(car.toString());
        context.close();
    }
}
