package org.example.fridgeservice.controller;

import org.example.cocktailmodel.Cocktail;
import org.example.cocktailmodel.Ingredient;
import org.example.fridgeservice.model.FridgeIngredient;
import org.example.fridgeservice.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/fridge")
public class FridgeController {

    private final FridgeService fridgeService;

    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    // Endpoint zum Abrufen aller Zutaten im Kühlschrank
    @GetMapping("/ingredients")
    public Flux<FridgeIngredient> getFridgeIngredients() {
        return fridgeService.getFridgeIngredients();
    }

    // Endpoint zum Aktualisieren des Status einer Zutat im Kühlschrank
    @PatchMapping("/ingredients/{id}")
    public void updateIngredientStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> payload) {
        boolean inFridge = payload.getOrDefault("inFridge", false);
        fridgeService.updateIngredientStatus(id, inFridge);
    }

    // Endpoint zum Abrufen möglicher Cocktails
    @GetMapping("/possible")
    public Flux<Cocktail> getPossibleCocktails() {
        return fridgeService.getPossibleCocktails();
    }

    // Endpoint zum Abrufen der Einkaufsliste
    @GetMapping("/shopping")
    public Flux<Ingredient> getShoppingList() {
        return fridgeService.getShoppingList();
    }

}
