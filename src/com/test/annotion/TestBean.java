package com.test.annotion;

public class TestBean {
    public void sayHello(){
        System.out.println("TestBean sayHello...");
    }

//    public String toString(){
//        return "username:"+this.username+",url:"+this.url+",password:"+this.password;
//    }

    private void start(){
        System.out.println("TestBean 初始化。。。");
    }

    private void cleanUp(){
        System.out.println("TestBean 销毁。。。");
    }
}
