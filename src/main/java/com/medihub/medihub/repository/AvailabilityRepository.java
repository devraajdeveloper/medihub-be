package com.medihub.medihub.repository;

import com.medihub.medihub.entity.Availability;
import com.medihub.medihub.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findAvailabilitiesByDoctorAndDayOfWeek(Doctor doctor, DayOfWeek dayOfWeek);
}
