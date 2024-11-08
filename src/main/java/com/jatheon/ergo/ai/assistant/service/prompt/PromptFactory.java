package com.jatheon.ergo.ai.assistant.service.prompt;

import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.HashMap;
import java.util.Map;

public class PromptFactory {

    private final String POLITE_PROMPT_TEMPLATE = "You are a professional chef. You are friendly, polite and concise. "
            + "Answer the following question to the best of your ability:\n"
            + "\n"
            + "Question:\n"
            + "{{question}}\n"
            + "\n"
            + "If you don't know the answer be sure to redirect where this information can be found. "
            + "Also, if there is some URLs that can be used to get that information state them.";

    /**
     * Create a prompt for the given question.
      * @param question
     * @return
     */
    public Prompt createPrompt(final String question) {
        PromptTemplate promptTemplate = PromptTemplate.from(POLITE_PROMPT_TEMPLATE);
        Map<String, Object> variables = new HashMap<>();
        variables.put("question", question);
        return promptTemplate.apply(variables);
    }

}
