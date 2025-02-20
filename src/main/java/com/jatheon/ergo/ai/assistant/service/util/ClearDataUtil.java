package com.jatheon.ergo.ai.assistant.service.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClearDataUtil {

    public ClearDataUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String clearData(String data) {
        log.trace("result > 0: {}", data);
        String result = data;
        if (data.startsWith("\"")) {
            result = data.substring(1, data.length() - 1);
        }
        log.trace("result > 1: {}", result);
        if (result.endsWith("\"")) {
            result = result.substring(0, data.length() - 1);
        }
        log.trace("result > 2: {}", result);
        return result;
    }


}
