package com.jatheon.ergo.ai.assistant.config.langchain4j;

import com.jatheon.ergo.ai.assistant.model.ChatLanguageModelType;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.HashMap;

@Import({
        GPT35TChatLanguageModelConfig.class,
        BedrockAnothropicClaudeV2LanguageModelConfig.class
})
@Configuration
public class Langchain4JConfig {

    @Bean
    HashMap<ChatLanguageModelType, ChatLanguageModel> chatLanguageModels(@Qualifier("gpt35TChatLanguageModel") final ChatLanguageModel gpt35TChatLanguageModel,
                                                                         @Qualifier("bedrockAnthropicClaude2ChatLanguageModel") final ChatLanguageModel bedrockAnthropicClaude2ChatLanguageModel) {
        HashMap<ChatLanguageModelType, ChatLanguageModel> chatLanguageModels = new HashMap<>();
        chatLanguageModels.put(ChatLanguageModelType.GPT_3_5_TURBO, gpt35TChatLanguageModel);
        chatLanguageModels.put(ChatLanguageModelType.CLAUDE, bedrockAnthropicClaude2ChatLanguageModel);
        return chatLanguageModels;
    }

}
