package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.medicine.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Ingredient findByName(String name);
}
