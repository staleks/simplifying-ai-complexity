package com.jatheon.ergo.ai.assistant.service;

import com.jatheon.ergo.ai.assistant.model.ChatLanguageModelType;
import com.jatheon.ergo.ai.assistant.model.QuestionResponse;
import com.jatheon.ergo.ai.assistant.service.error.QuestionServiceException;

public interface QuestionService {

    QuestionResponse performSearch(final ChatLanguageModelType model,
                                   final String question) throws QuestionServiceException;
}