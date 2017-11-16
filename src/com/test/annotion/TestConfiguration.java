package com.test.annotion;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackageClasses =com.test.annotion.TestConfiguration.class)
public class TestConfiguration {
    public TestConfiguration() {
        System.out.println("spring容器启动初始化。。。");
    }

//    @Bean(initMethod ="start",name = "bean",destroyMethod = "cleanUp")
//    @Scope("prototype")
//    public TestBean testBean(){
//
//        return  new TestBean();
//    }
}
