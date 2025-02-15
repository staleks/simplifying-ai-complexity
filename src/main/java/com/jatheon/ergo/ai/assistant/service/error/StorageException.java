package com.jatheon.ergo.ai.assistant.service.error;

public class StorageException extends RuntimeException {
    public static final String UNABLE_TO_READ_FOR_BUCKET = "Unable to read message content from AWS storage [bucket: %s]";
    public static final String UNABLE_TO_READ_FOR_BUCKET_AND_LOCATION = "Unable to read message content from AWS storage [bucket: %s, key: %s]";
    public static final String UNABLE_TO_STORE_FILE = "Unable to store file in AWS storage [bucket: %s, fileName: %s]";
    public StorageException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
