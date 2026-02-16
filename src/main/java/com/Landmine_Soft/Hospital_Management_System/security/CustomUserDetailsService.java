package com.Landmine_Soft.Hospital_Management_System.security;


import com.Landmine_Soft.Hospital_Management_System.entity.Admin;
import com.Landmine_Soft.Hospital_Management_System.entity.Doctor;
import com.Landmine_Soft.Hospital_Management_System.entity.Patient;
import com.Landmine_Soft.Hospital_Management_System.repository.AdminRepository;
import com.Landmine_Soft.Hospital_Management_System.repository.DoctorRepository;
import com.Landmine_Soft.Hospital_Management_System.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final AdminRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (patientRepo.findByEmail(email).isPresent()) {
            Patient p = patientRepo.findByEmail(email).get();
            return new User(p.getEmail(), p.getPassword(),
                    Collections.singleton(() -> "ROLE_PATIENT"));
        }

        if (doctorRepo.findByEmail(email).isPresent()) {
            Doctor d = doctorRepo.findByEmail(email).get();
            return new User(d.getEmail(), d.getPassword(),
                    Collections.singleton(() -> "ROLE_DOCTOR"));
        }

        if (adminRepo.findByEmail(email).isPresent()) {
            Admin a = adminRepo.findByEmail(email).get();
            return new User(a.getEmail(), a.getPassword(),
                    Collections.singleton(() -> "ROLE_ADMIN"));
        }

        throw new UsernameNotFoundException("User not found");
    }
}

