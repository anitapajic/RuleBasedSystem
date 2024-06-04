package com.ftn.sbnz.service.ingredient;

import com.ftn.sbnz.model.models.medicine.Ingredient;
import com.ftn.sbnz.repository.IngredientRepository;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient findByName(String name){
        return ingredientRepository.findByName(name);
    }

    public Ingredient findById(Integer id){
        return ingredientRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This ingredient doesn't exist"));
    }

    public List<Ingredient> findAll(){
        return  ingredientRepository.findAll();
    }


}
