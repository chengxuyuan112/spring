package com.test.annotion.Reponsory;


import org.springframework.stereotype.Repository;

@Repository
public class UserRepoeryIml1 implements UserReposery {
    @Override
    public void save() {
       System.out.println("----UserRepoeryIml1-----save");
    }
}
