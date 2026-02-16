package com.Landmine_Soft.Hospital_Management_System.service;


import com.Landmine_Soft.Hospital_Management_System.dto.AvailabilityRequestDto;
import com.Landmine_Soft.Hospital_Management_System.dto.SlotResponseDto;
import com.Landmine_Soft.Hospital_Management_System.entity.Availability;
import com.Landmine_Soft.Hospital_Management_System.entity.Doctor;
import com.Landmine_Soft.Hospital_Management_System.repository.AvailabilityRepository;
import com.Landmine_Soft.Hospital_Management_System.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final DoctorRepository doctorRepository;


    public void addAvailability(Long doctorId,
                                List<AvailabilityRequestDto> requestList) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        for (AvailabilityRequestDto dto : requestList) {

            List<Availability> existingSlots =
                    availabilityRepository.findByDoctorIdAndDate(
                            doctorId, dto.getDate());


            for (Availability slot : existingSlots) {
                if (dto.getStartTime().isBefore(slot.getEndTime())
                        && dto.getEndTime().isAfter(slot.getStartTime())) {
                    throw new RuntimeException("Time slot overlaps");
                }
            }

            Availability availability = Availability.builder()
                    .doctor(doctor)
                    .date(dto.getDate())
                    .startTime(dto.getStartTime())
                    .endTime(dto.getEndTime())
                    .durationMinutes(30)
                    .isBooked(false)
                    .build();

            availabilityRepository.save(availability);
        }
    }

    public List<SlotResponseDto> getSlots(Long doctorId,
                                          java.time.LocalDate date) {

        List<Availability> availabilityList =
                availabilityRepository.findByDoctorIdAndDate(doctorId, date);

        List<SlotResponseDto> slots = new ArrayList<>();

        for (Availability availability : availabilityList) {

            LocalTime start = availability.getStartTime();
            LocalTime end = availability.getEndTime();

            while (start.isBefore(end)) {

                slots.add(new SlotResponseDto(
                        start.toString(),
                        availability.getIsBooked()
                ));

                start = start.plusMinutes(availability.getDurationMinutes());
            }
        }

        return slots;
    }
}

