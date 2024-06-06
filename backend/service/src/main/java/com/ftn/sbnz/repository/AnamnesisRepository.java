package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.dto.anamnesis.AnamnesisDTO;
import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnamnesisRepository extends JpaRepository<Anamnesis, Integer> {
    List<Anamnesis> findByPatientId(Integer id);
}
