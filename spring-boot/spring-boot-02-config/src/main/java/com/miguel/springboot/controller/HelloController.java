package com.miguel.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${dog.name}") // 适合只需要取单个值的情况下使用
    private String name;

    @RequestMapping("hello")
    public String sayHello() {
        return "Hello, " + name;
    }
}
