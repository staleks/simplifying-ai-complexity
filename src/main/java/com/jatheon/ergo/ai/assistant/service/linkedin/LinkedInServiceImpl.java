package com.jatheon.ergo.ai.assistant.service.linkedin;

import com.jatheon.ergo.ai.assistant.model.WritingStyle;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LinkedInServiceImpl implements LinkedInService {

    private final ChatLanguageModel chatLanguageModel;

    @Override
    public String generatePost(final String article) {
        String systemMessageTemplate = String.format("Please ignore all previous instructions. Please respond only in the English language. " +
                "You are an expert content creator on LinkedIn and would like to convert your article into an engaging LinkedIn post. " +
                "You have a Casual tone of voice. You have a %s writing style. Do not self reference. Do not explain what you are doing. " +
                "Add emojis to the thread when appropriate. Please add relevant hashtags to the post and encourage the LinkedIn users to join " +
                "the conversation.", WritingStyle.TECHNICAL);
        SystemMessage systemMessage = SystemMessage.from(systemMessageTemplate);
        UserMessage userMessage = UserMessage.from(String.format("Please turn the following article into a LinkedIn post: %s", article));
        Response<AiMessage> response = chatLanguageModel.generate(systemMessage, userMessage);
        return response.content().text();
    }

}
