package com.ftn.sbnz.model.models.disease;

import com.ftn.sbnz.model.models.confirmationTest.ConfirmationTest;
import com.ftn.sbnz.model.models.confirmationTest.enums.TestType;
import com.ftn.sbnz.model.models.medicine.Medicine;
import com.ftn.sbnz.model.models.symptom.Symptom;
import com.ftn.sbnz.model.models.user.Role;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "diseases")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "disease_symptom",
            joinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id", referencedColumnName = "id"))
    private Set<Symptom> symptoms = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "disease_confirmation_test",
            joinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "confirmation_test_id", referencedColumnName = "id"))
    private Set<ConfirmationTest> confirmationTests = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "disease_medicine",
            joinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    private Set<Medicine> medicines = new HashSet<>();

    @Column
    @Enumerated(EnumType.STRING)
    private TestType testType;

    //region Constructors

    public Disease() {
    }

    public Disease(Integer id, String name, Set<Symptom> symptoms, Set<ConfirmationTest> confirmationTests,
                   Set<Medicine> medicines, TestType testType) {
        this.id = id;
        this.name = name;
        this.symptoms = symptoms;
        this.confirmationTests = confirmationTests;
        this.medicines = medicines;
        this.testType = testType;
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

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Set<ConfirmationTest> getConfirmationTests() {
        return confirmationTests;
    }

    public void setConfirmationTests(Set<ConfirmationTest> confirmationTests) {
        this.confirmationTests = confirmationTests;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    //endregion

}
