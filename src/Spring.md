####Spring简单使用

-  创建一个applicationContext.xml配置文件
   
       <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
           <!-- 配置bean-->
           <bean id="helloword" class="com.test.beans.HelloWord">
               <property name="name" value="Spring"></property>
           </bean>
      </beans>
      
 - 使用配置好的bean
 
          //1创建spring的IOC容器

        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
          // 从IOC中获取bean对象
        HelloWord helloWord= (HelloWord)context.getBean("helloword");
          // 调用方法
          helloWord.hello();   
          
          
 #### IOC DI
 
 - IOC 反转资源获取的方向 容器主动将资源推送给它所管理的组件，组件需要做的仅是选择一种合适的方式来接收资源，        
 
 
 - DI IOC的另外一种表述方式，即组件以一些预定义好的方法，（例如：setter方法）接受来自容器的资源注入  
 
 
#### bean的配置

  - 在xml文件中通过反射的方式生成一个bean对象，bean对象必须有一个默认的无参数的构造器
  
   
#### Spring 支持的3中依赖注入的方式

 - 属性注入
  
       <bean id="helloword" class="com.test.beans.HelloWord">
           <property name="name" value="Spring"></property>
      </bean>
  
 - 构造器注入
       ① 使用ID区分对象
       ② 使用class来反射的方式来创建一个bean对象
       ③ 使用type 来设置 bean对象的属性类型 （构造器重载的时候使用）
       ④ 使用 index 来设置属性的位置 （构造器重载的时候使用）
       ⑤ 使用 ref="car"用来对bean对象的引用属性赋值；
       ⑥ 使用级联属性赋值 
             
       <bean id="car" class="com.test.beans.Car">
           <constructor-arg value="aaa" index="0" type="java.lang.String"></constructor-arg>
            <constructor-arg value="300000"></constructor-arg>
        </bean>
 
  
          <bean id="person" class="com.test.beans.Person">
           <property name="name" value="Tom"></property>
          <property name="car" ref="car"></property>
           </bean>
           
     ⑦ 配置集合属性
      
      
      <bean id="person3" class="com.test.beans.Person">
                      <property name="name" value="TOM"></property>
                       <property name="cars">
                           <!--使用list节点可以为list属性赋值 -->
                           <list>
                               <ref bean="car"></ref>
                               <ref bean="car"></ref>
                               <ref bean="car"></ref>
                           </list>
                       </property>
                   </bean>  
                   
                   
   ⑧ 配置Map属性
   
    <bean id="person4" class="com.test.beans.Person2">
                  <property name="name" value="tom"></property>
                    <property name="cars">
                        <map>
                              <entry key="AAA" value-ref="car"></entry>
                              <entry key="BBB" value-ref="car"></entry>
                        </map>
   
                    </property>
   
       </bean>             
    
   ⑨ Properties属性配置      
   
   
      <bean id="datasource" class="com.test.beans.DataSouce">
                  <property name="properties">
                          <props>
                              <prop key="user">root</prop>
                              <prop key="password">123456</prop>
                              <prop key="jdbcurl">jdbc:url.//test</prop>
                          </props>
    
                  </property>
    
        </bean>
#### xmlbean自动装配

     <bean id="address" class="com.test.autowire.Adress" >
            <property name="addr" value="陕西"></property>
            <property name="city" value="xian"></property>
       </bean>
      <bean id="car" class="com.test.autowire.Car">
           <property name="brank" value="aodi"></property>
           <property name="price" value="3000000"></property>
      </bean>
       <!--autowire="byName"属性指定自动装配的方式byName 根据bean的名字和当前bean的setter风格的属性名字进行自动装配 -->
       <!--autowire="byType"根据bean的类型和当前bean的属性的类型进行自动装配 假如可以有多个类型的可以匹配就会报错（匹配的对象必须是唯一的）-->
       <bean id="person" class="com.test.autowire.Person" autowire="byType">
         <property name="name" value="Tom"></property>
         <!--<property name="adress" ref="address"></property>-->
         <!--<property name="car" ref="car"></property>-->
     </bean> 
  
####bean直接的关系 

- 继承 
  
  
  
    <!-- 抽象bean bean的abstract属性为TRUE的bean，不能被Ioc实例化，只用来被继承配置，若某一个bean的class属性没有指定，则bean必须是一个模板-->
      <bean id="address" class="com.test.autowire.Adress" abstract="false">
          <property name="city" value="xian"></property>
          <property name="addr" value="chazhanglu"></property>
     </bean>
      <!--bean配置的继承，使用bean的parent属性指定继承那个bean的配置 -->
    <bean id="address2" class="com.test.autowire.Adress" parent="address">
         <property name="addr" value="sanqiao"></property>
        <!--<property name="city" value="xian"></property>-->
    </bean>
       
       
 - 依赖
   
  ①
   Spirng 允许用户通过depend-on属性设置bean前置依赖，前置依赖的bean 会在本bean实例化之前创建好
   
   
   ② 如果需要依赖多个前置的bean，则可以通过,空格,或的方式配置bean的名称  
   
   
   
    <!--配置Person时，必须关联一个car，也就是说这个bean依赖于car -->
       <bean id="ps" class="com.test.autowire.Person" depends-on="car">
           <property name="name" value="laoli"></property>
           <property name="adress" ref="address2"></property>
           <property name="car" ref="car"></property>
       </bean>
       
 #### 配置bean的作用域
 
    <!--使用bean的scope来配置bean的作用域
           ① singleton 默认值，容器初始化值，在整个容器的生命周期只创建一个bean，单例模式
           ② prototype 原型模式，容器初始化时候不创建bean的实例，而在每次请求时候时都创建一个新的bean实例，并返回
           ③ 
      -->
     <bean id="car" class="com.test.autowire.Car" scope="prototype">
         <property name="brank" value="auto"></property>
         <property name="price" value="3000000"></property>
     </bean>      
    
 #### 外部属性文件
 
   在配置文件里面配置bean时，有时候需要在配置的bean里面混入系统部署的细节信息，(文件路径，数据源配置信息等)
   
   而这些数据实际上需要和bean配置想分离
   
 #### Spel
   
   - Spring表达式语言，是一个 支持运行时查询和操作对象图的强大的表达式语言。
    
   - 语法  类似EL， 使用#{}作为定界符，所有在大括号中的字符都将被认为是SpEL
   
   - SpEL 为bean 的属性进行动态赋值提供了便利
   
   -  通过SpEL 可以实现
   
      ① 通过bean的ID对bean进行引用
      
      ② 调用方法以及引用对象中的属性
      
      ③ 计算表达式的值
      
      ④ 正则表达式的匹配
      
    </bean>
        <bean id="person" class="com.test.spel.Person">
            <!--使用spel来引用其他的bean -->
             <property name="car" value="#{car}"></property>
            <!--使用sepl来引用其他的bean的 bean的属性 -->
             <property name="adress" value="#{address.city }"></property>
            <!--在sepl中使用运算符 -->
            <property name="info" value="#{car.price>30000?'金领':'白领'}"></property>
            <property name="name" value="Tom"></property>
        </bean>   
        
        
 #### IOC容器中bean的生命周期方法
 
   <!--init-method="init 初始化调用的方法  destroy-method="destorya 销毁的时候调用bean的方法 -->
       <bean id="car" class="com.test.cycle.Car" init-method="init" destroy-method="destory">
         <property name="brank" value="auto"></property>
       </bean>             
     <!--bean的后置处理器
           ①实现BeanPostProcessor接口，
     
               postProcessBeforeInitialization init-method 之前被调用
     
               postProcessAfterInitialization  init-method  之后调用
     
                object bean本身
     
                String  name 容器IOC配置的bean的名字
     
               返回值 ：是实际上返回给用户的那个bean，注意：可以在上面的方法中修改返回的修改的bean，也可以返回一个新的bean
     
             配置bean的后置处理器，不需要配置ID，IOC容器自动识别是一个BeanPostProcessor
     
         -->
         <bean id="mybean" class="com.test.cycle.MyBeanPostProcessor">
     
         </bean>
 #### 通过静态工厂方法配置bean 
 
     <!--通过静态工厂方法来配置bean -->
             ① class 指向静态工厂方法的全类名
    
             ② factory-method 指向静态工厂方法
        
             ③ <!--  <constructor-arg></constructor-arg> -->用来配置工厂方法的参数
    
        <bean id="car1" class="com.test.factory.StaticCarFactory" factory-method="getCar">   
 
 
 ####实例工厂方法
 
       <!--创建工厂模式的对象 -->
         <bean id="carfactory" class="com.test.factory.IntanceCarFactory"></bean>
          <!--在根据工厂对象创建bean对象 -->
         <bean id="car2"  factory-bean="carfactory" factory-method="getCar">
         </bean>
  
  
 ####　通过factoryBean配置bean对象
 
    ①实现接口
    
       public class CarFactoryBean implements FactoryBean<Car> {
          //返回bean对象
          @Override
          public Car getObject() throws Exception {
              return  new Car("BWM",1000000);
          }
      
          /**
           * 返回bean的类型
           * @return
           */
          @Override
          public Class<?> getObjectType() {
              return Car.class;
          }
      
          @Override
          public boolean isSingleton() {
              return false;
          }
      }

      ② 在xml中配置
       <bean id="car" class="com.test.factoryBean.CarFactoryBean">
      
          </bean>
 #### 基于注解形式的配置方式
  
   - @bean注解
   
      ① @Bean注解在返回实例的方法上，如果未通过@Bean指定bean的名称，则默认与标注的方法名相同； 
       
      ② @Bean注解默认作用域为单例singleton作用域，可通过@Scope(“prototype”)设置为原型作用域； 
      
      ③既然@Bean的作用是注册bean对象，那么完全可以使用@Component、@Controller、@Service、@Ripository等注解注册bean，当然需要配置@ComponentScan注解进行自动扫描。
  
      ④代码示例
      
 bean类
 
      public class TestBean {
      
          public void sayHello(){
              System.out.println("TestBean sayHello...");
          }
      
          public String toString(){
              return "username:"+this.username+",url:"+this.url+",password:"+this.password;
          }
      
          public void start(){
              System.out.println("TestBean 初始化。。。");
          }
      
          public void cleanUp(){
              System.out.println("TestBean 销毁。。。");
          }
      }      
      
 配置类
 
    @Configuration
      public class TestConfiguration {
         public TestConfiguration(){
             System.out.println("spring容器启动初始化。。。");
         }
 
     //@Bean注解注册bean,同时可以指定初始化和销毁方法
     //@Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
     @Bean
     @Scope("prototype")
     public TestBean testBean() {
         return new TestBean();
     }
     } 
 
 测试类
  
    public class TestMain {
        public static void main(String[] args) {
            ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
            //获取bean
            TestBean tb = context.getBean("testBean");
            tb.sayHello();
        }
    } 
   - 组件扫描(component scanning):  Spring 能够从 classpath 下自动扫描, 侦测和实例化具有特定注解的组件. 
   - 特定组件包括:
       
         ① @Component: 基本注解, 标识了一个受 Spring 管理的组件
         ② @Respository: 标识持久层组件
         ③ @Service: 标识服务层(业务层)组件
         ④ @Controller: 标识表现层组件
   - 对于扫描到的组件, Spring 有默认的命名策略: 使用非限定类名, 第一个字母小写. 也可以在注解中通过 value 属性值标识组件的名称

   - 当在组件类上使用了特定的注解之后, 还需要在 Spring 的配置文件中声明<context:component-scan> 
   
   
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd">
          <!-- resource-pattern="/"指定扫描的资源 -->
         <context:component-scan base-package="com.test.annotion"
         >
         </context:component-scan>
     </beans>
  
        ① base-package 属性指定一个需要扫描的基类包，Spring 容器将会扫描这个基类包里及其子包中的所有类
        
        ② 当需要扫描多个包时, 可以使用逗号分隔.
        
        ③ 如果仅希望扫描特定的类而非基包下的所有类，可使用 resource-pattern 属性过滤特定的类
        
   - <context:include-filter> 子节点表示要包含的目标类(这里所说的是注解对象)
   
         <context:component-scan base-package="com.test.annotion" use-default-filters="false">
               <!--<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Component"></context:exclude-filter>-->
                <context:include-filter type="annotation" expression="org.springframework.stereotype.Component"></context:include-filter>
            </context:component-scan>
   
   
   -  <context:exclude-filter> 子节点表示要排除在外的目标类 (这里所说的是注解对象)  
   
           <context:component-scan base-package="com.test.annotion">
              <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Component"></context:exclude-filter>
           </context:component-scan>  
        
 #### 使用注解来自动装配bean(是bean于bean直接创建关联关系)     

 - @Autowired 
     
     ①   @Autowired 注解自动装配具有兼容类型的单个 Bean属性
     
     ②   构造器, 普通字段(即使是非 public), 一切具有参数的方法都可以应用
     
     ③  默认情况下, 所有使用 @Authwired 注解的属性都需要被设置. 当 Spring 找不到匹配的 Bean 装配属性时, 会抛出异常, 若某一属性允许不被设置, 可以设置 @Authwired 注解的 required 属性为 false
     
             @Autowired(required = false)
             private TestBean testBean;
    
     ④  默认情况下, 当 IOC 容器里存在多个类型兼容的 Bean 时, 通过类型的自动装配将无法工作. 此时可以在 @Qualifier 注解里提供 Bean 的名称. Spring 允许对方法的入参标注 @Qualifiter 已指定注入 Bean 的名称
     
              private UserReposery userReposery;
         
                @Autowired
                @Qualifier("userRepoeryIml1")这里的名字注意第一个字母小写
               public void setUserReposery(UserReposery userReposery) {
                 this.userReposery = userReposery;
                }
     
   
     ⑤   @Authwired 注解也可以应用在数组类型的属性上, 此时 Spring 将会把所有匹配的 Bean 进行自动装配.
     
     ⑥  @Authwired 注解也可以应用在集合属性上, 此时 Spring 读取该集合的类型信息, 然后自动装配所有与之兼容的 Bean. 
     
     ⑦  @Authwired 注解用在 java.util.Map 上时, 若该 Map 的键值为 String, 那么 Spring 将自动装配与之 Map 值类型兼容的 Bean, 此时 Bean 的名称作为键值
 - @Autowired
 
 
 - @Autowired   
 
 ####　泛型依赖注入
 
 
 
 #### AOP(外面切面编程)解决的问题
 
  -  代码混乱：越来越多的非业务需求(日志和验证等)加入后, 原有的业务方法急剧膨胀.  每个方法在处理核心逻辑的同时还必须兼顾其他多个关注点. 


  -  代码分散: 以日志需求为例, 只是为了满足这个单一需求, 就不得不在多个模块（方法）里多次重复相同的日志代码. 如果日志需求发生变化, 必须修改所有模块.
  

  - 代理设计模式的原理: 使用一个代理将对象包装起来, 然后用该代理对象取代原始对象. 任何对原始对象的调用都要通过代理. 代理对象决定是否以及何时将方法调用转到原始对象上


#####创建一个动态代理类

     public class ArithmeticCalculatorLoggingProxy {
     
         private  ArithmeticCalculator target;
     
         public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
             this.target = target;
         }
     
         /**
          * 获取代理对象
          * @return
          */
         public ArithmeticCalculator getTarget() {
          ArithmeticCalculator proxy=null;
             /**
              * loader: 代理对象使用的类加载器
              *
              *  interfaces: 指定代理对象的类型. 即代理代理对象中可以有哪些方法.
              *
              *  h: 当具体调用代理对象的方法时, 应该如何进行响应, 实际上就是调用 InvocationHandler 的 invoke 方法
              */
     
             ClassLoader loader=target.getClass().getClassLoader();
     
             Class[] interfaces=new Class[]{ArithmeticCalculator.class};
     
             InvocationHandler h=new InvocationHandler() {
     
                 /**
                  * proxy: 代理对象。 一般不使用该对象
                  * method: 正在被调用的方法
                  * args: 调用方法传入的参数
                  */
                 @Override
                 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
     
                     String methodName = method.getName();
                     //打印日志
                     System.out.println("[before] The method " + methodName + " begins with " + Arrays.asList(args));
                     //调用目标方法
                     Object result = null;
                     try {
                         //前置通知
                         result = method.invoke(target, args);
                         //返回通知, 可以访问到方法的返回值
                     } catch (NullPointerException e) {
                         e.printStackTrace();
                         //异常通知, 可以访问到方法出现的异常
                     }
                     //后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值
     
                     //打印日志
                     System.out.println("[after] The method ends with " + result);
                     return result;
                 }
             };
     
            proxy= (ArithmeticCalculator) Proxy.newProxyInstance(loader,interfaces,h);
     
             return proxy;
         }
     }
     
##### 测试类

      //生成需要代理的对象
            ArithmeticCalculator arithmeticCalculator=new ArithmeticCalculatorImpl();
             //生成代理类
            ArithmeticCalculatorLoggingProxy calculatorLoggingProxy=new ArithmeticCalculatorLoggingProxy(arithmeticCalculator);
            //使用代理类生成一个新的代理对象
              ArithmeticCalculator arithmeticCalculatorproxy=calculatorLoggingProxy.getTarget();
     
                 arithmeticCalculatorproxy.add(1,2);
                 arithmeticCalculatorproxy.sub(4,2);      

#### AOP 简介

- AOP(Aspect-Oriented Programming, 面向切面编程): 是一种新的方法论, 是对传统 OOP(Object-Oriented Programming, 面向对象编程) 的补充.


- AOP 的主要编程对象是切面(aspect), 而切面模块化横切关注点.


- 在应用 AOP 编程时, 仍然需要定义公共功能, 但可以明确的定义这个功能在哪里, 以什么方式应用, 并且不必修改受影响的类. 这样一来横切关注点就被模块化到特殊的对象(切面)里.


- AOP 的好处:

   ① 每个事物逻辑位于一个位置, 代码不分散, 便于维护和升级
   
   ② 业务模块更简洁, 只包含核心业务代码.


#### AOP 术语

 - 切面(Aspect):  横切关注点(跨越应用程序多个模块的功能)被模块化的特殊对象


 - 通知(Advice):  切面必须要完成的工作


 - 目标(Target): 被通知的对象

 - 代理(Proxy): 向目标对象应用通知之后创建的对象
 
 
 - 连接点（Joinpoint）：程序执行的某个特定位置：如类某个方法调用前、调用后、方法抛出异常后等。连接点由两个信息确定：方法表示的程序执行点；相对点表示的方位。例如 ArithmethicCalculator#add() 方法执行前的连接点，执行点为 ArithmethicCalculator#add()； 方位为该方法执行前的位置
 
 - 切点（pointcut）：每个类都拥有多个连接点：例如 ArithmethicCalculator 的所有方法实际上都是连接点，即连接点是程序类中客观存在的事务。AOP 通过切点定位到特定的连接点。类比：连接点相当于数据库中的记录，切点相当于查询条件。切点和连接点不是一对一的关系，一个切点匹配多个连接点，切点通过 org.springframework.aop.Pointcut 接口进行描述，它使用类和方法作为连接点的查询条件。
  
       