####  Spring  AOP的使用 （内部原理 类似使用动态代理的方式来实现）

  - AspectJ：Java 社区里最完整最流行的 AOP 框架.
  - 在 Spring2.0 以上版本中, 可以使用基于 AspectJ 注解或基于 XML 配置的 AOP

  - 要在 Spring 中声明 AspectJ 切面, 只需要在 IOC 容器中将切面声明为 Bean 实例. 当在 Spring IOC 容器中初始化 AspectJ 切面之后, Spring IOC 容器就会为那些与 AspectJ 切面相匹配的 Bean 创建代理.

  - 在 AspectJ 注解中, 切面只是一个带有 @Aspect 注解的 Java 类. 
  
  - 通知是标注有某种注解的简单的 Java 方法
     
       AspectJ 支持 5 种类型的通知注解:
       
           ① @Before: 前置通知, 在方法执行之前执行
           ② @After: 后置通知, 在方法执行之后执行 
           ③  @AfterRunning: 返回通知, 在方法返回结果之后执行
           ④  @AfterThrowing: 异常通知, 在方法抛出异常之后
           ⑤  @Around: 环绕通知, 围绕着方法执行

    

#### AspectJ的使用(基于注解)

   - 首先需要导入AspectJ需要的jar包    aopalliance.jar、aspectj.weaver.jar 和 spring-aspects.jar

   - 创建一个接口

     public interface Arithme {

            int  add(int a, int b);

            int  mul(int a ,int b);
     ​

            String get();
     }

   - 实现接口

     import org.springframework.stereotype.Component;
     @Component
     public class ArithmeIml implements Arithme {

         @Override
         public int add(int a, int b) {
             return a+b;
         }
         
         @Override
         public int mul(int a, int b) {
             return a-b;
         }
         
         @Override
         public String get() {
             return "hhahha";
         }
     }

   - 生成一个切面对象 需要 @Aspect来实现一个切面类

            @Aspect
          public class LoggingAspect {
         /**
         *  @Before("execution(public String com.test.Aspectj.ArithmeIml.*(..))") 定义出发的方法
            *
         *  被@Before注解的方法会在方法调用之前调用方法 （前置通知）
            *
             */
              @Before("execution(public String com.test.Aspectj.ArithmeIml.*(..))")
               public void beforeMethod(){
               System.out.println("----beforeMethod----");
               }
               
          /**
              * 后置通知：在目标方法执行后（无论是否发生异常）执行通知 在后置通知中不能访问目标方法的执行结果
              * @param joinPoint 切面对象
              */
             @After("execution(public String com.test.Aspectj.ArithmeIml.*(..))")
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
                 @AfterThrowing(value ="execution(public String com.test.Aspectj.ArithmeIml.*(..))",throwing="ex")
                  public void afterThrowing(JoinPoint joinPoint ,Exception ex){
              
              
                  }
                /**
                    * 环绕通知需要携带ProceedingJoinPoint类型的参数
                    * 环绕通知类似于动态代理的全过程，ProceedingJoinPoint类型的参数可以决定是否执行目标方法
                    * 且环绕通知必须要有返回值，返回值即为目标方法的返回值
                    * @param point
                    */
                   @Around("execution(public String com.test.Aspectj.ArithmeIml.*(..))")
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
                       System.out.println("The method " + methodName + " ends");
               
                       return result;
                   }   
 
               
             
             
             
             }  

   - 测试类

         Arithme arithme=new ArithmeIml();
           AspectJProxyFactory aspectJProxyFactory=new AspectJProxyFactory();
              //设置目标对象
             aspectJProxyFactory.setTarget(arithme);
               //添加切面的对象
             aspectJProxyFactory.addAspect(LoggingAspect.class);
              //生成切面对象的代理对象
             Arithme proxy1=  aspectJProxyFactory.getProxy();
             int result=proxy1.add(1,2);
             System.out.println("------>"+result);
             int result1=proxy1.mul(4,2);
             System.out.println("--------->"+result1);
              System.out.println(proxy1.get());      


   -  通过Spring配置使用@AspectJ切面：

       ① 创建xml文件
           
           <?xml version="1.0" encoding="UTF-8"?>
           <beans xmlns="http://www.springframework.org/schema/beans"
                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xmlns:aop="http://www.springframework.org/schema/aop"
                  xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
           		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd">
           
                <bean id="waiter" class="com.test.Aspectj.ArithmeIml"></bean>
           
               <bean id="PreGreetingAspect" class="com.test.Aspectj.PreGreetingAspect"></bean>
           
               <!--配置AOP -->
               <aop:config>
                   <!--配置切入表达式 -->
                   <aop:pointcut id="pointcut" expression="execution(* com.test.Aspectj.Arithme.*(..))"/>
                        <aop:aspect ref="PreGreetingAspect" order="1">
                             <aop:before method="beforeGreeting" pointcut-ref="pointcut"></aop:before>
                              <aop:after method="afterGreeting" pointcut-ref="pointcut"></aop:after>
                             <aop:after-returning method="returningGreeting" pointcut-ref="pointcut" returning="result"></aop:after-returning>
                             <aop:after-throwing method="afterThrowing" pointcut-ref="pointcut" throwing="e"></aop:after-throwing>
                        </aop:aspect>
               </aop:config>
           </beans>
            

       ②  创建测试类

             ApplicationContext ctx=new ClassPathXmlApplicationContext("beans-aspetJ.xml");
                    //这里一定要注意 动态代理返回的对象一定要是父类对象，就是接口对象
                    Arithme atm = (Arithme) ctx.getBean("waiter");
                    System.out.println(atm.get());

   - 使用基于Schema的AOP命名空间配置：

     ① 创建切面对象

           @Aspect
           @Component //必须注册
           public class LoggingAspect {
               /**
                *  @Before("execution(public String com.test.Aspectj.ArithmeIml.*(..))") 定义出发的方法
                *
                *   被@Before注解的方法会在方法调用之前调用方法
                *
                */
                 @Before("execution(public String com.test.Aspectj.ArithmeIml.*(..))")
                  public void beforeMethod(){
                      System.out.println("----beforeMethod----");
                  }
           
           }

     ② 创建xml的文件   

           <?xml version="1.0" encoding="UTF-8"?>
           <beans xmlns="http://www.springframework.org/schema/beans"
                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xmlns:aop="http://www.springframework.org/schema/aop"
                  xmlns:context="http://www.springframework.org/schema/context"
                  xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
           		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd
           		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
                 <!--自动扫描的包 -->
                 <context:component-scan base-package="com.test.Aspectj">
                 </context:component-scan>
                 <!-- 注册切入对象 -->
                 <aop:aspectj-autoproxy/>
           </beans>

     ③ 创建测试类

          ApplicationContext context=new ClassPathXmlApplicationContext("beans-aspects.xml");
                   //获取返回的代理对象必须是父类的对象，接口对象，否则就会报错
                 Arithme arithmeIml = (Arithme) context.getBean("arithmeIml");
                 int result= arithmeIml.add(1,2);
                 System.out.println(arithmeIml.get());

   - 多个切面的调用顺序 
   
       使用@Order(1)来切换切面调用的顺序，数字越小越早调用
       
       
   - 切面切入表达式
   
          ① @Pointcut("execution(public String com.test.Aspectj.ArithmeIml.*(..))")
            public  void declareJointPointExpression(){};    
            
          ②在其他地方使用的时候直接引入这个方法  
       
       
        
#### 基于配置的方式使用

       <?xml version="1.0" encoding="UTF-8"?>
       <beans xmlns="http://www.springframework.org/schema/beans"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xmlns:aop="http://www.springframework.org/schema/aop"
              xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd">
       
            <bean id="waiter" class="com.test.Aspectj.ArithmeIml"></bean>
       
           <bean id="PreGreetingAspect" class="com.test.Aspectj.PreGreetingAspect"></bean>
       
           <!--配置AOP -->
           <aop:config>
               <!--配置切入表达式 -->
               <aop:pointcut id="pointcut" expression="execution(* com.test.Aspectj.Arithme.*(..))"/>
                    <aop:aspect ref="PreGreetingAspect" order="1">
                         <aop:before method="beforeGreeting" pointcut-ref="pointcut"></aop:before>
                          <aop:after method="afterGreeting" pointcut-ref="pointcut"></aop:after>
                         <aop:after-returning method="returningGreeting" pointcut-ref="pointcut" returning="result"></aop:after-returning>
                         <aop:after-throwing method="afterThrowing" pointcut-ref="pointcut" throwing="e"></aop:after-throwing>
                    </aop:aspect>
           </aop:config>
       </beans>

  
