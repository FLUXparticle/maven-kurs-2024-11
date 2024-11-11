package org.example.cocktailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.example.cocktailmodel")
public class CocktailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CocktailServiceApplication.class, args);
    }
}
