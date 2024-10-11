package com.jatheon.ergo.ai.assistant.service;

import com.jatheon.ergo.ai.assistant.model.QuestionResponse;
import com.jatheon.ergo.ai.assistant.service.error.AIAssistantServiceException;

public interface QuestionService {

    QuestionResponse performSearch(final String question) throws AIAssistantServiceException;

}
