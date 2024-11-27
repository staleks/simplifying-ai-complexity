package com.jatheon.ergo.ai.assistant.service.storage.parser;

import dev.langchain4j.data.document.DocumentParser;

public interface DocumentParserFactory {
    DocumentParser create(final String contentType);
}
