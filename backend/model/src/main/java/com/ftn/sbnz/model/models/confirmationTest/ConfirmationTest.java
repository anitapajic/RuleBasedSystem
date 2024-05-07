package com.ftn.sbnz.model.models.confirmationTest;

import com.ftn.sbnz.model.models.confirmationTest.enums.TestResult;
import jakarta.persistence.*;

@Entity
@Table(name = "confirmation_tests")
public class ConfirmationTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private TestResult testResult;

}
