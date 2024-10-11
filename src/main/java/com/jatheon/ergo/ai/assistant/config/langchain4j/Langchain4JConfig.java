package com.jatheon.ergo.ai.assistant.config.langchain4j;

import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import static dev.langchain4j.model.openai.OpenAiImageModelName.DALL_E_3;

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

}
