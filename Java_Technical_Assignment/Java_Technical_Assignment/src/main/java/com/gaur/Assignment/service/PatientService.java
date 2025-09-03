package com.gaur.Assignment.service;

import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient createPatient(Patient request);

    List<Patient> findAllPatient();

    Patient findById(Long id);

    Patient updatePatientById(Patient patient,Long id);

    boolean deletePatientById(Long id);


}
