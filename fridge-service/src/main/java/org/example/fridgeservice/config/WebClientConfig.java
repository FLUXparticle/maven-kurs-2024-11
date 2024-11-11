package org.example.fridgeservice.config;

import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.*;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081") // URL des cocktail-service
                .build();
    }

}
