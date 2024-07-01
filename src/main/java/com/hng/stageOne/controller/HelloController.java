package com.hng.stageOne.controller;

import com.hng.stageOne.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    private HelloService helloService;
    @GetMapping("/hello")
    public Map<String, Object> sayHello(@RequestParam String visitor_name, HttpServletRequest request){
        return helloService.sayHello(visitor_name, request);
    }
}
