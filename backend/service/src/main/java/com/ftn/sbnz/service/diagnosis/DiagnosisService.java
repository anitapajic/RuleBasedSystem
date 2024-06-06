package com.ftn.sbnz.service.diagnosis;

import com.ftn.sbnz.model.models.diagnosis.Diagnosis;
import com.ftn.sbnz.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public Diagnosis save(Diagnosis diagnosis){
        return diagnosisRepository.save(diagnosis);
    }

    public Diagnosis findLastByPatientEmail(String patientEmail){
        List<Diagnosis> diagnoses = diagnosisRepository.findAllByPatientEmailOrderByTimestampDesc(patientEmail);
        return diagnoses.get(0);
    }
    public List<Diagnosis> findAllByPatientId(Integer id){
        return diagnosisRepository.findAllByPatientId(id);
    }

    public List<Diagnosis> findAll(){
        return diagnosisRepository.findAll();
    }

}
