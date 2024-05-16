package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.symptom.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Integer> {
}
