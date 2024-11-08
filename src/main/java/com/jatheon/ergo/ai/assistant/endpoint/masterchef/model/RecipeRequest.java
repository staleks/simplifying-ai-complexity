package com.jatheon.ergo.ai.assistant.endpoint.masterchef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeRequest {
    @JsonProperty("ingredients")
    private List<String> ingredients;
}
