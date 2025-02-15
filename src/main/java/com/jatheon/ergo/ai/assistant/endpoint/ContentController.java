package com.jatheon.ergo.ai.assistant.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ContentController {

    private static final String CONTENT_PAGE_VIEW = "pages/contentPage";

    @GetMapping("/content")
    public String handleContentPage() {
        return CONTENT_PAGE_VIEW;
    }
}
