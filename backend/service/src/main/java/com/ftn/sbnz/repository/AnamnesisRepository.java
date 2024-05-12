package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.dto.anamnesis.AnamnesisDTO;
import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnamnesisRepository extends JpaRepository<Anamnesis, Integer> {

}
