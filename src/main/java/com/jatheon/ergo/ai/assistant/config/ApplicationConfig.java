package com.jatheon.ergo.ai.assistant.config;

import com.jatheon.ergo.ai.assistant.config.web.RestWebMvcConfig;
import com.jatheon.ergo.ai.assistant.endpoint.PingController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        RestWebMvcConfig.class,
})
@Configuration
public class ApplicationConfig {

    //~ Ping
    @Bean
    PingController pingController() {
        return new PingController();
    }

}
