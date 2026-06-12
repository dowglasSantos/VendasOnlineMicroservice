package com.app.config.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Bean {
    @org.springframework.context.annotation.Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
