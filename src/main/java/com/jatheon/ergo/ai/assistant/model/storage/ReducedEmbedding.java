package com.jatheon.ergo.ai.assistant.model.storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReducedEmbedding {
    private String text;
    private ReducedMetadata metadata;
}
