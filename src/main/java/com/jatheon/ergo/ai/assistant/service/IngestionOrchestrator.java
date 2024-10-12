package com.jatheon.ergo.ai.assistant.service;

import com.jatheon.ergo.ai.assistant.model.queue.DocumentUploadEvent;
import com.jatheon.ergo.ai.assistant.service.file.StorageService;
import com.jatheon.ergo.ai.assistant.service.queue.MessageEventGateway;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class IngestionOrchestrator {

    private final MessageEventGateway messageEventGateway;
    private final StorageService storageService;

    private final EmbeddingStoreIngestor embeddingStoreIngestor;

    @Scheduled(fixedRateString = "${processing.message.fetch.rate}")
    public void processEvent() {
        log.info("Fetching messages from SQS");
        List<DocumentUploadEvent> events = messageEventGateway.fetchEvents();
        events.forEach(event -> {
            log.info("Received message: {}", event);
            // Process the message
            Document document = storageService.load(event.getObjectKey());
            log.info("Loaded document");
            embeddingStoreIngestor.ingest(document);
            log.info("Ingest document");
            messageEventGateway.deleteEvent(event.getEventId());
            log.info("Deleted message: {}", event);
        });
    }

}
