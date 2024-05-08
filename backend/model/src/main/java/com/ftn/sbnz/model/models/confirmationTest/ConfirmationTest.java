package com.ftn.sbnz.model.models.confirmationTest;

import com.ftn.sbnz.model.models.confirmationTest.enums.TestResult;
import com.ftn.sbnz.model.models.confirmationTest.enums.TestType;
import com.ftn.sbnz.model.models.user.Patient;
import jakarta.persistence.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "confirmation_tests")
public class ConfirmationTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private TestType name;

    @Column
    @Enumerated(EnumType.STRING)
    private TestResult testResult;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column
    private LocalDateTime testDate;

    //region Constructors

    public ConfirmationTest() {
    }

    public ConfirmationTest(Integer id, TestType name, TestResult testResult, Patient patient, LocalDateTime testDate) {
        this.id = id;
        this.name = name;
        this.testResult = testResult;
        this.patient = patient;
        this.testDate = testDate;
    }

    //endregion

    //region Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TestType getName() {
        return name;
    }

    public void setName(TestType name) {
        this.name = name;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDateTime testDate) {
        this.testDate = testDate;
    }

    //endregion
}
