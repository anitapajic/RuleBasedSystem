package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.medicine.Medicine;
import com.ftn.sbnz.model.models.symptom.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    Disease findByName(String name);

}
