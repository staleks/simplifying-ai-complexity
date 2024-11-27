package com.jatheon.ergo.ai.assistant.model.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.time.Instant;

public class StorageFile {
    private final String bucketName;
    private final String name;
    private final Instant lastModified;
    private final long size;
    private final String etag;

    private StorageFile(final String bucketName, final String name, final Instant lastModified, final long size, final String etag) {
        this.bucketName = bucketName;
        this.name = name;
        this.lastModified = lastModified;
        this.size = size;
        this.etag = etag;
    }

    public static StorageFile of(final String bucketName, final String name, final Instant lastModified, final long size, final String etag) {
        return new StorageFile(bucketName, name, lastModified, size, etag);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return "s3://" + this.bucketName + "/" + this.name;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public long getSize() {
        return size;
    }

    public String getEtag() {
        return etag;
    }

    public String getHumanReadableSize() {
        return humanReadableByteCountBin(size);
    }

    @JsonIgnore
    public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }
}
