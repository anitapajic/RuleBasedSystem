package com.ftn.sbnz.model.models.diagnosis;

import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.user.Patient;
import jakarta.persistence.*;

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

    //region Getters and Setters

    public Diagnosis() {
    }

    public Diagnosis(Integer id, Patient patient, Disease disease) {
        this.id = id;
        this.patient = patient;
        this.disease = disease;
    }

    //endregion

    //region Constructors

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
