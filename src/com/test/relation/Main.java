package com.test.relation;

import com.test.autowire.Adress;
import com.test.autowire.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("beans-relation.xml");

        Adress address=(Adress) context.getBean("address");
        System.out.println(address.toString());
        Adress address1=(Adress) context.getBean("address2");
        System.out.println(address1.toString());
        Person ps = (Person) context.getBean("ps");
        System.out.println(ps.toString());
    }
}
