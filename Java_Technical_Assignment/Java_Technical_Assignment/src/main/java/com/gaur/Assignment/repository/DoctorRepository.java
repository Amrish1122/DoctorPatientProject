package com.gaur.Assignment.repository;

import com.gaur.Assignment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    boolean existsByEmail(String email);

    List<Doctor> findBySpeciality(String speciality);

  //  List<Doctor> findBySpecialityAndSymptom(String speciality, String patient_city);

    boolean existsByNumber (String phoneNumber);

    Doctor findByNumber(String phoneNumber);

    List<Doctor> findByCity(String city);
}
