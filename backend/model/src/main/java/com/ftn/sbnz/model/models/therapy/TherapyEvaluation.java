package com.ftn.sbnz.model.models.therapy;

import java.time.LocalDate;

public class TherapyEvaluation {
    private Integer diseaseLevel;
    private String medicineName;
    private Integer patientAge;
    private boolean isBiggerDoseNeeded;
    private boolean isMedicinePrescribed;
    private Integer milligrams;
    private Integer frequency;
    private Boolean isBiggerFrequencyNeeded;

    public TherapyEvaluation(){}
    public TherapyEvaluation(Integer diseaseLevel, String medicineName, Integer patientAge, boolean isBiggerDoseNeeded,
                             boolean isMedicinePrescribed, Integer milligrams, Integer frequency, Boolean isBiggerFrequencyNeeded) {
        this.diseaseLevel = diseaseLevel;
        this.medicineName = medicineName;
        this.patientAge = patientAge;
        this.isBiggerDoseNeeded = isBiggerDoseNeeded;
        this.isMedicinePrescribed = isMedicinePrescribed;
        this.milligrams = milligrams;
        this.frequency = frequency;
        this.isBiggerFrequencyNeeded = isBiggerFrequencyNeeded;
    }

    public Boolean getBiggerFrequencyNeeded() {
        return this.isBiggerFrequencyNeeded;
    }

    public void setBiggerFrequencyNeeded(Boolean biggerFrequencyNeeded) {
        this.isBiggerFrequencyNeeded = biggerFrequencyNeeded;
    }

    public Integer getMilligrams() {
        return milligrams;
    }

    public void setMilligrams(Integer milligrams) {
        this.milligrams = milligrams;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getDiseaseLevel() {
        return diseaseLevel;
    }

    public void setDiseaseLevel(Integer diseaseLevel) {
        this.diseaseLevel = diseaseLevel;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public boolean getBiggerDoseNeeded() {
        return isBiggerDoseNeeded;
    }

    public void setBiggerDoseNeeded(boolean biggerDoseNeeded) {
        isBiggerDoseNeeded = biggerDoseNeeded;
    }

    public boolean getMedicinePrescribed() {
        return isMedicinePrescribed;
    }

    public void setMedicinePrescribed(boolean medicinePrescribed) {
        isMedicinePrescribed = medicinePrescribed;
    }
}
