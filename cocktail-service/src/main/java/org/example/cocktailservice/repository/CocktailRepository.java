package org.example.cocktailservice.repository;

import org.example.cocktailmodel.Cocktail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CocktailRepository extends CrudRepository<Cocktail, Long> {

    @NonNull
    Collection<Cocktail> findAll();

    Collection<Cocktail> findByNameContains(String query);

    Collection<Cocktail> findDistinctByInstructionsIngredientIdIn(Collection<Long> ingredientIDs);

}
