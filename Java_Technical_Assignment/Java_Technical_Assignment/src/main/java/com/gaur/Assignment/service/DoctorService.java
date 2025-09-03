package com.gaur.Assignment.service;

import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.enums.Symptom;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Doctor request);

    List<Doctor> findAllDoctors();

    List<Doctor> suggestDoctorBySymptoms(Symptom symptom);

    boolean deleteById(Long id);

    Doctor findByDoctorNumber(String Number);

    List<Doctor> findDoctorByCity (String city);

    Doctor updateDoctorById(Doctor doctor, long id);

    Doctor findDoctorById(long id);
}
