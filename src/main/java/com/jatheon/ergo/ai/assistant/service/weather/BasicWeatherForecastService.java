package com.jatheon.ergo.ai.assistant.service.weather;

import com.jatheon.ergo.ai.assistant.model.weather.WeatherForecast;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicWeatherForecastService implements WeatherForecastService {



    @Tool("Get the weather forecast for a location")
    @Override
    public WeatherForecast getForecastForLocation(@P("Location to get the forecast for") final String location) {
        log.info("Getting basic weather forecast for {}", location);
        if (location.equals("Paris")) {
            return new WeatherForecast("Paris", "Sunny", 20.0);
        } else if (location.equals("London")) {
            return new WeatherForecast("London", "Rainy", 15.0);
        } else if (location.equals("Rovinj")) {
            return new WeatherForecast("Rovinj", "Sunny", 22.0);
        } else if (location.equals("Novi Sad")) {
            return new WeatherForecast("Novi Sad", "Sunny", 18.0);
        } else {
            return new WeatherForecast("Unknown", "Unknown", 0.0);
        }
    }

}
