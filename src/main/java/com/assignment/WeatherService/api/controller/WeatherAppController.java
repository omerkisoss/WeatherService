package com.assignment.WeatherService.api.controller;

import com.assignment.WeatherService.api.model.Device;
import com.assignment.WeatherService.integration.OWM.DailyWeather;
import com.assignment.WeatherService.services.DeviceService;
import com.assignment.WeatherService.services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/devices")
public class WeatherAppController {

    private final WeatherService weatherService;

    private final DeviceService deviceService;

    @Autowired
    public WeatherAppController(WeatherService weatherService, DeviceService deviceService) {
        this.weatherService = weatherService;
        this.deviceService = deviceService;
    }

    @GetMapping("{id}")
    public String getWeather(@PathVariable String id) {
        Device device = deviceService.getDeviceById(id);
        DailyWeather dailyWeather = weatherService.getWeather(BigDecimal.valueOf(device.getDeviceLatitude()), BigDecimal.valueOf(device.getDeviceLongitude()));
        return WeatherService.weatherConditionResponse(dailyWeather);
    }
}
