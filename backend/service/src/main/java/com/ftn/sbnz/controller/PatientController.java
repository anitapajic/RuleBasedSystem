package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.models.user.Patient;
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
}
