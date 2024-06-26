package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.user.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByEmail(String email);
}
