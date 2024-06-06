package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {
    List<Diagnosis> findAllByPatientEmailOrderByTimestampDesc(String patientEmail);
    List<Diagnosis> findAllByPatientId(Integer id);

}
