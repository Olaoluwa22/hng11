package com.hng.stageOne.util;

import com.hng.stageOne.exception.exceptionHandler.CityNotFoundException;
import com.hng.stageOne.exception.exceptionHandler.InternalErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Component
public class InfoGetter {
    @Value("${api.key}")
    private String apiKey;
    public String getLocation(String ip) throws CityNotFoundException {
        String apiUrl = "https://ipapi.co/" + ip + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject(apiUrl, Map.class);
        if(result == null){
            throw new CityNotFoundException("City not Found");
        }
        return (String) result.get("city");
    }
    public String getTemperature(String location) throws InternalErrorException {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject(apiUrl, Map.class);
        Map<String, Object> main = (Map<String, Object>) result.get("main");
        if(main == null){
            throw new InternalErrorException("Cannot resolve weather condition of requested location");
        }
        return main.get("temp").toString();
    }
}
