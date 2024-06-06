package com.ftn.sbnz.util;

import com.ftn.sbnz.model.dto.DiseaseSeverityDTO;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class KieSessionUtils {

    private static KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }

    public static KieSession getDiseaseSeveritySession(){
        InputStream template = KieSessionUtils.class.
                getResourceAsStream("/rules/forward/disease-severity-template.drt");

        List<DiseaseSeverityDTO> diseaseSeverityDTOS = new ArrayList<>();
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.0, 0.2, 1));
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.2, 0.4, 2));
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.4, 0.6, 3));
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.6, 0.8, 4));
        diseaseSeverityDTOS.add(new DiseaseSeverityDTO(0.8, 1.0, 5));
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(diseaseSeverityDTOS, template);

        return createKieSessionFromDRL(drl);
    }

}
