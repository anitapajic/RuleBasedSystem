package com.ftn.sbnz.controller;

import com.ftn.sbnz.service.disease.DiseaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/{name}")
    public ResponseEntity<?> findAllByPatientId(@Valid @PathVariable String name){
        return ResponseEntity.ok(diseaseService.findByName(name));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Integer id){
        return ResponseEntity.ok(diseaseService.findById(id));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(diseaseService.findAll());
    }

}
