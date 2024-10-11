package com.jatheon.ergo.ai.assistant.config;

import com.jatheon.ergo.ai.assistant.config.langchain4j.Langchain4JConfig;
import com.jatheon.ergo.ai.assistant.config.web.RestWebMvcConfig;
import com.jatheon.ergo.ai.assistant.endpoint.image.ImageController;
import com.jatheon.ergo.ai.assistant.endpoint.ping.PingController;
import com.jatheon.ergo.ai.assistant.service.image.ImageService;
import com.jatheon.ergo.ai.assistant.service.image.OpenAIImageService;
import dev.langchain4j.model.image.ImageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

    //~ DALLE-3
    @Bean
    ImageService imageService(final ImageModel imageModel) {
        return new OpenAIImageService(imageModel);
    }

    @Bean
    ImageController imageController(final ImageService imageService) {
        return new ImageController(imageService);
    }

}
