package org.example.fridgeservice.service;

import org.example.cocktailmodel.Cocktail;
import org.example.cocktailmodel.Ingredient;
import org.example.fridgeservice.model.FridgeIngredient;
import org.example.fridgeservice.repository.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class FridgeService {

    private final CocktailService cocktailService;

    private final FridgeRepository fridgeRepository;

    @Autowired
    public FridgeService(CocktailService cocktailService, FridgeRepository fridgeRepository) {
        this.cocktailService = cocktailService;
        this.fridgeRepository = fridgeRepository;
    }

    // Methode zum Abrufen aller Zutaten im Kühlschrank
    public Flux<FridgeIngredient> getFridgeIngredients() {
        Set<Long> fridgeIngredientIds = getFridgeIngredientIds();

        return cocktailService.getAllIngredients()
                .map(ingredient -> {
                    Long ingredientId = ingredient.getId();
                    String name = ingredient.getName();
                    boolean inFridge = fridgeIngredientIds.contains(ingredientId);
                    return new FridgeIngredient(ingredientId, name, inFridge);
                });
    }

    // Methode zum Aktualisieren des Status einer Zutat im Kühlschrank
    public void updateIngredientStatus(Long ingredientId, boolean inFridge) {
        FridgeIngredient fridgeIngredient = new FridgeIngredient(ingredientId, inFridge);
        fridgeRepository.save(fridgeIngredient);
    }

    // Methode zum Abrufen möglicher Cocktails basierend auf den Zutaten im Kühlschrank
    public Flux<Cocktail> getPossibleCocktails() {
        // Hole alle Zutaten, die im Kühlschrank sind
        List<FridgeIngredient> fridgeIngredients = fridgeRepository.findByInFridge(true);
        List<Long> ingredientIDs = fridgeIngredients.stream()
                .map(FridgeIngredient::getId)
                .toList();

        if (ingredientIDs.isEmpty()) {
            return Flux.empty();
        }

        return cocktailService.getPossibleCocktails(ingredientIDs);
    }

    private Set<Long> getFridgeIngredientIds() {
        // Hole alle Zutaten, die im Kühlschrank sind
        return fridgeRepository.findByInFridge(true).stream()
            .map(FridgeIngredient::getId)
            .collect(toSet());
    }

    // Methode zum Abrufen der Einkaufsliste
    public Flux<Ingredient> getShoppingList() {
        // Hole die Zutaten, die im Kühlschrank sind
        Set<Long> fridgeIngredientIds = getFridgeIngredientIds();

        // Hole alle Zutaten aus dem cocktail-service und filtere diejenigen, die nicht im Kühlschrank sind
        return cocktailService.getAllIngredients()
                .filter(ingredient -> !fridgeIngredientIds.contains(ingredient.getId()));
    }

}
