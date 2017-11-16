package com.test.Aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 参数验证的切面
 */
@Order(1)
@Aspect
@Component
public class VildationAspectj {
  // @Before("execution( public  String com.test.Aspectj.*(..))")
  //@Before("execution(* com.test.Aspectj.Arithme.*(..))")
    @Before("executionss()")
  public void validateArgs(JoinPoint joinPoint){
       System.out.println("validateArgs----="+ Arrays.asList(joinPoint.getArgs()));


  }
  @Pointcut("execution(* com.test.Aspectj.Arithme.*(..))")
  public void executionss(){};
}
