package com.jatheon.ergo.ai.assistant.service.masterchef;

import com.jatheon.ergo.ai.assistant.model.ChatLanguageModelType;
import com.jatheon.ergo.ai.assistant.model.QuestionResponse;
import com.jatheon.ergo.ai.assistant.model.masterchef.Chef;
import com.jatheon.ergo.ai.assistant.model.masterchef.Recipe;
import com.jatheon.ergo.ai.assistant.service.error.QuestionServiceException;
import com.jatheon.ergo.ai.assistant.service.prompt.PromptFactory;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class MasterChefServiceImpl implements MasterChefService {

    private final PromptFactory promptFactory;
    private final Map<ChatLanguageModelType, ChatLanguageModel> chatLanguageModels;

    @Override
    public String prepareRecipeBasedOnIngredients(final List<String> ingredients) {
        log.debug("Preparing recipe based on ingredients: {}", String.join(", ", ingredients));
        ChatLanguageModel chatLanguageModel = chatLanguageModels.get(ChatLanguageModelType.OPEN_AI);
        Chef chef = AiServices.create(Chef.class, chatLanguageModel);
        Recipe recipe = chef.createRecipe(ingredients.stream().toArray(String[]::new));
        return recipe.toString();
    }

    @Override
    public QuestionResponse explain(final String recipe) {
        String finalAnswer = StringUtils.EMPTY;
        ChatLanguageModel chatLanguageModel = chatLanguageModels.get(ChatLanguageModelType.OPEN_AI);
        var prompt = promptFactory.createPrompt(recipe);
        if (chatLanguageModel != null) {
            Response<AiMessage> answer = chatLanguageModel.generate(prompt.toUserMessage());
            if (answer != null) {
                finalAnswer = answer.content().text();
            } else {
                throw new QuestionServiceException("Failed to get answer from LLM");
            }
        }
        return QuestionResponse.builder()
                .answer(finalAnswer)
                .build();
    }

}
