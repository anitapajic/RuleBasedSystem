package com.ftn.sbnz.model.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.model.models.medicine.Ingredient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends User implements Serializable {

    @Column
    private LocalDate birthDate;

    @Column
    private Double weight;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patient_allergen",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id", referencedColumnName = "id"))
    private Set<Ingredient> allergens = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Diagnosis> diagnosis = new HashSet<>();

    //region Constructors

    public Patient() {
    }

    public Patient(LocalDate birthDate, Double weight, Set<Ingredient> allergens, Set<Diagnosis> diagnosis) {
        this.birthDate = birthDate;
        this.weight = weight;
        this.allergens = allergens;
        this.diagnosis = diagnosis;
    }

    //endregion

    //region Getters and Setters

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Set<Ingredient> getAllergens() {
        return allergens;
    }

    public void setAllergens(Set<Ingredient> allergens) {
        this.allergens = allergens;
    }

    public Set<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Set<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

    //endregion

}
