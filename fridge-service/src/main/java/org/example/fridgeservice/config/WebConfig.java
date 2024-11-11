package org.example.fridgeservice.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Angular-Client-URL
                .allowedMethods("GET", "PATCH")
                .allowedHeaders("*");
    }

}
