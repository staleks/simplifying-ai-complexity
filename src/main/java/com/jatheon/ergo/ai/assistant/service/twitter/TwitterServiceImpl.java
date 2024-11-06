package com.jatheon.ergo.ai.assistant.service.twitter;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TwitterServiceImpl implements TwitterService {

    private final ChatLanguageModel chatLanguageModel;

    @Override
    public String convertArticleIntoTwitterThread(final String article) {
        SystemMessage systemMessage = SystemMessage.from("Please ignore all previous instructions. Please respond only in the English language. You are a professional copywriter and would like to convert your article into an engaging Twitter thread. You have a Casual tone of voice. You have a Academic writing style. Do not self reference. Do not explain what you are doing. Add emojis to the thread when appropriate. The character count for each thread should be between 270 to 280 characters. Please add relevant hashtags to the post and encourage the Twitter users to join the conversation.");
        UserMessage userMessage = UserMessage.from(String.format("Please turn the following article into a Twitter thread: %s", article));
        Response<AiMessage> response = chatLanguageModel.generate(systemMessage, userMessage);
        return response.content().text();
    }
}
