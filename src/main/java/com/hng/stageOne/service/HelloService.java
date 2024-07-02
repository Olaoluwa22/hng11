package com.hng.stageOne.service;

import com.hng.stageOne.dto.ResponseDto;
import com.hng.stageOne.exception.exceptionHandler.CityNotFoundException;
import com.hng.stageOne.exception.exceptionHandler.InternalErrorException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface HelloService {
    ResponseDto sayHello(String visitor_name, HttpServletRequest request) throws CityNotFoundException, InternalErrorException;
}
