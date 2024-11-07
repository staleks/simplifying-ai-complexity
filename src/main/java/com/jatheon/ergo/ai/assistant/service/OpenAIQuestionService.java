package com.jatheon.ergo.ai.assistant.service;

import com.jatheon.ergo.ai.assistant.model.ChatLanguageModelType;
import com.jatheon.ergo.ai.assistant.model.QuestionResponse;
import com.jatheon.ergo.ai.assistant.service.error.QuestionServiceException;
import com.jatheon.ergo.ai.assistant.service.prompt.PromptFactory;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
public class OpenAIQuestionService implements QuestionService {

    private final PromptFactory promptFactory;
    private final HashMap<ChatLanguageModelType, ChatLanguageModel> chatLanguageModels;

    @Override
    public QuestionResponse performSearch(final ChatLanguageModelType model,
                                          final String question) throws QuestionServiceException {
        var prompt = promptFactory.createPrompt(question);

        ChatLanguageModel chatLanguageModel = chatLanguageModels.get(model);
        Response<AiMessage> answer = chatLanguageModel.generate(prompt.toUserMessage());

        return QuestionResponse.builder()
                .answer(answer.content().text())
                .build();
    }

}
