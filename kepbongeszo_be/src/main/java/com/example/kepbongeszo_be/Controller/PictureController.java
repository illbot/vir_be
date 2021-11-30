package com.example.kepbongeszo_be.Controller;

import com.example.kepbongeszo_be.Controller.Request.UploadRequest;
import com.example.kepbongeszo_be.Controller.Response.HelloWorld;
import com.example.kepbongeszo_be.Controller.Response.MessageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/picture")
public class PictureController {

    @PostMapping("/upload")
    public MessageResponse upload(@RequestBody UploadRequest request){
        System.out.println(request.getName());
        System.out.println(request.getType());
        return new MessageResponse("Sikeres!");
    }


    @RequestMapping("/get")
    public HelloWorld home(){
        return new HelloWorld();
    }
}
