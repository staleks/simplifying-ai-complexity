package com.jatheon.ergo.ai.assistant.service.masterchef;

import com.jatheon.ergo.ai.assistant.model.ChatLanguageModelType;
import com.jatheon.ergo.ai.assistant.model.masterchef.Chef;
import com.jatheon.ergo.ai.assistant.model.masterchef.Recipe;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class MasterChefServiceImpl implements MasterChefService {

    private final Map<ChatLanguageModelType, ChatLanguageModel> chatLanguageModels;

    @Override
    public void doSomething() {
        ChatLanguageModel chatLanguageModel = chatLanguageModels.get(ChatLanguageModelType.OPEN_AI);

        Chef chef = AiServices.create(Chef.class, chatLanguageModel);
        Recipe recipe = chef.createRecipe("cucumber", "tomato", "feta", "onion", "olives");

    }
}
