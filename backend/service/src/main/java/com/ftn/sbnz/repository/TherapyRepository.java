package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.therapy.Therapy;
import com.ftn.sbnz.model.models.therapy.TherapyEvaluation;
import com.ftn.sbnz.model.models.user.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Integer> {
    List<Therapy> findAllByDiagnosisId(Integer id);

    @Query("SELECT t.diagnosis.patient FROM Therapy t " +
            "WHERE t.diagnosis.disease.id = :diseaseId " +
            "AND :currentDate BETWEEN t.dateRange.startDate AND t.dateRange.endDate")
    List<Patient> findAllPatientsWithActiveTherapyForDisease(@Param("diseaseId") Integer diseaseId,
                                                             @Param("currentDate") LocalDate currentDate);

    @Query("SELECT t.diagnosis.patient FROM Therapy t " +
            "WHERE :currentDate BETWEEN t.dateRange.startDate AND t.dateRange.endDate")
    List<Patient> findAllPatientsWithActiveTherapy(@Param("currentDate") LocalDate currentDate);
}
