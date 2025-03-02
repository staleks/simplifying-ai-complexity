package com.jatheon.ergo.ai.assistant.endpoint;

import com.jatheon.ergo.ai.assistant.model.SummarizationRequest;
import com.jatheon.ergo.ai.assistant.model.SummarizationResponse;
import com.jatheon.ergo.ai.assistant.service.SummarizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SummarizationController {

    private static final String SUMMARIZATION_ENDPOINT = "/ai/get-summary";

    private final SummarizationService summarizationService;

    @PostMapping(value = SUMMARIZATION_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SummarizationResponse> question(@Valid @RequestBody SummarizationRequest request) {
        try{
            return ResponseEntity.ok(SummarizationResponse.of(summarizationService.summarize(request.getETag())));
        } catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

}
