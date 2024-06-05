package com.ftn.sbnz.util;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

@Component
@Singleton
public class KieContainerComponent {

    private final KieServices ks = KieServices.Factory.get();
    private final KieContainer kContainer = ks.getKieClasspathContainer();

    public KieContainer getkContainer() {
        return kContainer;
    }
}
