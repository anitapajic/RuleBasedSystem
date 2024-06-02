package com.ftn.sbnz.model.models.diagnosis;

import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.user.Patient;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosis")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @Column
    private Integer diseaseLevel;
    @Column
    private LocalDateTime timestamp;

    //region Getters and Setters

    public Diagnosis() {
    }

    public Diagnosis(Integer id, Patient patient, Disease disease, Integer diseaseLevel, LocalDateTime timestamp) {
        this.id = id;
        this.patient = patient;
        this.disease = disease;
        this.diseaseLevel = diseaseLevel;
        this.timestamp = timestamp;
    }

    //endregion

    //region Constructors


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDiseaseLevel() {
        return diseaseLevel;
    }

    public void setDiseaseLevel(Integer diseaseLevel) {
        this.diseaseLevel = diseaseLevel;
    }

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

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    //endregion
}
