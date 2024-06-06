package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.events.DiarrheaEvent;
import com.ftn.sbnz.model.events.OxygenEvent;
import com.ftn.sbnz.model.events.TemperatureEvent;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.concurrent.TimeUnit;


public class CEPConfigTest {

    @Test
    public void test() throws InterruptedException {
         KieServices ks = KieServices.Factory.get();
         KieContainer kContainer = ks.getKieClasspathContainer();
         KieSession ksession = kContainer.newKieSession("cepRulesKsession");

         SessionPseudoClock clock = ksession.getSessionClock();

         ksession.insert(new TemperatureEvent(1, 39.0, "Anita"));
         clock.advanceTime(10, TimeUnit.SECONDS);
         ksession.fireAllRules();

         ksession.insert(new TemperatureEvent(1, 39.5, "Anita"));
         clock.advanceTime(1, TimeUnit.HOURS);
         ksession.fireAllRules();

         ksession.insert(new TemperatureEvent(1, 39.5, "Anita"));
         clock.advanceTime(3, TimeUnit.HOURS);
         ksession.fireAllRules();
    }

    @Test
    public void testOxygen(){
         KieServices ks = KieServices.Factory.get();
         KieContainer kContainer = ks.getKieClasspathContainer();
         KieSession ksession = kContainer.newKieSession("cepRulesKsession");

         SessionPseudoClock clock = ksession.getSessionClock();

         ksession.insert(new OxygenEvent(1, 89.5, "Mile"));
         ksession.insert(new OxygenEvent(1, 99.5, "Mile"));
         ksession.insert(new OxygenEvent(1, 92.5, "Mile"));
         ksession.fireAllRules();

         clock.advanceTime(2, TimeUnit.HOURS);
         ksession.insert(new OxygenEvent(1, 89.5, "Mile"));
         ksession.insert(new OxygenEvent(1, 99.5, "Mile"));
         ksession.insert(new OxygenEvent(1, 92.5, "Mile"));
         ksession.fireAllRules();

         clock.advanceTime(2, TimeUnit.HOURS);
         ksession.insert(new OxygenEvent(1, 80.5, "Mile"));
         ksession.insert(new OxygenEvent(1, 90.5, "Mile"));
         ksession.insert(new OxygenEvent(1, 90.5, "Mile"));
         ksession.fireAllRules();
    }

     @Test
     public void testDiarrhea(){
          KieServices ks = KieServices.Factory.get();
          KieContainer kContainer = ks.getKieClasspathContainer();
          KieSession ksession = kContainer.newKieSession("cepRulesKsession");

          SessionPseudoClock clock = ksession.getSessionClock();

          ksession.insert(new DiarrheaEvent(1, "Pera"));
          ksession.insert(new DiarrheaEvent(1, "Pera"));
          ksession.insert(new DiarrheaEvent(1, "Pera"));
          ksession.insert(new DiarrheaEvent(1, "Pera"));
          ksession.insert(new DiarrheaEvent(1, "Pera"));

          ksession.fireAllRules();
     }

}
