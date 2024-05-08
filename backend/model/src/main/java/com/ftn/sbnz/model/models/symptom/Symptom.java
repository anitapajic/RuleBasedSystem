package com.ftn.sbnz.model.models.symptom;

import com.ftn.sbnz.model.models.symptom.enums.SymptomLevel;
import jakarta.persistence.*;

@Entity
@Table(name = "symptoms")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private SymptomLevel symptomLevel;

    //region Constructors

    public Symptom() {
    }

    public Symptom(Integer id, String name, SymptomLevel symptomLevel) {
        this.id = id;
        this.name = name;
        this.symptomLevel = symptomLevel;
    }

    //endregion

    //region Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymptomLevel getSymptomLevel() {
        return symptomLevel;
    }

    public void setSymptomLevel(SymptomLevel symptomLevel) {
        this.symptomLevel = symptomLevel;
    }

    //endregion

}
