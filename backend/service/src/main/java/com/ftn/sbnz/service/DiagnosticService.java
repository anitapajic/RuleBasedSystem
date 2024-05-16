package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import com.ftn.sbnz.model.models.anamnesis.AnamnesisEvaluation;
import com.ftn.sbnz.service.anamnesis.AnamnesisService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticService {

    @Autowired
    private AnamnesisService anamnesisService;

    private KieSession getKieSession(String session) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        return kContainer.newKieSession(session);
    }

    public void findDisease(Anamnesis anamnesis){
        //na osnovu anamneze pronaci najverovatniju bolest
        KieSession kieSession = getKieSession("rulesKsession");
        kieSession.insert(anamnesis);
        AnamnesisEvaluation anamnesisEvaluation = new AnamnesisEvaluation();
        kieSession.insert(anamnesisEvaluation);

        kieSession.fireAllRules();
        kieSession.dispose();

        // videti sta vracati

    }

}
