package com.assignment.WeatherService.services;

import java.math.BigDecimal;
import java.net.URI;

import com.assignment.WeatherService.integration.OWM.DailyWeather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Slf4j
@Service
public class WeatherService {

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude=hourly,current,minutely,alerts&units=imperial&appid={key}";

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Value("${api.openweathermap.key}")
    private String apiKey;

    @Autowired
    public WeatherService(RestTemplate restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder;
        this.objectMapper = objectMapper;
    }


    public DailyWeather getWeather(BigDecimal lat, BigDecimal lon) {
        log.info("Requesting current weather for geographical lat, lon {}/{}", lat, lon);
        URI url = new UriTemplate(WEATHER_URL).expand(lat, lon, this.apiKey);
        return convert(invoke(url));
    }

    @SuppressWarnings("unchecked")
    private <T> ResponseEntity<T> invoke(URI url) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        return this.restTemplate
                .exchange(request, (Class<T>) String.class);
    }
    private DailyWeather convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            return DailyWeather.builder().main(root.path("daily").get(0).path("weather").get(0).path("main").asText()).
                    minTemp(BigDecimal.valueOf(root.path("daily").get(0).path("temp").path("min").asDouble())).
                    maxTemp(BigDecimal.valueOf(root.path("daily").get(0).path("temp").path("max").asDouble())).build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
    public static String weatherConditionResponse(DailyWeather dailyWeather){
        if(dailyWeather.getMinTemp() != null && dailyWeather.getMinTemp().compareTo(BigDecimal.valueOf(30)) >= 0 ||
                dailyWeather.getMaxTemp() != null && dailyWeather.getMaxTemp().compareTo(BigDecimal.valueOf(30)) >= 0){
            return "it's too hot!, stay in the Mazgan";
        }
        else if(dailyWeather.getMain().toLowerCase().contains("rain")){
            return "its raining outside, stay at home";
        }
        else return "you can go to check device issue and fix it, nice weather today!";
    }
}