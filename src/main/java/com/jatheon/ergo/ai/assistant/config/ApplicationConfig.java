package com.jatheon.ergo.ai.assistant.config;

import com.jatheon.ergo.ai.assistant.config.langchain4j.Langchain4JConfig;
import com.jatheon.ergo.ai.assistant.config.web.RestWebMvcConfig;
import com.jatheon.ergo.ai.assistant.endpoint.masterchef.ChefController;
import com.jatheon.ergo.ai.assistant.endpoint.ping.PingController;
import com.jatheon.ergo.ai.assistant.model.ChatLanguageModelType;
import com.jatheon.ergo.ai.assistant.service.masterchef.MasterChefService;
import com.jatheon.ergo.ai.assistant.service.masterchef.MasterChefServiceImpl;
import com.jatheon.ergo.ai.assistant.service.prompt.PromptFactory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;

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
    MasterChefService masterChefService(final Map<ChatLanguageModelType, ChatLanguageModel> chatLanguageModels) {
        return new MasterChefServiceImpl(new PromptFactory(), chatLanguageModels);
    }

    @Bean
    ChefController  chefController(final MasterChefService masterChefService) {
        return new ChefController(masterChefService);
    }

}
