package com.ftn.sbnz.service.user;

import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.repository.PatientRepository;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient findById(Integer id) {
        return patientRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This patient doesn't exist"));
    }

}
