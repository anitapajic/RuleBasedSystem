package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Role(Role.Type.EVENT)
public class TemperatureEvent {
    private Integer patientId;
    private Double temperatureValue;
    private String patientName;

    public TemperatureEvent(Integer patientId, Double temperatureValue, String patientName) {
        this.patientId = patientId;
        this.temperatureValue = temperatureValue;
        this.patientName = patientName;
    }
    public TemperatureEvent(){}

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
