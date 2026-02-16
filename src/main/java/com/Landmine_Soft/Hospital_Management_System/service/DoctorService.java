package com.Landmine_Soft.Hospital_Management_System.service;


import com.Landmine_Soft.Hospital_Management_System.dto.DoctorProfileRequestDto;
import com.Landmine_Soft.Hospital_Management_System.dto.DoctorResponseDto;
import com.Landmine_Soft.Hospital_Management_System.entity.Doctor;
import com.Landmine_Soft.Hospital_Management_System.entity.DoctorProfile;
import com.Landmine_Soft.Hospital_Management_System.repository.DoctorProfileRepository;
import com.Landmine_Soft.Hospital_Management_System.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorProfileRepository profileRepository;

    public DoctorResponseDto addOrUpdateProfile(Long doctorId,
                                                DoctorProfileRequestDto dto) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorProfile profile = profileRepository.findById(doctorId)
                .orElse(new DoctorProfile());

        profile.setDoctor(doctor);
        profile.setPosition(dto.getPosition());
        profile.setDepartment(dto.getDepartment());
        profile.setFeesPerConsult(dto.getFeesPerConsult());
        profile.setClinicAddress(dto.getClinicAddress());
        profile.setExperienceYears(dto.getExperienceYears());
        profile.setSpecialization(dto.getSpecialization());

        profileRepository.save(profile);

        return convertToDto(doctor, profile);
    }

    public DoctorResponseDto getDoctorById(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return convertToDto(doctor, profile);
    }

    public Page<DoctorResponseDto> listDoctors(
            String department,
            Double feesLt,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<DoctorProfile> profiles;

        if (department != null && feesLt != null) {
            profiles = profileRepository
                    .findByDepartmentContainingIgnoreCaseAndFeesPerConsultLessThanEqual(
                            department, feesLt, pageable);
        } else if (department != null) {
            profiles = profileRepository
                    .findByDepartmentContainingIgnoreCase(department, pageable);
        } else {
            profiles = profileRepository.findAll(pageable);
        }

        return profiles.map(p -> convertToDto(p.getDoctor(), p));
    }

    private DoctorResponseDto convertToDto(Doctor doctor,
                                           DoctorProfile profile) {

        return DoctorResponseDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .department(profile.getDepartment())
                .feesPerConsult(profile.getFeesPerConsult())
                .clinicAddress(profile.getClinicAddress())
                .experienceYears(profile.getExperienceYears())
                .rating(profile.getRating())
                .specialization(profile.getSpecialization())
                .build();
    }
}

