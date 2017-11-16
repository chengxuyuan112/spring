package com.test.annotion;

import com.test.annotion.Controller.UserController;
import com.test.annotion.Reponsory.UserRepoeryIml;
import com.test.annotion.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

   

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("beans-annotion.xml");
//
//        TestObject tt = (TestObject) context.getBean("TestObject");
//
//        tt.save();
       // ApplicationContext context=new AnnotationConfigApplicationContext(TestConfiguration.class);

//        TestBean test = (TestBean) context.getBean("bean");
//        test.sayHello();

        //获取bean
        TestBean1 tb = (TestBean1) context.getBean("TestBean1");
        tb.sayHello();
        UserController userController = (UserController) context.getBean("userController");
                  userController.save();


//        UserRepoeryIml userRepoeryIml = (UserRepoeryIml) context.getBean("userRepoeryIml");
//                  userRepoeryIml.save();
//
//        UserService userService = (UserService) context.getBean("userService");
//        userRepoeryIml.save();
    }
}
