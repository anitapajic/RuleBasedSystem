package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.therapy.Therapy;
import com.ftn.sbnz.model.models.therapy.TherapyEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Integer> {
    List<Therapy> findAllByDiagnosisId(Integer id);
}
