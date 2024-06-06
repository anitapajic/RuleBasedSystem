package com.ftn.sbnz.service.medicine;

import com.ftn.sbnz.model.models.medicine.Medicine;
import com.ftn.sbnz.repository.MedicineRepository;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    MedicineRepository medicineRepository;

    public List<Medicine> findAll(){
        return medicineRepository.findAll();
    }
    public Medicine findById(Integer id){
        return medicineRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("This medicine doesn't exist"));
    }
    public Medicine findByName(String name){
        return medicineRepository.findByName(name);
    }
}
