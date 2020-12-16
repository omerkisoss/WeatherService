package com.assignment.WeatherService.integration.OWM;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyWeather {
    private BigDecimal minTemp;
    private BigDecimal maxTemp;
    private String main;

}
