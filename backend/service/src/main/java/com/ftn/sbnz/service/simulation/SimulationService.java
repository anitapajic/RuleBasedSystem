package com.ftn.sbnz.service.simulation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftn.sbnz.model.events.OxygenEvent;
import com.ftn.sbnz.model.events.TemperatureEvent;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.service.therapy.TherapyService;
import com.ftn.sbnz.util.KieContainerComponent;
import com.ftn.sbnz.util.SimulationUtils;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SimulationService {

    private KieSession ksession;

    private SessionPseudoClock clock;

    private TherapyService therapyService;

    private KieContainerComponent kieContainerComponent;

    private SimpMessagingTemplate simpMessagingTemplate;

    private ObjectMapper jsonMapper;

    @Autowired
    public SimulationService(TherapyService therapyService,
                             KieContainerComponent kieContainerComponent,
                             SimpMessagingTemplate simpMessagingTemplate,
                             ObjectMapper jsonMapper) {
        this.therapyService = therapyService;
        this.kieContainerComponent = kieContainerComponent;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.jsonMapper = jsonMapper;
        ksession = this.kieContainerComponent.getkContainer().newKieSession("cepRulesKsession");

        clock = ksession.getSessionClock();
    }

    @Scheduled(initialDelayString = "${simulateTemp.initialdelay}", fixedRateString = "${simulateTemp.fixedrate}")
    private void temperatureSimulation(){

        List<Patient> patients = therapyService.findAllPatientsByActiveTherapy(-1);
        patients.forEach(patient -> {
            Double temperature = SimulationUtils.generateTemperature();
            TemperatureEvent temperatureEvent = new TemperatureEvent(patient.getId(), temperature, patient.getName());
            ksession.insert(temperatureEvent);
            try {
                String payload = jsonMapper.writeValueAsString(temperatureEvent);
                simpMessagingTemplate.convertAndSend("/temperature/all", payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Simulating temp for" + patient.getName() + " -> " + temperature );
        });
        ksession.fireAllRules();
        clock.advanceTime(3, TimeUnit.HOURS);
    }

    @Scheduled(initialDelayString = "${simulateOxygen.initialdelay}", fixedRateString = "${simulateOxygen.fixedrate}")
    private void oxygenSimulation(){
        List<Patient> patients = therapyService.findAllPatientsByActiveTherapy(2);
        patients.forEach(patient -> {
            Double saturation = SimulationUtils.generateOxygenSaturation();
            OxygenEvent oxygenEvent = new OxygenEvent(patient.getId(), saturation, patient.getName());
            ksession.insert(oxygenEvent);
            try {
                String payload = jsonMapper.writeValueAsString(oxygenEvent);
                simpMessagingTemplate.convertAndSend("/oxygen/all", payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Simulating oxygen for" + patient.getName() + " -> " + oxygenEvent );
        });
        clock.advanceTime(1, TimeUnit.MINUTES);
    }

    @Scheduled(initialDelayString = "${checkOxygen.initialdelay}", fixedRateString = "${checkOxygen.fixedrate}")
    private void oxygenCheck(){
        ksession.fireAllRules();
        clock.advanceTime(1, TimeUnit.HOURS);
        clock.advanceTime(1, TimeUnit.MINUTES);
    }

}
