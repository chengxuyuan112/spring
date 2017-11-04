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
    
     