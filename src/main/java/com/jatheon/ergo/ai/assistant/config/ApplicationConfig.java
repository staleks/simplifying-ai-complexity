package com.jatheon.ergo.ai.assistant.config;

import com.jatheon.ergo.ai.assistant.config.langchain4j.Langchain4JConfig;
import com.jatheon.ergo.ai.assistant.config.web.RestWebMvcConfig;
import com.jatheon.ergo.ai.assistant.endpoint.ping.PingController;
import com.jatheon.ergo.ai.assistant.service.OpenAIQuestionService;
import com.jatheon.ergo.ai.assistant.service.QuestionService;
import com.jatheon.ergo.ai.assistant.service.weather.WeatherForecastService;
import com.jatheon.ergo.ai.assistant.service.weather.WeatherForecastServiceImpl;
import com.jatheon.ergo.ai.assistant.service.weather.WeatherForecastServiceImprovedImpl;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import({
        RestWebMvcConfig.class,
        Langchain4JConfig.class
})
@Configuration
public class ApplicationConfig {

    //~ Ping
    @Bean
    PingController pingController() {
        return new PingController();
    }

    @Bean
    @Profile("basic")
    WeatherForecastService weatherForecastService() {
        return new WeatherForecastServiceImpl();
    }

    @Bean
    @Profile("improved")
    WeatherForecastService improvedWeatherForecastService() {
        return new WeatherForecastServiceImprovedImpl();
    }

    @Bean
    QuestionService questionService(final ChatLanguageModel chatLanguageModel,
                                    final WeatherForecastService weatherForecastService) {
        return new OpenAIQuestionService(chatLanguageModel, weatherForecastService);
    }

    @Bean
    QuestionController questionController(final QuestionService questionService) {
        return new QuestionController(questionService);
    }


}
