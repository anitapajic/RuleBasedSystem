package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.medicine.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    Medicine findByName(String name);
}
