package com.jatheon.ergo.ai.assistant.config;

import com.jatheon.ergo.ai.assistant.config.langchain4j.Langchain4JConfig;
import com.jatheon.ergo.ai.assistant.config.web.RestWebMvcConfig;
import com.jatheon.ergo.ai.assistant.endpoint.QuestionController;
import com.jatheon.ergo.ai.assistant.endpoint.ping.PingController;
import com.jatheon.ergo.ai.assistant.service.BedrockAnthropicClaudeQuestionService;
import com.jatheon.ergo.ai.assistant.service.QuestionService;
import com.jatheon.ergo.ai.assistant.service.prompt.PromptFactory;
import dev.langchain4j.model.chat.ChatLanguageModel;
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

    //~ OpenAI Question
    @Bean
    QuestionService questionService(final ChatLanguageModel chatLanguageModel) {
        return new BedrockAnthropicClaudeQuestionService(new PromptFactory(), chatLanguageModel);
    }

    @Bean
    QuestionController questionController(final QuestionService questionService) {
        return new QuestionController(questionService);
    }

}
