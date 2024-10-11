package com.jatheon.ergo.ai.assistant.service.error;

public class AIAssistantServiceException extends RuntimeException {

    private static final long serialVersionUID = 335017367662513808L;

    public AIAssistantServiceException(final String message) {
        super(message);
    }

    public AIAssistantServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
