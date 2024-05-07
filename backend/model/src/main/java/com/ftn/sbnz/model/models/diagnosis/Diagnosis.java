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
}
