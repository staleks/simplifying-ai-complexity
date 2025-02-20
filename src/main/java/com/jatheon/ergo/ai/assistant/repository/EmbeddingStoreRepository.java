package com.jatheon.ergo.ai.assistant.repository;

import java.util.List;

public interface EmbeddingStoreRepository {
    List<String> fetchAllEmbeddingsByAttachment(final String eTag);
}
