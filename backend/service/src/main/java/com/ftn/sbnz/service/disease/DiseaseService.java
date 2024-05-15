package com.ftn.sbnz.service.disease;

import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {
    @Autowired
    private DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }
    public DiseaseService(){}

    public Disease findByName(String name){
        return diseaseRepository.findByName(name);
    }
}
