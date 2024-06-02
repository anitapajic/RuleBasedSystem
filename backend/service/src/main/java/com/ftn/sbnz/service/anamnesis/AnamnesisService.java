package com.ftn.sbnz.service.anamnesis;

import com.ftn.sbnz.model.dto.anamnesis.AnamnesisDTO;
import com.ftn.sbnz.model.models.anamnesis.Anamnesis;
import com.ftn.sbnz.repository.AnamnesisRepository;

import com.ftn.sbnz.service.symptom.SymptomService;
import com.ftn.sbnz.service.user.PatientService;
import com.ftn.sbnz.service.user.UserService;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnamnesisService {
    @Autowired
    private AnamnesisRepository anamnesisRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private SymptomService symptomService;

    public Anamnesis save(AnamnesisDTO anamnesisDTO) {
        Anamnesis anamnesis = new Anamnesis();
        System.out.println(anamnesisDTO.getIsTestNeeded());

        anamnesis.setPatient(patientService.findByEmail(anamnesisDTO.getPatientEmail()));
        anamnesis.setDoctor(userService.findById(anamnesisDTO.getDoctorId()));
        anamnesis.setDateTime(LocalDateTime.now());
        anamnesis.setDescription(anamnesisDTO.getDescription());
        anamnesis.setIsTestNeeded(anamnesisDTO.getIsTestNeeded());
        anamnesis.setPatientsSymptoms(anamnesisDTO.getPatientsSymptomsIds().stream().map(symptomId ->
                symptomService.findById(symptomId)).collect(Collectors.toSet()));

        anamnesisRepository.save(anamnesis);

        return anamnesis;
    }

    public Anamnesis findById(Integer id){
        return anamnesisRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This anamnesis doesn't exist"));
    }

    public List<Anamnesis> findAllByPatientId(Integer id){
        return anamnesisRepository.findByPatientId(id);
    }


}
