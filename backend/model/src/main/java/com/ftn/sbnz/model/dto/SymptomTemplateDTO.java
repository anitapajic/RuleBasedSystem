package com.ftn.sbnz.model.dto;

public class SymptomTemplateDTO {

    private String symptomLevel;
    private String symptomName;

    public SymptomTemplateDTO(String symptomLevel, String symptomName) {
        this.symptomLevel = symptomLevel;
        this.symptomName = symptomName;
    }

    public SymptomTemplateDTO() {
    }

    public String getSymptomLevel() {
        return symptomLevel;
    }

    public void setSymptomLevel(String symptomLevel) {
        this.symptomLevel = symptomLevel;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }
}
