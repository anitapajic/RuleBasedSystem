package com.ftn.sbnz.service.symptom;

import com.ftn.sbnz.model.models.symptom.Symptom;
import com.ftn.sbnz.repository.SymptomRepository;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    public Symptom findById(Integer id) {
        return symptomRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This symptom doesn't exist"));
    }

}
