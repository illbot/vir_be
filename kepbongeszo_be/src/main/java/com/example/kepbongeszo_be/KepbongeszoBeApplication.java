package com.example.kepbongeszo_be;

import com.example.kepbongeszo_be.Service.PictureStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class KepbongeszoBeApplication implements CommandLineRunner {
    @Resource
    PictureStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(KepbongeszoBeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //storageService.deleteAll();
        //storageService.init();
    }
}
