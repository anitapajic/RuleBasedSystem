package com.ftn.sbnz.model.models.anamnesis;

import com.ftn.sbnz.model.models.symptom.Symptom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AnamnesisEvaluation implements Serializable {

    private List<Symptom> symptoms = new ArrayList<>();

    private Integer level1Symptoms;

    private Integer level2Symptoms;

    private boolean bloodAnalysisNeeded;

    private boolean testNeeded;

    public AnamnesisEvaluation(List<Symptom> symptoms, Integer level1Symptoms, Integer level2Symptoms, boolean bloodAnalysisNeeded, boolean testNeeded) {
        this.symptoms = symptoms;
        this.level1Symptoms = level1Symptoms;
        this.level2Symptoms = level2Symptoms;
        this.bloodAnalysisNeeded = bloodAnalysisNeeded;
        this.testNeeded = testNeeded;
    }

    public AnamnesisEvaluation() {
        this.level1Symptoms = 0;
        this.level2Symptoms = 0;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Integer getLevel1Symptoms() {
        return level1Symptoms;
    }

    public void setLevel1Symptoms(Integer level1Symptoms) {
        this.level1Symptoms = level1Symptoms;
    }

    public Integer getLevel2Symptoms() {
        return level2Symptoms;
    }

    public void setLevel2Symptoms(Integer level2Symptoms) {
        this.level2Symptoms = level2Symptoms;
    }

    public boolean isBloodAnalysisNeeded() {
        return bloodAnalysisNeeded;
    }

    public void setBloodAnalysisNeeded(boolean bloodAnalysisNeeded) {
        this.bloodAnalysisNeeded = bloodAnalysisNeeded;
    }

    public boolean isTestNeeded() {
        return testNeeded;
    }

    public void setTestNeeded(boolean testNeeded) {
        this.testNeeded = testNeeded;
    }
}
