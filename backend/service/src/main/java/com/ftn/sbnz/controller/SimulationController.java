package com.ftn.sbnz.controller;

import com.ftn.sbnz.service.simulation.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/simulation")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @GetMapping("/diarrhea")
    public ResponseEntity<?> simulateDiarrhea(){
        simulationService.triggerIntravenousFluids();
        return ResponseEntity.ok("");
    }

}
