package com.jatheon.ergo.ai.assistant.model.storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReducedMetadata {
    private Long contentLength;
    private String contentType;
    private String source;
}
