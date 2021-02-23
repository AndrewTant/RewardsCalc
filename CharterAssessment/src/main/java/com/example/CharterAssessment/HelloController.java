package com.example.CharterAssessment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController{
    @GetMapping("/api/hello")
    public String hello(){
        return new Date() + "\n";
    }
}
