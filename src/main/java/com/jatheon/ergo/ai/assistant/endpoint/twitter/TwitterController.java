package com.jatheon.ergo.ai.assistant.endpoint.twitter;

import com.jatheon.ergo.ai.assistant.endpoint.model.ArticleRequest;
import com.jatheon.ergo.ai.assistant.endpoint.model.ArticleResponse;
import com.jatheon.ergo.ai.assistant.service.error.QuestionServiceException;
import com.jatheon.ergo.ai.assistant.service.twitter.TwitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TwitterController {

    private static final String TWITTER_ENDPOINT = "/ai/twitter";

    private final TwitterService twitterService;

    @PostMapping(value = TWITTER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleResponse> question(@RequestBody ArticleRequest request) {
        if (StringUtils.isNotBlank(request.getContent())) {
            try {
                ArticleResponse response = ArticleResponse.builder()
                        .answer(twitterService.convertArticleIntoTwitterThread(request.getContent()))
                        .build();
                return ResponseEntity.ok(response);
            } catch (QuestionServiceException qse) {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }




}
