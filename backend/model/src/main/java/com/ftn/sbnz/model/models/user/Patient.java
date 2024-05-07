package com.ftn.sbnz.model.models.user;

import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.model.models.medicine.Ingredient;
import com.ftn.sbnz.model.models.therapy.Therapy;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patients")
@Inheritance(strategy = InheritanceType.JOINED)
public class Patient extends User {

    @Column
    private LocalDate birthDate;

    @Column
    private Double weight;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patient_allergen",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id", referencedColumnName = "id"))
    private Set<Ingredient> allergens = new HashSet<>();

    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Diagnosis> diagnosis = new HashSet<>();



}
