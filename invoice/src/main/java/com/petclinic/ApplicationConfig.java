package com.petclinic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfig {
    @Value("${petclinic.endpoint}")
    private String endpointUrl;

    @Bean
    public RestClient restClient() {
        return RestClient.create(endpointUrl);
    }
}
