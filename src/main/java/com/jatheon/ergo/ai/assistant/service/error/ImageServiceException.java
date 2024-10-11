package com.jatheon.ergo.ai.assistant.service.error;

public class ImageServiceException extends RuntimeException {

    private static final long serialVersionUID = 2563281766347928963L;

    public ImageServiceException(final String message) {
        super(message);
    }

    public ImageServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
