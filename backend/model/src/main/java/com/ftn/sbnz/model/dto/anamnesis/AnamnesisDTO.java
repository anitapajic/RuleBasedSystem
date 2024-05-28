package com.ftn.sbnz.model.dto.anamnesis;


import java.util.ArrayList;
import java.util.List;

public class AnamnesisDTO {

    private String patientEmail;

    private Integer doctorId;

    private List<Integer> patientsSymptomsIds = new ArrayList<>();

    private Boolean isTestNeeded;

    private String description;

    //region Constructors

    public AnamnesisDTO(String patientEmail, Integer doctorId, List<Integer> patientsSymptomsIds, Boolean isTestNeeded, String description) {
        this.patientEmail = patientEmail;
        this.doctorId = doctorId;
        this.patientsSymptomsIds = patientsSymptomsIds;
        this.isTestNeeded = isTestNeeded;
        this.description = description;
    }

    public AnamnesisDTO() {
    }

    //endregion

    //region Getters and Setters

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public List<Integer> getPatientsSymptomsIds() {
        return patientsSymptomsIds;
    }

    public void setPatientsSymptomsIds(List<Integer> patientsSymptomsIds) {
        this.patientsSymptomsIds = patientsSymptomsIds;
    }

    public Boolean getIsTestNeeded() {
        return isTestNeeded;
    }

    public void setIsTestNeeded(Boolean isTestNeeded) {
        this.isTestNeeded = isTestNeeded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion

}
