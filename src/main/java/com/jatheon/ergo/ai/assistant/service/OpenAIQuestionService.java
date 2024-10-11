package com.jatheon.ergo.ai.assistant.service;

import com.jatheon.ergo.ai.assistant.model.QuestionResponse;
import com.jatheon.ergo.ai.assistant.service.error.AIAssistantServiceException;
import com.jatheon.ergo.ai.assistant.service.weather.WeatherAssistant;
import com.jatheon.ergo.ai.assistant.service.weather.WeatherForecastService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class OpenAIQuestionService implements QuestionService {

    private static final int CHAT_MEMORY_MAX_MESSAGES = 10;

    private final ChatLanguageModel chatLanguageModel;
    private final WeatherForecastService weatherForecastService;

    @Override
    public QuestionResponse performSearch(final String question) throws AIAssistantServiceException {

        WeatherAssistant assistant = AiServices.builder(WeatherAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(CHAT_MEMORY_MAX_MESSAGES))
                .tools(weatherForecastService)
                .build();

        return QuestionResponse.builder()
                .answer(assistant.chat(question))
                .build();
    }

}
