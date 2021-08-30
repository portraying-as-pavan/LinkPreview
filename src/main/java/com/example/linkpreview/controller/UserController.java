package com.example.linkpreview.controller;

import com.example.linkpreview.Entity.Userd;
import com.example.linkpreview.service.UserdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController("/user")
public class UserController {
    @Autowired
    private UserdService userdService;

    @PostMapping(value = "/add/{userName}/{password}")
    public void register(@PathVariable String userName, @PathVariable String password){
        Userd userd=new Userd(userName,password,"user",true);
        userdService.addUser(userd);
    }

    @GetMapping(value = "/show/{userName}")
    public Userd showUserByName(@PathVariable String userName){
        return userdService.getUserd(userName);
    }

    @GetMapping(value = "/hello/1")
    public String hello(){
        return "Hello from User Controller";
    }
}
