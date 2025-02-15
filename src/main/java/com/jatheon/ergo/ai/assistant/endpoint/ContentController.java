package com.jatheon.ergo.ai.assistant.endpoint;

import com.jatheon.ergo.ai.assistant.service.error.StorageException;
import com.jatheon.ergo.ai.assistant.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContentController {

    private static final String CONTENT_PAGE_VIEW = "pages/contentPage";

    private final StorageService storageService;

    @GetMapping("/content")
    public String handleContentPage() {
        return CONTENT_PAGE_VIEW;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        /**
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }
         **/
        try {
            storageService.uploadFile(file, file.getOriginalFilename());
            return "redirect:/content";
        } catch (final StorageException e) {
            log.error("Failed to upload file: {}", file.getOriginalFilename());
            return "redirect:/content";
            /**
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + file.getOriginalFilename());
             **/
        }
    }

}
