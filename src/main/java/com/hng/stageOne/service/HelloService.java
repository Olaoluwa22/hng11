package com.hng.stageOne.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface HelloService {
    Map<String, Object> sayHello(String visitor_name, HttpServletRequest request);
}
