package com.test.annotion;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class TestObject {

     public  void save(){
         System.out.println("----TestObject-----save");
     }
}
