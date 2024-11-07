package com.jatheon.ergo.ai.assistant.config.langchain4j;

import com.jatheon.ergo.ai.assistant.model.ChatLanguageModelType;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

@Import({
        OpenAIChatLanguageModelConfig.class,
        BedrockAnothropicClaudeV2LanguageModelConfig.class
})
@Configuration
public class Langchain4JConfig {

    @Bean
    Map<ChatLanguageModelType, ChatLanguageModel> chatLanguageModels(@Qualifier("openAiChatLanguageModel") final ChatLanguageModel openAiChatLanguageModel,
                                                                     @Qualifier("bedrockAnthropicClaude2ChatLanguageModel") final ChatLanguageModel bedrockAnthropicClaude2ChatLanguageModel) {
        Map<ChatLanguageModelType, ChatLanguageModel> chatLanguageModels = new HashMap<>();
        chatLanguageModels.put(ChatLanguageModelType.OPEN_AI, openAiChatLanguageModel);
        chatLanguageModels.put(ChatLanguageModelType.CLAUDE, bedrockAnthropicClaude2ChatLanguageModel);
        return chatLanguageModels;
    }

}
