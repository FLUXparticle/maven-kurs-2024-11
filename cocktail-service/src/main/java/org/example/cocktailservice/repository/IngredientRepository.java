package org.example.cocktailservice.repository;

import org.example.cocktailmodel.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Collection<Ingredient> findAll();

    Collection<Ingredient> findByNameContains(String query);

}
