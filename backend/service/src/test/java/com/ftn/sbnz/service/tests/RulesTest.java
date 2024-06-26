package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.dto.DiseaseSeverityDTO;
import com.ftn.sbnz.model.dto.SymptomTemplateDTO;
import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import com.ftn.sbnz.model.models.anamnesis.AnamnesisEvaluation;
import com.ftn.sbnz.model.models.confirmationTest.enums.TestType;
import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.symptom.Symptom;
import com.ftn.sbnz.model.models.symptom.enums.SymptomLevel;
import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RulesTest {

    @Test
    public void test() {
        Anamnesis anamnesis = new Anamnesis();
        Set<Symptom> symptoms = new HashSet<>();
        Set<Symptom> diseaseSymptoms = new HashSet<>();
        Set<Symptom> diseaseSymptoms2 = new HashSet<>();
        Set<Disease> diseases = new HashSet<>();

        symptoms.add(new Symptom(1, "Povisena temperatura", SymptomLevel.LEVEL_1));
        symptoms.add(new Symptom(2, "Bol u grlu", SymptomLevel.LEVEL_1));
        symptoms.add(new Symptom(3, "Belicaste tacke na krajnicima", SymptomLevel.LEVEL_2));
        symptoms.add(new Symptom(4, "Zbunjenost ili promene u ponasanju", SymptomLevel.LEVEL_2));
        anamnesis.setPatientsSymptoms(symptoms);

        diseaseSymptoms.add(new Symptom(1, "Povisena temperatura", SymptomLevel.LEVEL_1));
        diseaseSymptoms.add(new Symptom(2, "Bol u grlu", SymptomLevel.LEVEL_1));
        diseaseSymptoms.add(new Symptom(3, "Poteskoce pri gutanju", SymptomLevel.LEVEL_1));
        diseaseSymptoms.add(new Symptom(4, "Belicaste tacke na krajnicima", SymptomLevel.LEVEL_2));
        diseaseSymptoms.add(new Symptom(5, "Crvenilo grla", SymptomLevel.LEVEL_2));
        diseaseSymptoms.add(new Symptom(6, "Bolni limfni cvorovi", SymptomLevel.LEVEL_2));

        Disease disease = new Disease(1, "Streptokokna upala grla", diseaseSymptoms, TestType.RAPID_STREP_TEST);
        diseases.add(disease);

        diseaseSymptoms2.add(new Symptom(1, "Povisena temperatura", SymptomLevel.LEVEL_1));
        diseaseSymptoms2.add(new Symptom(7, "Dijareja", SymptomLevel.LEVEL_1));
        diseaseSymptoms2.add(new Symptom(8, "Bolovi u stomaku", SymptomLevel.LEVEL_1));
        diseaseSymptoms2.add(new Symptom(9, "Povracanje", SymptomLevel.LEVEL_2));

        Disease disease2 = new Disease(2, "Salmoneloza", diseaseSymptoms2, TestType.SPUTUM_TEST);
        diseases.add(disease2);

        findDisease(anamnesis, diseases);
    }

    @Test
    public void testTemplates(){
        InputStream template = RulesTest.class.getResourceAsStream("/rules/forward/disease-severity-template.drt");
//        List<SymptomTemplateDTO>  symptomTemplateDTOS = new ArrayList<>();
//        symptomTemplateDTOS.add(new SymptomTemplateDTO("1", "Povisena temperatura"));
//        symptomTemplateDTOS.add(new SymptomTemplateDTO("1", "Bol u grlu"));
//        symptomTemplateDTOS.add(new SymptomTemplateDTO("2", "AAAAAAAA"));

        List<DiseaseSeverityDTO> diseaseSeverityDTOS = new ArrayList<>();
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.0, 0.2, 1));
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.2, 0.4, 2));
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.4, 0.6, 3));
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.6, 0.8, 4));
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.8, 1.0, 5));
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(diseaseSeverityDTOS, template);

        System.out.println(drl);

    }


    private KieSession getKieSession(String session) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        return kContainer.newKieSession(session);
    }

    public void findDisease(Anamnesis anamnesis, Set<Disease> diseases){
        KieSession kieSession = getKieSession("rulesKsession");

        kieSession.insert(anamnesis);
        for(Disease disease: diseases){
            kieSession.insert(disease);
        }
        AnamnesisEvaluation anamnesisEvaluation = new AnamnesisEvaluation();
        kieSession.insert(anamnesisEvaluation);

        kieSession.fireAllRules();
        kieSession.dispose();

    }

}
