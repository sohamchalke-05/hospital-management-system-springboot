package com.Landmine_Soft.Hospital_Management_System.repository;


import com.Landmine_Soft.Hospital_Management_System.entity.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;

public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {

    Page<DoctorProfile> findByDepartmentContainingIgnoreCase(
            String department, Pageable pageable);

    Page<DoctorProfile> findByDepartmentContainingIgnoreCaseAndFeesPerConsultLessThanEqual(
            String department, Double fees, Pageable pageable);

    Page<DoctorProfile> findByDepartmentContainingIgnoreCaseAndCityContainingIgnoreCase(
            String department, String city, Pageable pageable);

}

