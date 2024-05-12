package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import com.ftn.sbnz.model.models.anamnesis.AnamnesisEvaluation;
import com.ftn.sbnz.model.models.symptom.Symptom;
import com.ftn.sbnz.model.models.symptom.enums.SymptomLevel;
import com.ftn.sbnz.service.DiagnosticService;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RulesTest {



    @Test
    public void test() {
        Anamnesis anamnesis = new Anamnesis();
        Set<Symptom> symptoms = new HashSet<>();
        symptoms.add(new Symptom(2, "Povisena temperatura", SymptomLevel.LEVEL_1));
        symptoms.add(new Symptom(3, "Bol u grlu", SymptomLevel.LEVEL_1));
        anamnesis.setPatientsSymptoms(symptoms);

        findDisease(anamnesis);
    }


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

        System.out.println(anamnesisEvaluation.getLevel1Symptoms());
        // videti sta vracati

    }

}
