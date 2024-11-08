package com.jatheon.ergo.ai.assistant.model.masterchef;

import dev.langchain4j.model.output.structured.Description;

import java.util.HashSet;

public class Recipe {

    @Description("The short title of the recipe, 3 words maximum")
    private String title;

    @Description("The short description of the recipe, 3 sentences maximum")
    private String description;

    @Description("Each step should be described in maximum 5 words")
    private HashSet<String> steps = new HashSet<>();

    private int preparationTimeMinutes;

    @Override
    public String toString() {
        return "Recipe {" +
                " title = \"" + title + "\"" +
                ", description = \"" + description + "\"" +
                ", steps = " + steps +
                ", preparationTimeMinutes = " + preparationTimeMinutes +
                " }";
    }
}
