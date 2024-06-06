package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.dto.anamnesis.AnamnesisDTO;
import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import com.ftn.sbnz.model.models.anamnesis.AnamnesisEvaluation;
import com.ftn.sbnz.service.DiagnosticService;
import com.ftn.sbnz.service.anamnesis.AnamnesisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/anamnesis")
public class AnamnesisController {
    @Autowired
    private AnamnesisService anamnesisService;
    @Autowired
    private DiagnosticService diagnosticService;

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/{patientId}")
    public ResponseEntity<?> findAllByPatientId(@Valid @PathVariable Integer patientId){
        return ResponseEntity.ok(anamnesisService.findAllByPatientId(patientId));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Integer id){
        return ResponseEntity.ok(anamnesisService.findById(id));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> save(@Valid @RequestBody AnamnesisDTO anamnesisDTO){
        Anamnesis anamnesis = anamnesisService.save(anamnesisDTO);
        AnamnesisEvaluation anamnesisEvaluation = diagnosticService.findDisease(anamnesis);
        return ResponseEntity.ok(anamnesisEvaluation);
    }

}
