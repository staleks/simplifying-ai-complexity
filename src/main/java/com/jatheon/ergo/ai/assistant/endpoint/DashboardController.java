package com.jatheon.ergo.ai.assistant.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class DashboardController {

    private static final String DASHBOARD_PAGE_VIEW = "pages/dashboardPage";

    @GetMapping("/")
    public String handleDashboardPage() {
        return DASHBOARD_PAGE_VIEW;
    }
}
