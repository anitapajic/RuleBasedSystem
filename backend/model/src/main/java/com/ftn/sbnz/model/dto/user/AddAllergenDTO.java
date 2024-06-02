package com.ftn.sbnz.model.dto.user;

import java.util.ArrayList;
import java.util.List;

public class AddAllergenDTO {
    private Integer patientId;
    List<Integer> allergens = new ArrayList<>();

    public AddAllergenDTO(Integer patientId, List<Integer> allergens) {
        this.patientId = patientId;
        this.allergens = allergens;
    }
    public AddAllergenDTO(){}

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public List<Integer> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Integer> allergens) {
        this.allergens = allergens;
    }
}
