package com.gaur.Assignment.service.impl;

import com.gaur.Assignment.ExceptionHandler.DataAlreadyExistsException;
import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.enums.Symptom;
import com.gaur.Assignment.mapper.SymptomsToSpecialityMapper;
import com.gaur.Assignment.repository.DoctorRepository;
import com.gaur.Assignment.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private  DoctorRepository doctorRepository;
    @Override
    public Doctor createDoctor(Doctor request) {
        if (doctorRepository.existsByEmail(request.getEmail())){
            throw new DataAlreadyExistsException("Email "+request.getEmail()+" already linked with another doctor");
        } else if (doctorRepository.existsByNumber(request.getNumber())) {
            throw new DataAlreadyExistsException("Number: "+request.getNumber()+" already linked with another doctor");
        }
        return doctorRepository.save(request);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> suggestDoctorBySymptoms(Symptom symptom) {
        SymptomsToSpecialityMapper symptomsToSpecialityMapper = new SymptomsToSpecialityMapper();
        String speciality = symptomsToSpecialityMapper.symptomToSpecialityMap.get(symptom);
        if (speciality!=null){
            return doctorRepository.findBySpeciality(speciality);
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor!=null){
            doctorRepository.deleteById(id);
            return true;
        }else return false;


    }

    @Override
    public Doctor findByDoctorNumber(String Number) {
        return doctorRepository.findByNumber(Number);
    }

    @Override
    public List<Doctor> findDoctorByCity(String city) {
        return doctorRepository.findByCity(city);
    }

    @Override
    public Doctor updateDoctorById(Doctor doctor, long id) {
        Doctor doctor1 = doctorRepository.findById(id).orElse(null);
        if (doctor1!=null){
            doctor1.setName(doctor.getName());
            doctor1.setCity(doctor.getCity());
            doctor1.setEmail(doctor.getEmail());
            doctor1.setSpeciality(doctor.getSpeciality());
            doctor1.setNumber(doctor.getNumber());
            return doctorRepository.save(doctor);
        }
        return null;
    }

    @Override
    public Doctor findDoctorById(long id) {
        return doctorRepository.findById(id).orElse(null);
    }
}
