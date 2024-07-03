package com.hng.stageOne.serviceImpl;

import com.hng.stageOne.dto.ResponseDto;
import com.hng.stageOne.exception.exceptionHandler.CityNotFoundException;
import com.hng.stageOne.exception.exceptionHandler.InternalErrorException;
import com.hng.stageOne.service.HelloService;
import com.hng.stageOne.util.InfoGetter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private InfoGetter infoGetter;

    @Override
    public ResponseDto sayHello(String visitor_name, HttpServletRequest request) throws CityNotFoundException, InternalErrorException {
        String client_ip = request.getRemoteAddr();
        String clientLocation = infoGetter.getLocation(client_ip);
        String temperature = infoGetter.getTemperature(clientLocation);

        String greeting = "Hello, " + visitor_name + "!, the temperature is " + temperature + " degrees Celsius in " + clientLocation;
        return new ResponseDto(client_ip, clientLocation, greeting);
    }
}