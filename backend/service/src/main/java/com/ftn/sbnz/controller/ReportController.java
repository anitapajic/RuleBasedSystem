package com.ftn.sbnz.controller;

import com.ftn.sbnz.service.queries.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/report1/{medicineId}")
    public ResponseEntity<?> getReport1(@Valid @PathVariable Integer medicineId){
        return ResponseEntity.ok(reportService.getReport1(medicineId));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/report2/{ingredientId}")
    public ResponseEntity<?> getReport2(@Valid @PathVariable Integer ingredientId){
        return ResponseEntity.ok(reportService.getReport2(ingredientId));
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping(value = "/report3/{symptomId}")
    public ResponseEntity<?> getReport3(@Valid @PathVariable Integer symptomId,
                                        @RequestParam(value = "startDate", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                        @RequestParam(value = "endDate", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return ResponseEntity.ok(reportService.getReport3(symptomId, startDate, endDate));
    }
}
