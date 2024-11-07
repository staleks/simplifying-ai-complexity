package com.jatheon.ergo.ai.assistant.model;

public enum ChatLanguageModelType {

    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_4O("gpt-4o"),
    CLAUDE("claude");

    private final String modelName;

    ChatLanguageModelType(final String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }
}
