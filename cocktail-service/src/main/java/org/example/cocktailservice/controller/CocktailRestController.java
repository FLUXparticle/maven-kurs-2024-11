package org.example.cocktailservice.controller;

import org.example.cocktailmodel.Cocktail;
import org.example.cocktailmodel.Ingredient;
import org.example.cocktailmodel.Instruction;
import org.example.cocktailservice.service.CocktailService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/rest")
public class CocktailRestController {

    private final CocktailService cocktailService;

    public CocktailRestController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping(path = "/cocktails")
    public Collection<Cocktail> getAllCocktails() {
        return cocktailService.getAllCocktails().stream()
                .sorted(Comparator.comparing(Cocktail::getName))
                .toList();
    }

    @GetMapping("/cocktails/{id}")
    public Collection<Instruction> cocktail(@PathVariable Long id) {
        return cocktailService.getInstructions(id);
    }

    @GetMapping("/cocktails/search")
    public Collection<Cocktail> searchCocktails(@RequestParam String query) {
        return cocktailService.search(query);
    }

    @PostMapping("/possible")
    public Collection<Cocktail> getPossibleRecipes(@RequestBody Map<String, List<Long>> payload) {
        List<Long> ingredientIDs = payload.getOrDefault("ingredientIDs", emptyList());
        return cocktailService.getPossibleCocktails(new HashSet<>(ingredientIDs));
    }

    @GetMapping("/ingredients")
    public Collection<Ingredient> getAllIngredients() {
        return cocktailService.getAllIngredients();
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredient(@PathVariable Long id) {
        return cocktailService.getIngredientWithID(id);
    }

    @GetMapping("/ingredients/{id}/cocktails")
    public Collection<Cocktail> ingredient(@PathVariable Long id) {
        return cocktailService.getAllCocktailsWithIngredient(id);
    }

}
