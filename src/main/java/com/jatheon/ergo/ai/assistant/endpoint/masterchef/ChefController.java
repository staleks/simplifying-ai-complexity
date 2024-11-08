package com.jatheon.ergo.ai.assistant.endpoint.masterchef;

import com.jatheon.ergo.ai.assistant.endpoint.masterchef.model.ChefQuestion;
import com.jatheon.ergo.ai.assistant.endpoint.masterchef.model.RecipeRequest;
import com.jatheon.ergo.ai.assistant.model.QuestionResponse;
import com.jatheon.ergo.ai.assistant.service.masterchef.MasterChefService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChefController {

    private static final String CHEF_RECIPE_ENDPOINT = "/ai/chef-recipe";
    private static final String CHEF_QUESTION_ENDPOINT = "/ai/chef-question";

    private final MasterChefService masterChefService;

    @PostMapping(value = CHEF_RECIPE_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponse> chefRecipe(
            @RequestHeader(name = "X-Chat-Language-Model", defaultValue = "OPEN_AI") String chatLanguageModel,
            @RequestBody RecipeRequest request) {
        return ResponseEntity.ok(QuestionResponse.builder().answer(masterChefService.prepareRecipeBasedOnIngredients(request.getIngredients())).build());
    }

    @PostMapping(value = CHEF_QUESTION_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponse> chefQuestion(
            @RequestHeader(name = "X-Chat-Language-Model", defaultValue = "OPEN_AI") String chatLanguageModel,
            @RequestBody ChefQuestion request) {
        return ResponseEntity.ok(QuestionResponse.builder().answer("Chef is cooking").build());
    }


}
