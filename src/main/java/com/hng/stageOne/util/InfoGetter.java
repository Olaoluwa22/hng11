package com.hng.stageOne.util;

import com.hng.stageOne.exception.exceptionHandler.CityNotFoundException;
import com.hng.stageOne.exception.exceptionHandler.InternalErrorException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Component
public class InfoGetter {
    @Value("${api.key}")
    private String apiKey;

    public String getClientIpAddress(HttpServletRequest request){
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }
    public String getLocation(String ip) throws CityNotFoundException {
        String apiUrl = "https://ip-api.com/json" + ip;
        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<String, Object> result = restTemplate.getForObject(apiUrl, Map.class);
            if (result == null || !result.containsKey("city")) {
                throw new CityNotFoundException("City not found for IP: " + ip);
            }
            return (String) result.get("city");
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CityNotFoundException("City not found for IP: " + ip);
            }
            throw new CityNotFoundException("Error retrieving city for IP: " + ip + ", " + e.getMessage());
        }
    }
    public String getTemperature(String location) throws InternalErrorException {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<String, Object> result = restTemplate.getForObject(apiUrl, Map.class);
            if (result == null || !result.containsKey("main")) {
                throw new InternalErrorException("Cannot resolve weather condition of requested location: " + location);
            }
            Map<String, Object> main = (Map<String, Object>) result.get("main");
            if (main == null || !main.containsKey("temp")) {
                throw new InternalErrorException("Temperature data is missing for location: " + location);
            }
            return main.get("temp").toString();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new InternalErrorException("Weather data not found for location: " + location);
            }
            throw new InternalErrorException("Error retrieving weather data for location: " + location + ", " + e.getMessage());
        }
    }

}
