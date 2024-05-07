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

}
