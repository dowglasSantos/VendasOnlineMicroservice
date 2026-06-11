package com.app.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class SaleBean {
    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
