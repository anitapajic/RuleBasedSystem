package com.ftn.sbnz.service.queries;

import com.ftn.sbnz.model.dto.queries.Query1DTO;
import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.model.models.medicine.Ingredient;
import com.ftn.sbnz.model.models.medicine.Medicine;
import com.ftn.sbnz.model.models.symptom.Symptom;
import com.ftn.sbnz.model.models.therapy.Therapy;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.service.anamnesis.AnamnesisService;
import com.ftn.sbnz.service.diagnosis.DiagnosisService;
import com.ftn.sbnz.service.ingredient.IngredientService;
import com.ftn.sbnz.service.medicine.MedicineService;
import com.ftn.sbnz.service.symptom.SymptomService;
import com.ftn.sbnz.service.therapy.TherapyService;
import com.ftn.sbnz.service.user.PatientService;
import com.ftn.sbnz.util.KieContainerComponent;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReportService {
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private DiagnosisService diagnosisService;
    @Autowired
    private TherapyService therapyService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private SymptomService symptomService;
    @Autowired
    private AnamnesisService anamnesisService;
    @Autowired
    private KieContainerComponent kieContainerComponent;

    private KieSession getKieSession(String session) {
        return kieContainerComponent.getkContainer().newKieSession(session);
    }

    public Query1DTO getReport1(Integer medicineId){
        KieSession kSession = getKieSession("backwardRulesKsession");
        Query1DTO query1DTO = new Query1DTO();

        List<Therapy> therapies = therapyService.findAll();
        List<Diagnosis> diagnoses = diagnosisService.findAll();
        List<Patient> patients = patientService.findAll();
        Medicine medicine = medicineService.findById(medicineId);

        therapies.forEach(kSession::insert);
        diagnoses.forEach(kSession::insert);
        kSession.insert(medicine);

        kSession.fireAllRules();

        // Execute the query to find patients using the specified medicine
        QueryResults results = kSession.getQueryResults("PatientsForMedicine", medicine);

        Set<Patient> uniquePatients = new HashSet<>();
        for (QueryResultsRow row : results) {
            Patient patient = (Patient) row.get("$patient");
            uniquePatients.add(patient);
        }

        // Convert the set of unique patients to a list
        List<Patient> patientList = new ArrayList<>(uniquePatients);
        Double sizeOfPatients = (double) patientList.size();
        Double sizeOfAllPatients = (double) patients.size();

        // Set the results in query1DTO or process as needed
        query1DTO.setNumOfPatients(patientList.size());
        query1DTO.setPercentage((sizeOfPatients/sizeOfAllPatients)*100);

        kSession.dispose();

        return query1DTO;

    }

    public Query1DTO getReport2(Integer ingredientId){
        KieSession kSession = getKieSession("backwardRulesKsession");
        Query1DTO query1DTO = new Query1DTO();

        List<Patient> patients = patientService.findAll();
        List<Ingredient> ingredients = ingredientService.findAll();
        Ingredient allergen = ingredientService.findById(ingredientId);

        patients.forEach(kSession::insert);
        ingredients.forEach(kSession::insert);
        kSession.insert(allergen);

        kSession.fireAllRules();

        QueryResults results = kSession.getQueryResults("PatientsForAllergen", allergen);

        Set<Patient> uniquePatients = new HashSet<>();
        for (QueryResultsRow row : results) {
            Patient patient = (Patient) row.get("$patient");
            uniquePatients.add(patient);
        }

        List<Patient> patientList = new ArrayList<>(uniquePatients);

        Double sizeOfPatients = (double) patientList.size();
        Double sizeOfAllPatients = (double) patients.size();

        query1DTO.setNumOfPatients(patientList.size());
        query1DTO.setPercentage((sizeOfPatients/sizeOfAllPatients)*100);

        kSession.dispose();

        return query1DTO;

    }

    public Query1DTO getReport3(Integer symptomId, LocalDateTime startDate, LocalDateTime endDate){
        KieSession kSession = getKieSession("backwardRulesKsession");
        Query1DTO query1DTO = new Query1DTO();

        Symptom symptom = symptomService.findById(symptomId);
        List<Anamnesis> anamneses = anamnesisService.findAll();

        kSession.insert(symptom);
        anamneses.forEach(kSession::insert);
        if (startDate == null) {
            startDate = LocalDateTime.MIN;
        }
        if (endDate == null) {
            endDate = LocalDateTime.MAX;
        }
        kSession.insert(startDate);
        kSession.insert(endDate);

        kSession.fireAllRules();

        QueryResults results = kSession.getQueryResults("AnamnesesWithSymptomInPeriod", symptom, startDate, endDate);

        Set<Patient> uniquePatients = new HashSet<>();
        for (QueryResultsRow row : results) {
            Anamnesis anamnesis = (Anamnesis) row.get("$anamnesis");
            Patient patient = anamnesis.getPatient();
            if (patient != null) {
                uniquePatients.add(patient);
            }
        }

        List<Patient> patientList = new ArrayList<>(uniquePatients);
        List<Patient> patients = patientService.findAll();

        Double sizeOfPatients = (double) patientList.size();
        Double sizeOfAllPatients = (double) patients.size();

        query1DTO.setNumOfPatients(patientList.size());
        query1DTO.setPercentage((sizeOfPatients/sizeOfAllPatients)*100);

        kSession.dispose();

        return query1DTO;

    }

}
