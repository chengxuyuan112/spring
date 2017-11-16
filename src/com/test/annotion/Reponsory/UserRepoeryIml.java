package com.test.annotion.Reponsory;


import org.springframework.stereotype.Repository;

@Repository("")
public class UserRepoeryIml implements UserReposery {
    @Override
    public void save() {
       System.out.println("----UserRepoeryIml-----save");
    }
}
