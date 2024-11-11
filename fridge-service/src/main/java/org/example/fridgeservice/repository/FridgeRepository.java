package org.example.fridgeservice.repository;

import org.example.fridgeservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.lang.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface FridgeRepository extends JpaRepository<FridgeIngredient, Long> {

    @NonNull
    Optional<FridgeIngredient> findById(@NonNull Long ingredientId);

    List<FridgeIngredient> findByInFridge(boolean inFridge);

}
