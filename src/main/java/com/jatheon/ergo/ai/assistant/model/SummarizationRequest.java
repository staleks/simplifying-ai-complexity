package com.jatheon.ergo.ai.assistant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummarizationRequest {
    @NotEmpty
    @JsonProperty("etag")
    private String eTag;
}
