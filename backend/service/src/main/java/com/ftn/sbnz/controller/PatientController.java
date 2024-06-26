package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.dto.user.AddAllergenDTO;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.service.therapy.TherapyService;
import com.ftn.sbnz.service.user.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private TherapyService therapyService;

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Patient> patients = patientService.findAll();
            return ResponseEntity.ok(patients);
        }
        catch(Exception e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }

    @PreAuthorize("hasAnyRole('PATIENT')")
    @PutMapping(value = "/allergens")
    public ResponseEntity<?> addAllergen(@RequestBody AddAllergenDTO addAllergenDTO) {
        try {
            Patient patient = patientService.addAllergens(addAllergenDTO);
            return ResponseEntity.ok(patient);
        }
        catch(Exception e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping("/with-disease/{diseaseId}")
    public ResponseEntity<?> getAllWithActiveDisease(@PathVariable Integer diseaseId) {
        try {
            List<Patient> patients = therapyService.findAllPatientsByActiveTherapy(diseaseId);
            return ResponseEntity.ok(patients);
        }
        catch(Exception e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }
}
