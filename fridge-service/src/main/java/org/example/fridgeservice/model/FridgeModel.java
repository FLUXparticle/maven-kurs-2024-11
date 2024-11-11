package org.example.fridgeservice.model;

import org.example.cocktailmodel.Ingredient;

public class FridgeModel {

    private final Ingredient ingredient;

    private final boolean inFridge;

    public FridgeModel(Ingredient ingredient, boolean inFridge) {
        this.ingredient = ingredient;
        this.inFridge = inFridge;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public boolean isInFridge() {
        return inFridge;
    }

}
