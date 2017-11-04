package com.test.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//         //创建对象
//          HelloWord helloWord=new HelloWord();
//          //赋值
//          helloWord.setName("lijialang");
//          //调用方法
//          helloWord.hello();

          //1创建spring的IOC容器

        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
          // 从IOC中获取bean对象
        HelloWord helloWord= (HelloWord)context.getBean("helloword");
          // 调用方法
          helloWord.hello();

        B b1 = (B) context.getBean("B");
         System.out.println(b1.getName());
      //   System.out.println(b1.getA().getName());

        Car car = (Car) context.getBean("car");
        System.out.println(car.toString());

         Person person= (Person) context.getBean("person");
          System.out.println(person.getCar().toString());
        Person person3= (Person) context.getBean("person3");
        DataSouce datas = context.getBean(DataSouce.class);
        System.out.println(datas.getProperties());


    }
}
