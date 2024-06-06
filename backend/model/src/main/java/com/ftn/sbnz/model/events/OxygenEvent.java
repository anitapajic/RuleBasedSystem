package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class OxygenEvent {
    private Integer patientId;
    private Double oxygenSaturationPercentage;
    private String patientName;

    public OxygenEvent(Integer patientId, Double oxygenSaturationPercentage, String patientName) {
        this.patientId = patientId;
        this.oxygenSaturationPercentage = oxygenSaturationPercentage;
        this.patientName = patientName;
    }

    public OxygenEvent() {
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Double getOxygenSaturationPercentage() {
        return oxygenSaturationPercentage;
    }

    public void setOxygenSaturationPercentage(Double oxygenSaturationPercentage) {
        this.oxygenSaturationPercentage = oxygenSaturationPercentage;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
