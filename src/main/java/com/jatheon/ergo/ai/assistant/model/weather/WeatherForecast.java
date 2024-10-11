package com.jatheon.ergo.ai.assistant.model.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherForecast {

    private String location;
    private String forecast;
    private Double temperature;

}
