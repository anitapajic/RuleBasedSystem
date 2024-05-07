package com.ftn.sbnz.model.models.anamnesis;

import com.ftn.sbnz.model.models.medicine.Ingredient;
import com.ftn.sbnz.model.models.symptom.Symptom;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.model.models.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "anamnesis")
public class Anamnesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @Column
    private LocalDateTime dateTime;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "anamnesis_symptom",
            joinColumns = @JoinColumn(name = "anamnesis_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id", referencedColumnName = "id"))
    private Set<Symptom> patientsSymptoms = new HashSet<>();

    @Column
    private Boolean isTestNeeded;

}
