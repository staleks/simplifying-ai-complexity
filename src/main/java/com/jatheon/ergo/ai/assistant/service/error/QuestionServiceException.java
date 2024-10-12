package com.jatheon.ergo.ai.assistant.service.error;

public class QuestionServiceException extends RuntimeException {

    private static final long serialVersionUID = 335017367662513808L;

    public QuestionServiceException(final String message) {
        super(message);
    }

    public QuestionServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
