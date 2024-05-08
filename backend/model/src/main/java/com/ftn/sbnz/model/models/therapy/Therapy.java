package com.ftn.sbnz.model.models.therapy;

import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.medicine.Medicine;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.model.models.utils.DateRange;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "therapies")
public class Therapy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @Embedded
    private DateRange dateRange;

    @Column
    private Integer milligrams;

    @Column
    private Integer frequency;

    //region Constructors

    public Therapy() {
    }

    public Therapy(Integer id, Diagnosis diagnosis, Medicine medicine, DateRange dateRange, Integer milligrams,
                   Integer frequency) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.medicine = medicine;
        this.dateRange = dateRange;
        this.milligrams = milligrams;
        this.frequency = frequency;
    }

    //endregion

    //region Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public Integer getMilligrams() {
        return milligrams;
    }

    public void setMilligrams(Integer milligrams) {
        this.milligrams = milligrams;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    //endregion
}
