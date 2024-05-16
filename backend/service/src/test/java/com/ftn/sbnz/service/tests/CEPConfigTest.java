package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.events.Landing;
import com.ftn.sbnz.model.events.TemperatureEvent;
import com.ftn.sbnz.model.events.TransactionEvent;
import com.ftn.sbnz.model.models.Account;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
// import org.kie.api.KieServices;
// import org.kie.api.runtime.KieContainer;
// import org.kie.api.runtime.KieSession;



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

}
