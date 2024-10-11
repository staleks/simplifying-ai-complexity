package com.jatheon.ergo.ai.assistant.model;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class QuestionResponse {
    private final String answer;
}
