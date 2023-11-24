package com.medihub.medihub.service;

import com.medihub.medihub.entity.Appointment;
import com.medihub.medihub.entity.User;
import com.medihub.medihub.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User scheduleAppointment(Long userId, Appointment appointment) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            appointment.setUser(user);
            user.getAppointments().add(appointment);
            userRepository.save(user);
            return user;
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }
}
