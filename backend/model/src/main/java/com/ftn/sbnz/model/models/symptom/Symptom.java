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


}
