package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class DiarrheaEvent {

    private Integer patientId;
    private String patientName;

    public DiarrheaEvent(Integer patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
    }

    public DiarrheaEvent() {
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
