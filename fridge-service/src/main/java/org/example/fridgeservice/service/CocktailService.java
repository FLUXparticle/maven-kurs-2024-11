package org.example.fridgeservice.service;

import org.example.cocktailmodel.Cocktail;
import org.example.cocktailmodel.Ingredient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CocktailService {

    private final WebClient webClient;

    public CocktailService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Cocktail> getAllCocktails() {
        return webClient.get()
                .uri("/rest/cocktails")
                .retrieve()
                .bodyToFlux(Cocktail.class);
    }

    public Flux<Ingredient> getAllIngredients() {
        return webClient.get()
                .uri("/rest/ingredients")
                .retrieve()
                .bodyToFlux(Ingredient.class);
    }

    public Mono<Ingredient> getIngredientWithID(Long id) {
        return webClient.get()
                .uri("/rest/ingredients/" + id)
                .retrieve()
                .bodyToMono(Ingredient.class);
    }

    public Flux<Cocktail> getPossibleCocktails(Collection<Long> ingredientIDs) {
        // Erstelle das Anfrage-Body-Objekt
        Map<String, Collection<Long>> requestBody = new HashMap<>();
        requestBody.put("ingredientIDs", ingredientIDs);

        // Führe den POST-Request aus
        return webClient.post()
                .uri("/rest/possible")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(Cocktail.class);
    }

}
