package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.disease.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    Disease findByName(String name);
}
