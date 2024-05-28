package com.ftn.sbnz.controller;

import com.ftn.sbnz.service.symptom.SymptomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/symptom")
public class SymptomController {
    @Autowired
    private SymptomService symptomService;

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/{diseaseName}")
    public ResponseEntity<?> findByDiseaseName(@Valid @PathVariable String diseaseName){
        return ResponseEntity.ok(symptomService.findSymptomsByDiseaseName(diseaseName));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/{diseaseId}")
    public ResponseEntity<?> findByDiseaseId(@Valid @PathVariable Integer diseaseId){
        return ResponseEntity.ok(symptomService.findSymptomsByDiseaseId(diseaseId));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Integer id){
        return ResponseEntity.ok(symptomService.findById(id));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(symptomService.findAll());
    }
}
