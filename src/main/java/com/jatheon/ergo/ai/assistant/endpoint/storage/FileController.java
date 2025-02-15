package com.jatheon.ergo.ai.assistant.endpoint.storage;

import com.jatheon.ergo.ai.assistant.model.storage.StorageFile;
import com.jatheon.ergo.ai.assistant.service.error.StorageException;
import com.jatheon.ergo.ai.assistant.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final StorageService storageService;

    @GetMapping("/list")
    public ResponseEntity<List<StorageFile>> listFiles() {
        try {
            return ResponseEntity.ok(storageService.fetchAll());
        } catch (final StorageException storageException) {
            log.error("Failed to fetch files from bucket!", storageException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
