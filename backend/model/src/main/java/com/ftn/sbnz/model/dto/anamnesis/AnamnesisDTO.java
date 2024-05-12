package com.ftn.sbnz.model.dto.anamnesis;

import com.ftn.sbnz.model.models.symptom.Symptom;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnamnesisDTO {

    private Integer patientId;

    private Integer doctorId;

    private List<Integer> patientsSymptomsIds = new ArrayList<>();

    private Boolean isTestNeeded;

    private String description;

    //region Constructors

    public AnamnesisDTO(Integer patientId, Integer doctorId, List<Integer> patientsSymptomsIds, Boolean isTestNeeded, String description) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientsSymptomsIds = patientsSymptomsIds;
        this.isTestNeeded = isTestNeeded;
        this.description = description;
    }

    public AnamnesisDTO() {
    }

    //endregion

    //region Getters and Setters

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

    public Boolean getTestNeeded() {
        return isTestNeeded;
    }

    public void setTestNeeded(Boolean testNeeded) {
        isTestNeeded = testNeeded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion

}
