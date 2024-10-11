package com.jatheon.ergo.ai.assistant.endpoint.ping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PingController {

    private static final String PING_ENDPOINT = "/ai/ping";

    @GetMapping(value = PING_ENDPOINT)
    public ResponseEntity<String> handle() {
        return ResponseEntity.ok("Pong.");
    }

}
