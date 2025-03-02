package com.jatheon.ergo.ai.assistant.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SummarizationResponse {

    private final String summary;

    public static SummarizationResponse of(final String summary) {
        return new SummarizationResponse(summary);
    }

}
