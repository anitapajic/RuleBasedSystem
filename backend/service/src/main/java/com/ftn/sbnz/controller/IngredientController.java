package com.ftn.sbnz.controller;

import com.ftn.sbnz.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(ingredientService.findAll());
    }
}
