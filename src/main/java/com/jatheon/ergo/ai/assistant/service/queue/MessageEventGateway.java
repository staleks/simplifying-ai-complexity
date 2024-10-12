package com.jatheon.ergo.ai.assistant.service.queue;

import com.jatheon.ergo.ai.assistant.model.queue.DocumentUploadEvent;

import java.util.List;

public interface MessageEventGateway {

    List<DocumentUploadEvent> fetchEvents();
    void deleteEvent(final String eventHandle);
}
