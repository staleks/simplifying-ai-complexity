package com.jatheon.ergo.ai.assistant.endpoint.masterchef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChefQuestion {
    @JsonProperty("question")
    private String question;
}
