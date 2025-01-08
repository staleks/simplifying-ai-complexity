package com.jatheon.ergo.ai.assistant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {

    private Long contentLength;
    private String contentType;
    private String index;
    private String source;

}
