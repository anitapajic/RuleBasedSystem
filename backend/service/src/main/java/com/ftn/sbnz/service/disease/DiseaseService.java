package com.ftn.sbnz.service.disease;

import com.ftn.sbnz.model.models.disease.Disease;
import com.ftn.sbnz.repository.DiseaseRepository;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {
    @Autowired
    private DiseaseRepository diseaseRepository;

    public Disease findByName(String name){
        return diseaseRepository.findByName(name);
    }
    public Disease findById(Integer id){
        return diseaseRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This disease doesn't exist"));
    }

    public List<Disease> findAll(){
        return diseaseRepository.findAll();
    }


}
