package com.Landmine_Soft.Hospital_Management_System.controller;

import com.Landmine_Soft.Hospital_Management_System.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/")
    public String dashboard(Model model) {

        long totalPatients = patientRepository.count();

        model.addAttribute("totalPatients", totalPatients);
        model.addAttribute("totalDoctors", 25); // Replace with real repo later
        model.addAttribute("appointmentsToday", 42); // Replace later
        model.addAttribute("availableBeds", 12); // Replace later

        return "dashboard";
    }
}
