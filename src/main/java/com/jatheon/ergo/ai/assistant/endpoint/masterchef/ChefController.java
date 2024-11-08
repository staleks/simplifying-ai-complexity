package com.jatheon.ergo.ai.assistant.endpoint.masterchef;

import com.jatheon.ergo.ai.assistant.model.QuestionResponse;
import com.jatheon.ergo.ai.assistant.service.masterchef.MasterChefService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChefController {

    private static final String CHEF_ENDPOINT = "/ai/chef-recipe";

    private final MasterChefService masterChefService;

    @PostMapping(value = CHEF_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponse> question(
            @RequestHeader(name = "X-Chat-Language-Model", defaultValue = "OPEN_AI") String chatLanguageModel) {
        masterChefService.doSomething();
        return ResponseEntity.ok(QuestionResponse.builder().answer("Chef is cooking").build());
    }

}
