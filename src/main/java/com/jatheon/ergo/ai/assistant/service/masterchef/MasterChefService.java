package com.jatheon.ergo.ai.assistant.service.masterchef;

import com.jatheon.ergo.ai.assistant.model.QuestionResponse;

import java.util.List;

public interface MasterChefService {

    String prepareRecipeBasedOnIngredients(final List<String> ingredients);

    QuestionResponse explain(final String recipe);
}
