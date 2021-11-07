package com.example.kepbongeszo_be.Controller;

import com.example.kepbongeszo_be.Controller.Response.HelloWorld;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class HomeController {

    @RequestMapping("/resource")
    public HelloWorld home(){
        return new HelloWorld();
    }

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }



}
