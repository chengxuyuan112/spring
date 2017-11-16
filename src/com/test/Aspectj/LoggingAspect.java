package com.test.Aspectj;

import javafx.scene.chart.ValueAxis;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component //必须注册
public class LoggingAspect {
    /**
     *  @Before("execution(public String com.test.Aspectj.ArithmeIml.*(..))") 定义出发的方法
     *
     *   被@Before注解的方法会在方法调用之前调用方法 前置通知
     *
     */
     // @Before("execution(public String com.test.Aspectj.ArithmeIml.*(..))")
    @Before("declareJointPointExpression()")
       public void beforeMethod(){
           System.out.println("----beforeMethod----");
       }
    /**
     * 后置通知：在目标方法执行后（无论是否发生异常）执行通知
     * @param joinPoint 切面对象
     */
   // @After("execution(public String com.test.Aspectj.ArithmeIml.*(..))")
      @After("declareJointPointExpression()")
    public  void  AfterMethod(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        List<Object> args= Arrays.asList(joinPoint.getArgs());
        System.out.println("----the method----"+methodName+"after with---+"+args);
        }

    /**
     *  返回通知：在方法正常执行后的
     * @param joinPoint1 切面对象
     */
    @AfterReturning(value ="execution(public String com.test.Aspectj.ArithmeIml.*(..))",returning = "result")
    public  void aferReturning( JoinPoint joinPoint1,Object result){
        String methodName=joinPoint1.getSignature().getName();
        List<Object> args= Arrays.asList(joinPoint1.getArgs());
        System.out.println("----the method----"+methodName+"after with---+"+args+"方法返回的值===="+result);
    }

    /**
     *   在目标方法出现异常的时候会执行此方法，可以指定异常对象，且可以指定特定的异常时在执行代码
     * @param joinPoint 切面对象
     * @param ex 异常对象
     */
   //@AfterThrowing(value ="execution(public String com.test.Aspectj.ArithmeIml.*(..))",throwing="ex")
    @AfterThrowing(value = "declareJointPointExpression()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint ,Exception ex){


    }

    /**
     * 环绕通知需要携带ProceedingJoinPoint类型的参数
     * 环绕通知类似于动态代理的全过程，ProceedingJoinPoint类型的参数可以决定是否执行目标方法
     * 且环绕通知必须要有返回值，返回值即为目标方法的返回值
     * @param point
     */
    @Around("declareJointPointExpression()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        String methodName = point.getSignature().getName();
        try {
            //前置通知
            System.out.println("The method " + methodName + " begins with " + Arrays.asList(point.getArgs()));
            //执行目标方法
            result = point.proceed();
            //返回通知
            System.out.println("The method " + methodName + " ends with " + result);
        } catch (Throwable e) {
            //异常通知
            System.out.println("The method " + methodName + " occurs exception:" + e);
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println( "The method " + methodName + " ends");

        return result;
    }
    @Pointcut("execution(public String com.test.Aspectj.ArithmeIml.*(..))")
    public  void declareJointPointExpression(){};
}
