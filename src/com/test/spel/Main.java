package com.test.spel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-spel.xml");
        Adress add = (Adress) context.getBean("address");
        System.out.println(add.toString());

        Person person = (Person) context.getBean("person");
        System.out.println(person.toString());
    }
}
