package com.gaur.Assignment.repository;

import com.gaur.Assignment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNumber(String number);
}
