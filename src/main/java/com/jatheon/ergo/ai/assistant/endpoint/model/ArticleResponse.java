package com.jatheon.ergo.ai.assistant.endpoint.model;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ArticleResponse {
    @Getter
    private final String answer;
}
