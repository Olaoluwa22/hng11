package com.hng.stageOne.controller;

import com.hng.stageOne.dto.ResponseDto;
import com.hng.stageOne.exception.exceptionHandler.CityNotFoundException;
import com.hng.stageOne.exception.exceptionHandler.InternalErrorException;
import com.hng.stageOne.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    private HelloService helloService;
    @GetMapping("/hello")
    public ResponseDto sayHello(@RequestParam("visitor_name") String visitor_name, HttpServletRequest request) throws CityNotFoundException, InternalErrorException {
        return helloService.sayHello(visitor_name, request);
    }
}
