package com.ftn.sbnz.model.models.disease;

import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.symptom.Symptom;

import java.util.ArrayList;
import java.util.List;

public class DiseaseProbability {
    private Disease disease;
    private Double probability;

    public DiseaseProbability(Disease disease, Double probability) {
        this.disease = disease;
        this.probability = probability;
    }
    public DiseaseProbability(){}

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }
}
