package com.medihub.medihub.service;
import com.medihub.medihub.entity.Appointment;
import com.medihub.medihub.entity.Availability;
import com.medihub.medihub.entity.Doctor;
import com.medihub.medihub.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {

    private DoctorRepository doctorRepository;
    private AvailabilityService availabilityService;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor scheduleAppointment(Long doctorId, Appointment appointment) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            appointment.setDoctor(doctor);
            doctor.getAppointments().add(appointment);
            doctorRepository.save(doctor);
            return doctor;
        } else {
            throw new RuntimeException("Doctor not found with id: " + doctorId);
        }
    }

    public Doctor setDoctorAvailability(Long doctorId, Availability availability) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            availability.setDoctor(doctor);
            doctor.getAvailabilities().add(availability);
            doctorRepository.save(doctor);
            return doctor;
        } else {
            throw new RuntimeException("Doctor not found with id: " + doctorId);
        }
    }

    public List<Availability> getDoctorAvailability(Long doctorId, DayOfWeek dayOfWeek) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            return availabilityService.getAvailabilitiesByDoctorAndDayOfWeek(doctor, dayOfWeek);
        } else {
            throw new RuntimeException("Doctor not found with id: " + doctorId);
        }
    }
}
