package com.hng.stageOne.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Component
public class InfoGetter {
    @Value("${api.key}")
    private String apiKey;
    public String getLocation(String ip) {
        String apiUrl = "https://ipapi.co/" + ip + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject(apiUrl, Map.class);
        return result != null ? (String) result.get("city") : "Unknown location";
    }
    public String getTemperature(String location) {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject(apiUrl, Map.class);
        Map<String, Object> main = (Map<String, Object>) result.get("main");
        return main != null ? main.get("temp").toString() : "Unknown temperature";
    }
}
