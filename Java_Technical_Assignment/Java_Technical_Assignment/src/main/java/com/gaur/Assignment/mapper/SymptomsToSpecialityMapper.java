package com.gaur.Assignment.mapper;

import com.gaur.Assignment.enums.Symptom;

import java.util.Map;

public class SymptomsToSpecialityMapper {
    public Map<Symptom,String> symptomToSpecialityMap=Map.of(
            Symptom.ARTHRITIS,"Orthopedic",
            Symptom.BACK_PAIN,"Orthopedic",
            Symptom.TISSUE_INJURIES,"Orthopedic",
            Symptom.DYSMENORRHEA,"Gynecology",
            Symptom.SKIN_BURN,"Dermatology",
            Symptom.SKIN_INFECTION,"Dermatology",
            Symptom.EAR_PAIN,"ENT specialist"
    );
}
