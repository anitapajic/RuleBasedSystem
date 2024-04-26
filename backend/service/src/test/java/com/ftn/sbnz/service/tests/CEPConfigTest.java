package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.events.Landing;
import com.ftn.sbnz.model.events.TransactionEvent;
import com.ftn.sbnz.model.models.Account;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Date;
import java.util.concurrent.TimeUnit;
// import org.kie.api.KieServices;
// import org.kie.api.runtime.KieContainer;
// import org.kie.api.runtime.KieSession;



public class CEPConfigTest {

    @Test
    public void test() throws InterruptedException {
         KieServices ks = KieServices.Factory.get();
         KieContainer kContainer = ks.getKieClasspathContainer();
         KieSession ksession = kContainer.newKieSession("cepKsession");

         SessionPseudoClock clock = ksession.getSessionClock();

         ksession.insert(new Landing("let1"));
         clock.advanceTime(5, TimeUnit.SECONDS);
         ksession.insert(new Landing("let2"));

         ksession.fireAllRules();

    }


    @Test
    public void testZadatak1() throws InterruptedException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("cepKsession");

        SessionPseudoClock clock = ksession.getSessionClock();

        ksession.insert(new TransactionEvent(1L, 30.0, "tr1"));
        Thread.sleep(2000);
        ksession.insert(new TransactionEvent(2L, 30.0, "tr2"));
        Thread.sleep(2000);
        ksession.insert(new TransactionEvent(3L, 30.0, "tr3"));

        ksession.fireAllRules();

    }

    @Test
    public void testZadatak2() throws InterruptedException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("cepKsession");

        Account acc = new Account(1L, 1000.0);
        ksession.insert(acc);
        ksession.insert(new TransactionEvent(1L, 451.0, "tr1"));
        Thread.sleep(5000);
        ksession.insert(new TransactionEvent(1L, 451.0, "tr2"));

        ksession.fireAllRules();

    }
}
