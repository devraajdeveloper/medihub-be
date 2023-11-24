package com.medihub.medihub.service;

import com.medihub.medihub.entity.Availability;
import com.medihub.medihub.entity.Doctor;
import com.medihub.medihub.repository.AvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
@AllArgsConstructor
public class AvailabilityService {
    private AvailabilityRepository availabilityRepository;

    public List<Availability> getAvailabilitiesByDoctorAndDayOfWeek(Doctor doctor, DayOfWeek dayOfWeek) {
        return availabilityRepository.findAvailabilitiesByDoctorAndDayOfWeek(doctor, dayOfWeek);
    }
}
