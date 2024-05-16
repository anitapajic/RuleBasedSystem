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

    //region Constructors

    public Anamnesis() {
    }

    public Anamnesis(Integer id, Patient patient, User doctor, LocalDateTime dateTime, String description,
                     Set<Symptom> patientsSymptoms, Boolean isTestNeeded) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.description = description;
        this.patientsSymptoms = patientsSymptoms;
        this.isTestNeeded = isTestNeeded;
    }

    //endregion

    //region Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Symptom> getPatientsSymptoms() {
        return patientsSymptoms;
    }

    public void setPatientsSymptoms(Set<Symptom> patientsSymptoms) {
        this.patientsSymptoms = patientsSymptoms;
    }

    public Boolean getTestNeeded() {
        return isTestNeeded;
    }

    public void setTestNeeded(Boolean testNeeded) {
        isTestNeeded = testNeeded;
    }

    //endregion

}
