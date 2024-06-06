package com.ftn.sbnz.model.dto;

public class EmergencyMessageDTO {

    private Integer patientId;
    private String message;
    private String patientName;
    private EmergencyType emergencyType;

    public EmergencyMessageDTO(Integer patientId, String message, String patientName, EmergencyType emergencyType) {
        this.patientId = patientId;
        this.message = message;
        this.patientName = patientName;
        this.emergencyType = emergencyType;
    }

    public EmergencyMessageDTO() {
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmergencyType getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(EmergencyType emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
