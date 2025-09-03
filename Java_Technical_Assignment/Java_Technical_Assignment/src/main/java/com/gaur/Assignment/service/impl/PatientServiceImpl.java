package com.gaur.Assignment.service.impl;

import com.gaur.Assignment.ExceptionHandler.DataAlreadyExistsException;
import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.entity.Patient;
import com.gaur.Assignment.mapper.SymptomsToSpecialityMapper;
import com.gaur.Assignment.repository.DoctorRepository;
import com.gaur.Assignment.repository.PatientRepository;
import com.gaur.Assignment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private  PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Patient createPatient(Patient request) {
        if (patientRepository.existsByEmail(request.getEmail())){
            throw new DataAlreadyExistsException("Email "+request.getEmail()+" already linked with another patient");
        }else if (patientRepository.existsByNumber(request.getNumber())){
            throw new DataAlreadyExistsException("Number: "+request.getNumber()+" already linked with another patient");
        }
        return patientRepository.save(request);
    }

    @Override
    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public boolean deletePatientById(Long id){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient!=null){
            patientRepository.deleteById(id);
            return true;
        }else return false;
    }


    @Override
    public Patient updatePatientById(Patient patient, Long id) {
        Patient patient1 = patientRepository.findById(id).orElse(null);
        if (patient1!=null){
            patient1.setName(patient.getName());
            patient1.setNumber(patient.getNumber());
            patient1.setEmail(patient.getEmail());
            patient1.setSymptom(patient.getSymptom());
            patient1.setEmail(patient.getEmail());
            patientRepository.save(patient);
        }
        return null;
    }
}
