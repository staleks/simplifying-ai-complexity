package com.jatheon.ergo.ai.assistant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Embedding {

    private Metadata metadata;
    private String text;

}
