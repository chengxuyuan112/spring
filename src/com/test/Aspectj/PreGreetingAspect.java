package com.test.Aspectj;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

public class PreGreetingAspect {
    public void beforeGreeting(){
        System.out.println("-----beforeGreeting-------How are you");
    }

    public  void afterGreeting(){
        System.out.println("-----afterGreeting----How are you");
    }
    public  void returningGreeting(JoinPoint joinPoint,Object result){
        System.out.println("-----returningGreeting----How are you"+result);
    }
    public  void afterThrowing(Exception e){
        System.out.println("-----afterThrowing----How are you"+e.toString());
    }
}
