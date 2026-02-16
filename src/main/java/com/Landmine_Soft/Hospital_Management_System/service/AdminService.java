package com.Landmine_Soft.Hospital_Management_System.service;


import com.Landmine_Soft.Hospital_Management_System.dto.PatientResponseDto;
import com.Landmine_Soft.Hospital_Management_System.entity.Patient;
import com.Landmine_Soft.Hospital_Management_System.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final PatientRepository patientRepository;

    public Page<PatientResponseDto> getAllPatients(
            int page,
            int size,
            String city) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Patient> patientPage;

        if (city != null && !city.isEmpty()) {
            patientPage = patientRepository.findByCityContainingIgnoreCase(city, pageable);
        } else {
            patientPage = patientRepository.findAll(pageable);
        }

        return patientPage.map(this::convertToDto);
    }

    private PatientResponseDto convertToDto(Patient patient) {

        return PatientResponseDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .phone(patient.getPhone())
                .email(patient.getEmail())
                .city(patient.getCity())
                .gender(patient.getGender().name())
                .bloodGroup(patient.getBloodGroup())
                .build();
    }
}

