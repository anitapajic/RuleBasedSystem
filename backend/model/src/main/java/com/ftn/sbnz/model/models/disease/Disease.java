package com.ftn.sbnz.model.models.disease;

import com.ftn.sbnz.model.models.confirmationTest.ConfirmationTest;
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

}
