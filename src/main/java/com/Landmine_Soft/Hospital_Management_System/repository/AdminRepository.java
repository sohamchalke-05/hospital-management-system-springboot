package com.Landmine_Soft.Hospital_Management_System.repository;

import com.Landmine_Soft.Hospital_Management_System.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}

