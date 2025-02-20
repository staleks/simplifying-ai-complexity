package com.jatheon.ergo.ai.assistant.service.prompt;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

@Slf4j
public final class PromptFactory {

    private PromptFactory() {
        throw new AssertionError("This class should not be instantiated.");
    }

    /**
     * Create a prompt for the given question and relevant embeddings.
     * @param question
     * @param embeddingMatches
     * @return {@link Prompt}
     */
    public static Prompt ragPrompt(final String question, final List<EmbeddingMatch<TextSegment>> embeddingMatches) {
        // Create a prompt for the model that includes question and relevant embeddings
        String RAG_PROMPT_TEMPLATE = "You are a helpful AI assistant. Use the following pieces of context to answer the user's question. "
                + "If you don't know the answer, just say that you don't know. Don't try to make up an answer.\n"
                + "Answer the following question to the best of your ability:\n"
                + "{{question}}\n"
                + "\n"
                + "Context:\n"
                + "{{context}}\n";
        PromptTemplate promptTemplate = PromptTemplate.from(RAG_PROMPT_TEMPLATE);
        String context = embeddingMatches.stream()
                .map(match -> match.embedded().text())
                .collect(joining("\n\n"));
        Map<String, Object> variables = new HashMap<>();
        variables.put("question", question);
        variables.put("context", context);
        return promptTemplate.apply(variables);
    }

    public static Prompt summarizePrompt(final List<String> textSegments) {
        if (textSegments == null || textSegments.isEmpty()) {
            return null;
        }
        String SUMMARIZATION_PROMPT_TEMPLATE = "As a professional summarizer, create a concise and comprehensive summary" +
                " of the provided text, be it an article, post, conversation, or passage, while adhering to these guidelines: \n\n" +
                "1. Craft a summary that is detailed, thorough, in-depth, and complex, while maintaining clarity and conciseness.\n" +
                "2. Incorporate main ideas and essential information, eliminating extraneous language and focusing on critical aspects.\n" +
                "3. Rely strictly on the provided text, without including external information.\n" +
                "4. Format the summary in paragraph form for easy understanding.\n" +
                "5. Ensure the summary is free from any personal opinions or biases, as the objective is to provide a comprehensive and accurate summary.\n" +
                "\n\nText: {{text}}";
        PromptTemplate promptTemplate = PromptTemplate.from(SUMMARIZATION_PROMPT_TEMPLATE);
        StringBuilder sb = new StringBuilder();
        for (String content : textSegments) {
            sb.append(content).append("\n");
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put("text", sb.toString());
        Prompt prompt = promptTemplate.apply(variables);
        log.trace("Summarization prompt: {}", prompt.text());
        return prompt;
    }

}
