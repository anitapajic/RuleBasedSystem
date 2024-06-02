package com.ftn.sbnz.service.queries;

import com.ftn.sbnz.model.dto.queries.Query1DTO;
import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.model.models.medicine.Medicine;
import com.ftn.sbnz.model.models.therapy.Therapy;
import com.ftn.sbnz.repository.MedicineRepository;
import com.ftn.sbnz.service.diagnosis.DiagnosisService;
import com.ftn.sbnz.service.therapy.TherapyService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private DiagnosisService diagnosisService;
    @Autowired
    private TherapyService therapyService;

    private Query1DTO getReport1(Medicine medicine){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("backwardRulesKsession");
        Query1DTO query1DTO = new Query1DTO();

        List<Therapy> therapies = therapyService.findAll();
        List<Diagnosis> diagnoses = diagnosisService.findAll();

        therapies.forEach(kSession::insert);
        diagnoses.forEach(kSession::insert);
        kSession.insert(medicine);

        kSession.fireAllRules();

        return query1DTO;

    }

}
