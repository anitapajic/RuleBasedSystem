package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import com.ftn.sbnz.model.models.anamnesis.AnamnesisEvaluation;
import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.service.diagnosis.DiagnosisService;
import com.ftn.sbnz.service.disease.DiseaseService;
import com.ftn.sbnz.service.user.PatientService;
import com.ftn.sbnz.util.KieContainerComponent;
import com.ftn.sbnz.util.KieSessionUtils;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Service
public class DiagnosticService {

    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private DiagnosisService diagnosisService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private KieContainerComponent kieContainerComponent;

    private KieSession getKieSession(String session) {
        return kieContainerComponent.getkContainer().newKieSession(session);
    }

    public AnamnesisEvaluation findDisease(Anamnesis anamnesis){
        KieSession kieSession = getKieSession("rulesKsession");
        kieSession.insert(anamnesis);

        AnamnesisEvaluation anamnesisEvaluation = new AnamnesisEvaluation();
        anamnesisEvaluation.setSymptoms(anamnesis.getPatientsSymptoms().stream().toList());
        anamnesisEvaluation.setTestNeeded(anamnesis.getIsTestNeeded());

        kieSession.insert(anamnesisEvaluation);

        for(Disease disease : diseaseService.findAll()){
            kieSession.insert(disease);
        }

        kieSession.fireAllRules();


        final Collection<?> kieSessionObjects = kieSession.getObjects(object -> object instanceof AnamnesisEvaluation);

        AnamnesisEvaluation anamnesisEvaluation1 = kieSessionObjects.stream()
                .filter(object -> AnamnesisEvaluation.class.isAssignableFrom(object.getClass()))
                .map(AnamnesisEvaluation.class::cast).toList()
                .get(0);

        if(anamnesisEvaluation1.isConfirmationTestResult()){
            int severityLevel = 0;

            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setDisease(diseaseService.findByName(anamnesisEvaluation1.getPossibleDiseaseName()));
            diagnosis.setPatient(anamnesis.getPatient());

            KieSession severitySession = KieSessionUtils.getDiseaseSeveritySession();
            severitySession.insert(diagnosis);
            severitySession.insert(anamnesis);
            severitySession.fireAllRules();

            System.out.println("Disease level: " + diagnosis.getDiseaseLevel());
            diagnosis.setTimestamp(LocalDateTime.now());
            Diagnosis savedDiagnosis = diagnosisService.save(diagnosis);

            Patient patient = anamnesis.getPatient();
            Set<Diagnosis> diagnosisPatient = patient.getDiagnosis();
            diagnosisPatient.add(savedDiagnosis);
            patient.setDiagnosis(diagnosisPatient);
            patientService.save(patient);

        }
        return anamnesisEvaluation1;
    }

}
