package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.service.diagnosis.DiagnosisService;
import com.ftn.sbnz.service.therapy.TherapyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/therapy")
public class TherapyController {
    @Autowired
    private TherapyService therapyService;
    @Autowired
    private DiagnosisService diagnosisService;

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/{patientEmail}")
    public ResponseEntity<?> findTherapy(@Valid @PathVariable String patientEmail){
        Diagnosis diagnosis = diagnosisService.findLastByPatientEmail(patientEmail);
        return ResponseEntity.ok(therapyService.findTherapy(diagnosis));
    }

    @PreAuthorize("hasAnyRole('PATIENT')")
    @GetMapping(value = "/all/{patientId}")
    public ResponseEntity<?> findAllByPatientId(@Valid @PathVariable Integer patientId){
        return ResponseEntity.ok(therapyService.findAllTherapiesByPatientId(patientId));
    }
}
