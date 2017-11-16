package com.test.annotion.service;

import com.test.annotion.Reponsory.UserReposery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserReposery userReposery;

       @Autowired
       @Qualifier("userRepoeryIml1")
    public void setUserReposery(UserReposery userReposery) {
        this.userReposery = userReposery;
    }

    public void save(){
        System.out.println("---UserService------save");
        userReposery.save();
    }
}
