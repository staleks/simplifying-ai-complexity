package com.jatheon.ergo.ai.assistant.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.jatheon.ergo.ai.assistant.model.storage.ReducedEmbedding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class EmbeddingStoreRepositoryImpl implements EmbeddingStoreRepository {

    private static final String METADATA_ETAG_FIELD = "metadata.eTag";
    private static final String INDEX_NAME = "embeddings";

    private final ElasticsearchClient esClient;

    @Override
    public List<String> fetchAllEmbeddingsByAttachment(final String eTag) {
        List<String> result = new ArrayList<>();
        Query byETag = MatchQuery.of(m ->
                m.field(METADATA_ETAG_FIELD)
                        .query(eTag)
        )._toQuery();
        SearchRequest searchRequest = SearchRequest.of(builder -> builder
                .index(INDEX_NAME)
                .query(q -> q.bool(b -> b.must(byETag))));
        try {
            SearchResponse<ReducedEmbedding> response = esClient.search(searchRequest, ReducedEmbedding.class);
            TotalHits total = response.hits().total();
            if (total == null) {
                log.debug("Embeddings - no hits found");
                return result;
            }
            List<Hit<ReducedEmbedding>> hits = response.hits().hits();
            for (Hit<ReducedEmbedding> hit : hits) {
                ReducedEmbedding reducedEmbedding = hit.source();
                log.trace("ReducedEmbedding > text: {}", reducedEmbedding.getText());
                result.add(reducedEmbedding.getText());
            }
        } catch (IOException | ElasticsearchException e) {
            log.error("Error while searching for embeddings", e);
        }
        return result;
    }
}
