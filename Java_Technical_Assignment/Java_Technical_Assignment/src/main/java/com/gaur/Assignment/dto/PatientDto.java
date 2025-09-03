package com.gaur.Assignment.dto;

import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.enums.Symptom;
import lombok.Data;

import java.util.List;

@Data
public class PatientDto {

    //additional info about the patient
    private String info;

    //basic patient details
    private String name;
    private String city;
    private String email;
    private String contactNumber;

    private Symptom symptom;

    //list of doctors suggested for the patient's symptoms
    private List<Doctor> suggested_doctors;
}
