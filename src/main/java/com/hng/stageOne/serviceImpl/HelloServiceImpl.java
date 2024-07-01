package com.hng.stageOne.serviceImpl;

import com.hng.stageOne.service.HelloService;
import com.hng.stageOne.util.InfoGetter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private InfoGetter infoGetter;

    @Override
    public Map<String, Object> sayHello(String visitor_name, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        String clientLocation = infoGetter.getLocation(clientIp);
        String temperature = infoGetter.getTemperature(clientLocation);

        Map<String, Object> response = new HashMap<>();
        response.put("greeting", "Hello, " + visitor_name + "!, the temperature is " + temperature + " in " + clientLocation);
        response.put("location", clientLocation);
        response.put("client_ip", clientIp);

        return response;
    }
}
