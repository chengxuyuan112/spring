package com.test.annotion.Controller;

import com.test.annotion.TestBean;
import com.test.annotion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
     @Autowired
     private UserService userService;
    @Autowired(required = false)
     private TestBean testBean;
    public void  save(){
        System.out.println("-----UserController-----save");
        System.out.println(testBean);
        userService.save();

    }
}
