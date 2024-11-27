package com.jatheon.ergo.ai.assistant.model.storage;

import lombok.Value;

@Value(staticConstructor = "of")
public class DocumentMetadata {
    Long contentLength;
    String contentType;
}
