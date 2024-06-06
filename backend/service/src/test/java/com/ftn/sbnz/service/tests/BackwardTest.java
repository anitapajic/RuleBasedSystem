package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.models.backwardModel.BackwardModel;
import com.ftn.sbnz.model.models.backwardModel.BackwardType;
import com.ftn.sbnz.model.models.medicine.Medicine;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;


public class BackwardTest {

    @Test
    public void test() throws InterruptedException {
        KieSession ksession = prepare();
        ksession.fireAllRules();
    }

    private KieSession prepare(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("backwardRulesKsession");
        ksession.setGlobal("ingredientGlobal", "Sastojak S1");

        ksession.insert(new BackwardModel("Lek A", "Infekcija", BackwardType.DISEASE));
        ksession.insert(new BackwardModel("Lek B", "Infekcija", BackwardType.DISEASE));
        ksession.insert(new BackwardModel("Lek C", "Infekcija", BackwardType.DISEASE));

        ksession.insert(new BackwardModel("Sastojak S1", "Lek A", BackwardType.MEDICINE));
        ksession.insert(new BackwardModel("Sastojak S2", "Lek A", BackwardType.MEDICINE));

        ksession.insert(new BackwardModel("Sastojak S2", "Lek B", BackwardType.MEDICINE));

        ksession.insert(new BackwardModel("Sastojak S1", "Lek C", BackwardType.MEDICINE));
        ksession.insert(new BackwardModel("Sastojak S2", "Lek C", BackwardType.MEDICINE));

        return ksession;
    }



}
