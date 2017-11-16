package com.test.annotion;

import org.springframework.stereotype.Component;

/**
 * 添加注解
 */
@Component(value = "TestBean1")
public class TestBean1 {
    public void sayHello(){
        System.out.println("TestBean1 sayHello...");
    }

//    public String toString(){
//        return "username:"+this.username+",url:"+this.url+",password:"+this.password;
//    }

    private void start(){
        System.out.println("TestBean1 初始化。。。");
    }

    private void cleanUp(){
        System.out.println("TestBean1 销毁。。。");
    }
}
