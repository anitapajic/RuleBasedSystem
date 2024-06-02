package com.ftn.sbnz.service.symptom;

import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.model.models.symptom.Symptom;
import com.ftn.sbnz.repository.DiseaseRepository;
import com.ftn.sbnz.repository.SymptomRepository;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;

    public Symptom findById(Integer id) {
        return symptomRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This symptom doesn't exist"));
    }

    public List<Symptom> findAll(){
        return symptomRepository.findAll();
    }

    public List<Symptom> findSymptomsByDiseaseId(Integer diseaseId){
        List<Symptom> symptoms = new ArrayList<>();
        Disease disease = diseaseRepository.findById(diseaseId).orElseThrow(() ->
                new ResourceNotFoundException("Disease doesn't exist."));
        if(disease!=null){
            return disease.getSymptoms().stream().toList();
        }
        return symptoms;
    }

    public List<Symptom> findSymptomsByDiseaseName(String name){
        List<Symptom> symptoms = new ArrayList<>();
        Disease disease = diseaseRepository.findByName(name);
        if(disease!=null){
            return disease.getSymptoms().stream().toList();
        }
        return symptoms;
    }

}
