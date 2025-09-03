package com.gaur.Assignment.dto;

import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.enums.Symptom;
import lombok.Data;

import java.util.List;

@Data
public class SuggestedDoctorDto {

    private String info;
    private Symptom symptom;
    private List<Doctor> suggested_doctors;
}
