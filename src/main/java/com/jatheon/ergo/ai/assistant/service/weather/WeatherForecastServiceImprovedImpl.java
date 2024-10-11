package com.jatheon.ergo.ai.assistant.service.weather;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.jatheon.ergo.ai.assistant.model.weather.WeatherForecast;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

@Slf4j
public class WeatherForecastServiceImprovedImpl implements WeatherForecastService {

    @Value("${openWeatherMap.apiKey}")
    private String openWeatherMapApiKey;

    @Tool("Get the weather forecast for a location")
    @Override
    public WeatherForecast getForecastForLocation(@P("Location to get the forecast for") final String location) {
        log.trace("Getting improved weather forecast for: {}", location);
        try {
            OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(openWeatherMapApiKey);
            final Weather weather = openWeatherClient
                    .currentWeather()
                    .single()
                    .byCityName(sanitizeLocation(location))
                    .language(Language.ENGLISH)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava();
            return new WeatherForecast(weather.getLocation().getName(), weather.getWeatherState().getDescription(), weather.getTemperature().getValue());
        } catch (Exception e) {
            log.error("Error getting weather forecast for {}", location, e);
            return new WeatherForecast("Unknown", "Unknown", 0.0);
        }
    }

    private String sanitizeLocation(final String location) {
        Assert.notNull(location, "Location cannot be null");
        return location.replace(" ", "+");
    }

}
