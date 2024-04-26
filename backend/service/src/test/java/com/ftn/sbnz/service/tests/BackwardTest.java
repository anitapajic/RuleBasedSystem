package com.ftn.sbnz.service.tests;


import com.ftn.sbnz.model.models.Man;
import com.ftn.sbnz.model.models.Woman;
import com.ftn.sbnz.model.models.Parent;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.concurrent.TimeUnit;

public class BackwardTest {

    @Test
    public void testWoman() throws InterruptedException {
        KieSession ksession = prepare();

        ksession.insert("woman");

        ksession.fireAllRules();
    }

    @Test
    public void testMan() throws InterruptedException {
        KieSession ksession = prepare();

        ksession.insert("man");

        ksession.fireAllRules();
    }


    @Test
    public void testParent() throws InterruptedException {
        KieSession ksession = prepare();

        ksession.insert("parent");

        ksession.fireAllRules();
    }


    private KieSession prepare(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("bwKsession");

        // grand parents
        ksession.insert(new Man("john"));
        ksession.insert(new Woman("janet"));
        // parent
        ksession.insert(new Man("adam"));
        ksession.insert(new Parent("john", "adam"));
        ksession.insert(new Parent("janet", "adam"));
        ksession.insert(new Man("stan"));
        ksession.insert(new Parent("john", "stan"));
        ksession.insert(new Parent("janet", "stan"));
        // grand parents
        ksession.insert(new Man("carl"));
        ksession.insert(new Woman("tina"));
        //
        // parent
        ksession.insert(new Woman("eve"));
        ksession.insert(new Parent("carl", "eve"));
        ksession.insert(new Parent("tina", "eve"));
        //
        // parent
        ksession.insert(new Woman("mary"));
        ksession.insert(new Parent("carl", "mary"));
        ksession.insert(new Parent("tina", "mary"));
        ksession.insert(new Man("peter"));
        ksession.insert(new Parent("adam", "peter"));
        ksession.insert(new Parent("eve", "peter"));
        ksession.insert(new Man("paul"));
        ksession.insert(new Parent("adam", "paul"));
        ksession.insert(new Parent("mary", "paul"));
        ksession.insert(new Woman("jill"));
        ksession.insert(new Parent("adam", "jill"));
        ksession.insert(new Parent("eve", "jill"));

        return ksession;
    }


}
