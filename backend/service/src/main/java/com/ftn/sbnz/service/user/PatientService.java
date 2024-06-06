package com.ftn.sbnz.service.user;

import com.ftn.sbnz.model.dto.user.AddAllergenDTO;
import com.ftn.sbnz.model.models.medicine.Ingredient;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.repository.IngredientRepository;
import com.ftn.sbnz.repository.PatientRepository;
import com.ftn.sbnz.service.ingredient.IngredientService;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private IngredientService ingredientService;

    public Patient findById(Integer id) {
        return patientRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This patient doesn't exist"));
    }

    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient addAllergens(AddAllergenDTO addAllergenDTO){
        Set<Ingredient> allergens = new HashSet<>();
        for(Integer id : addAllergenDTO.getAllergens()){
            allergens.add(ingredientService.findById(id));
        }
        Patient patient = this.findById(addAllergenDTO.getPatientId());
        patient.setAllergens(allergens);
        this.save(patient);

        return patient;
    }


}
