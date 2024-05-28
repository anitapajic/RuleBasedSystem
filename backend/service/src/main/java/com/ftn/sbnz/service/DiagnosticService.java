package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import com.ftn.sbnz.model.models.anamnesis.AnamnesisEvaluation;
import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.service.disease.DiseaseService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DiagnosticService {

    @Autowired
    private DiseaseService diseaseService;

    private KieSession getKieSession(String session) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        return kContainer.newKieSession(session);
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


        return kieSessionObjects.stream()
                .filter(object -> AnamnesisEvaluation.class.isAssignableFrom(object.getClass()))
                .map(AnamnesisEvaluation.class::cast).toList()
                .get(0);
    }

}
