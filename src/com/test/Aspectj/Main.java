package com.test.Aspectj;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context=new ClassPathXmlApplicationContext("beans-aspects.xml");
//        ArithmeIml arithmeIml = (ArithmeIml) context.getBean("arithmeIml");
//        int result= arithmeIml.add(1,2);
//        System.out.println("------->"+result);
//        Waiter target = new NaiveWaiter();
//        AspectJProxyFactory factory = new AspectJProxyFactory();
//        //设置目标对象
//        factory.setTarget(target);
//        //添加切面对象
//        factory.addAspect(PreGreetingAspect.class);
//        //生成织入切面的代理对象
//        Waiter proxy = factory.getProxy();
//        proxy.greetTo("Anny");
//        proxy.serveTo("Mei");

//     Arithme arithme=new ArithmeIml();
//     AspectJProxyFactory aspectJProxyFactory=new AspectJProxyFactory();
//         //设置目标对象
//        aspectJProxyFactory.setTarget(arithme);
//          //添加切面的对象
//        aspectJProxyFactory.addAspect(LoggingAspect.class);
//         //生成切面对象的代理对象
//        Arithme proxy1=  aspectJProxyFactory.getProxy();
//        int result=proxy1.add(1,2);
//        System.out.println("------>"+result);
//        int result1=proxy1.mul(4,2);
//        System.out.println("--------->"+result1);
//         System.out.println(proxy1.get());
        ApplicationContext context=new ClassPathXmlApplicationContext("beans-aspects.xml");
          //获取返回的代理对象必须是父类的对象，接口对象，否则就会报错
        Arithme arithmeIml = (Arithme) context.getBean("arithmeIml");
        int result= arithmeIml.add(1,2);
        System.out.println(arithmeIml.get("lijialang","very cool"));
    }
}
