package com.gaur.Assignment.mapper;

import com.gaur.Assignment.dto.PatientDto;
import com.gaur.Assignment.dto.SuggestedDoctorDto;
import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.entity.Patient;

import java.util.List;

public class PatientMapper {
    public static PatientDto maptoPatientDto(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setInfo("Patient data upload successfully");

        patientDto.setName(patient.getName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setCity(patient.getCity());
        patientDto.setContactNumber(patient.getNumber());
        patientDto.setSymptom(patient.getSymptom());
        return patientDto;
    }

    public static SuggestedDoctorDto suggestedDoctorDto(Patient patient, List<Doctor>doctors,boolean registered){
        SuggestedDoctorDto suggestedDoctorDto = new SuggestedDoctorDto();
        if (registered){
            suggestedDoctorDto.setInfo("This patient is already registered");
        }else suggestedDoctorDto.setInfo("Patient registered successfully");

        suggestedDoctorDto.setSymptom(patient.getSymptom());

        suggestedDoctorDto.setSuggested_doctors(doctors);
        return suggestedDoctorDto;
    }
}
