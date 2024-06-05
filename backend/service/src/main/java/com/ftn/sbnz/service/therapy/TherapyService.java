package com.ftn.sbnz.service.therapy;

import com.ftn.sbnz.controller.ReportController;
import com.ftn.sbnz.model.models.backwardModel.BackwardModel;
import com.ftn.sbnz.model.models.backwardModel.BackwardType;
import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.medicine.Ingredient;
import com.ftn.sbnz.model.models.medicine.Medicine;
import com.ftn.sbnz.model.models.therapy.Therapy;
import com.ftn.sbnz.model.models.therapy.TherapyEvaluation;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.model.models.utils.DateRange;
import com.ftn.sbnz.repository.TherapyRepository;
import com.ftn.sbnz.service.diagnosis.DiagnosisService;
import com.ftn.sbnz.service.disease.DiseaseService;
import com.ftn.sbnz.util.KieContainerComponent;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TherapyService {
    @Autowired
    TherapyRepository therapyRepository;
    @Autowired
    DiagnosisService diagnosisService;
    @Autowired
    DiseaseService diseaseService;
    @Autowired
    KieContainerComponent kieContainerComponent;


    public Therapy findById(Integer id){
        return therapyRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This therapy doesn't exist"));
    }

    public List<Therapy> findAll(){
        return therapyRepository.findAll();
    }

    public List<Patient> findAllPatientsByActiveTherapy(Integer diseaseId){
        if (diseaseId == -1) return therapyRepository.findAllPatientsWithActiveTherapy(LocalDate.now());
        return therapyRepository.findAllPatientsWithActiveTherapyForDisease(diseaseId, LocalDate.now());
    }

    private KieSession getKieSession(String session) {
        return kieContainerComponent.getkContainer().newKieSession(session);
    }

    public Therapy findTherapy(Diagnosis diagnosis){

        KieSession kieSession = getKieSession("therapyKsession");
        kieSession.insert(diagnosis);
        Disease disease = diagnosis.getDisease();

        //Pozivanje backwarda
        List<Medicine> medicines = getMedicines(disease, diagnosis.getPatient());
        if (medicines.isEmpty()) {
            throw new RuntimeException("No suitable medicine found for the patient.");
        }

        Medicine medicine = medicines.get(0);
        kieSession.insert(medicine);

        TherapyEvaluation therapyEvaluation = new TherapyEvaluation();
        therapyEvaluation.setMedicinePrescribed(false);
        therapyEvaluation.setPatientAge(LocalDate.now().getYear() - diagnosis.getPatient().getBirthDate().getYear());
        therapyEvaluation.setDiseaseLevel(diagnosis.getDiseaseLevel());
        kieSession.insert(therapyEvaluation);

        kieSession.fireAllRules();

        final Collection<?> kieSessionObjects = kieSession.getObjects(object -> object instanceof TherapyEvaluation);

        TherapyEvaluation therapyEvaluation1 = kieSessionObjects.stream()
                .filter(object -> TherapyEvaluation.class.isAssignableFrom(object.getClass()))
                .map(TherapyEvaluation.class::cast).toList()
                .get(0);

        kieSession.dispose();

        return save(therapyEvaluation1, diagnosis, medicine);

    }

    public Therapy save(TherapyEvaluation therapyEvaluation, Diagnosis diagnosis, Medicine medicine){
        Therapy therapy = new Therapy();
        therapy.setFrequency(therapyEvaluation.getFrequency());
        therapy.setMilligrams(therapyEvaluation.getMilligrams());
        therapy.setDiagnosis(diagnosis);
        therapy.setMedicine(medicine);
        therapy.setDateRange(new DateRange(LocalDate.now(), LocalDate.now().plusDays(14)));
        return therapyRepository.save(therapy);
    }

    public List<Medicine> getMedicines(Disease disease, Patient patient) {
        KieSession ksession = kieContainerComponent.getkContainer().newKieSession("backwardRulesKsession");

        List<Medicine> medicines = disease.getMedicines().stream().toList();

        if(patient.getAllergens().size()>0){
            for (Ingredient allergen : patient.getAllergens()) {
                medicines = filterMedicinesByAllergen(medicines, allergen.getName(), disease, ksession);
            }
        }


        return medicines;
    }

    private List<Medicine> filterMedicinesByAllergen(List<Medicine> medicines, String allergen, Disease disease, KieSession ksession) {

        ksession.setGlobal("ingredientGlobal", allergen);
        List<String> medicineList = new ArrayList<>();
        ksession.setGlobal("medicineList", medicineList);

        for (Medicine medicine : medicines) {
            ksession.insert(new BackwardModel(medicine.getName(), disease.getName(), BackwardType.DISEASE));
        }
        for (Medicine medicine : medicines) {
            for (Ingredient ingredient : medicine.getIngredients()) {
                ksession.insert(new BackwardModel(ingredient.getName(), medicine.getName(), BackwardType.MEDICINE));
            }
        }

        ksession.fireAllRules();

        List<Medicine> filteredMedicines = medicines.stream()
                .filter(medicine -> medicineList.contains(medicine.getName()))
                .collect(Collectors.toList());

        ksession.dispose();

        return filteredMedicines;
    }

    public List<Therapy> findAllTherapiesByPatientId(Integer patientId) {
        List<Diagnosis> diagnoses = diagnosisService.findAllByPatientId(patientId);
        List<Therapy> therapies = new ArrayList<>();

        for (Diagnosis diagnosis : diagnoses) {
            List<Therapy> therapyList = therapyRepository.findAllByDiagnosisId(diagnosis.getId());
            therapies.addAll(therapyList);
        }
        System.out.println(therapies.size());

        return therapies;
    }

}

