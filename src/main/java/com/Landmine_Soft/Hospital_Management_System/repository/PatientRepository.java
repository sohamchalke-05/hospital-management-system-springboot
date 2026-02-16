package com.Landmine_Soft.Hospital_Management_System.repository;


import com.Landmine_Soft.Hospital_Management_System.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.domain.*;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
    boolean existsByEmail(String email);
    Page<Patient> findByCityContainingIgnoreCase(String city, Pageable pageable);
}
