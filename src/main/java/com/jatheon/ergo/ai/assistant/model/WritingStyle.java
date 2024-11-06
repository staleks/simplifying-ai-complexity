package com.jatheon.ergo.ai.assistant.model;

public enum WritingStyle {
    DEFAULT("Default"),
    ACADEMIC("Academic"),
    ANALYTICAL("Analytical"),
    ARGUMENTATIVE("Argumentative"),
    CASUAL("Casual"),
    CONVERSATIONAL("Conversational"),
    CREATIVE("Creative"),
    CRITICAL_THINKING("Critical Thinking"),
    DESCRIPTIVE("Descriptive"),
    EPIGRAMMATIC("Epigrammatic"),
    EPISTOLARY("Epistolary"),
    EXPOSITORY("Expository"),
    INFORMATIVE("Informative"),
    INSTRUCTIVE("Instructive"),
    JOURNALISTIC("Journalistic"),
    METAPHORICAL("Metaphorical"),
    NARRATIVE("Narrative"),
    PERSUASIVE("Persuasive"),
    POETIC("Poetic"),
    SATIRICAL("Satirical"),
    SCIENTIFIC("Scientific"),
    SIMPLIFIED("Simplified"),
    TECHNICAL("Technical"),
    TRANSLATIONAL("Translational");

    private String style;

    WritingStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

}
