package com.jatheon.ergo.ai.assistant.model;

public enum ChatLanguageModelType {

    OPEN_AI("OPEN_AI"),
    CLAUDE("CLAUDE");

    private final String modelName;

    ChatLanguageModelType(final String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public static ChatLanguageModelType fromModelName(final String modelName) {
        for (ChatLanguageModelType type : ChatLanguageModelType.values()) {
            if (type.getModelName().equalsIgnoreCase(modelName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown model name: " + modelName);
    }

}
