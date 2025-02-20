package com.jatheon.ergo.ai.assistant.service;

import com.jatheon.ergo.ai.assistant.repository.EmbeddingStoreRepository;
import com.jatheon.ergo.ai.assistant.service.prompt.PromptFactory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class SummarizationServiceImpl implements SummarizationService {

    private static final String SUMMARY_EXCUSE_ANSWER = "I'm sorry, can not summary. Can I assist You by any other means?";

    private final EmbeddingStoreRepository embeddingStoreRepository;
    private final ChatLanguageModel chatLanguageModel;

    @Override
    public String summarize(final String eTag) {
        log.info("Summarizing ... eTag: {}", eTag);
        String generatedAnswer = SUMMARY_EXCUSE_ANSWER;
        List<String> textItems = embeddingStoreRepository.fetchAllEmbeddingsByAttachment(eTag);
        Prompt summarizePrompt = PromptFactory.summarizePrompt(textItems);
        if (summarizePrompt != null) {
            generatedAnswer = chatLanguageModel.generate(summarizePrompt.text());
            log.trace("Answer: {}", generatedAnswer);
        }
        return generatedAnswer;
    }

}
