package com.jatheon.ergo.ai.assistant.service.weather;

import com.jatheon.ergo.ai.assistant.model.weather.WeatherForecast;

public interface WeatherForecastService {

    WeatherForecast getForecastForLocation(final String location);
}
