package com.jatheon.ergo.ai.assistant.config.langchain4j;

import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.Duration;

import static dev.langchain4j.model.openai.OpenAiImageModelName.DALL_E_3;


/**
 * DALL-E-3
 * sizes: 1024x1024, 1024x1792, 1792x1024
 * quality: standard, hd (for enhanced detail)
 * styles: vivid, natural
 */

@Configuration
public class Langchain4JConfig {

    @Value("${langchain4j.dalle3.api-key}")
    private String openAiApiKey;


    @Bean
    ImageModel imageModel() {
        return OpenAiImageModel.builder()
                .apiKey(openAiApiKey)
                .modelName(DALL_E_3)
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    /**
    @Bean
    ImageModel imageModel() {
        return OpenAiImageModel.builder()
                .apiKey(openAiApiKey)
                .modelName(DALL_E_3)
                .size("1792x1024")
                .quality("standard")
                .style("natural")
                .timeout(Duration.ofMinutes(10))
                .logRequests(true)
                .logResponses(true)
                .build();
    }**/

}
