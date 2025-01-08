package com.jatheon.ergo.ai.assistant.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.jatheon.ergo.ai.assistant.model.Embedding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class VectorStoreRepository {

    private final ElasticsearchClient esClient;

    public void search() {
        log.info("Search ...");
        SearchRequest searchRequest = SearchRequest.of(builder -> builder.index("embeddings"));
        try {
            SearchResponse<Embedding> response = esClient.search(searchRequest, Embedding.class);
            TotalHits total = response.hits().total();
            log.info("Total hits: {}", total.value());
            List<Hit<Embedding>> hits = response.hits().hits();
            for (Hit<Embedding> hit: hits) {
                Embedding embedding = hit.source();
                log.info("Found embedding > att.contentType: {}", embedding.getMetadata().getContentType());
                log.info("Found embedding > att.contentLength: {}", embedding.getMetadata().getContentLength());
                log.info("Found embedding > att.source " + embedding.getMetadata().getSource());
            }
        } catch (Exception ex) {
            log.error("Error searching", ex);
        }

    }

}
