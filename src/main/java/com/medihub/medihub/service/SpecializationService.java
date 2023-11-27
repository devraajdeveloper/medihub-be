package com.medihub.medihub.service;

import com.medihub.medihub.entity.Specialization;
import com.medihub.medihub.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public Specialization getSpecializationById(Long id) {
        Optional<Specialization> optionalSpecialization = specializationRepository.findById(id);
        return optionalSpecialization.orElse(null);
    }

    public Specialization createSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    public Specialization updateSpecialization(Long id, Specialization specialization) {
        Optional<Specialization> optionalExistingSpecialization = specializationRepository.findById(id);

        if (optionalExistingSpecialization.isPresent()) {
            Specialization existingSpecialization = optionalExistingSpecialization.get();
            existingSpecialization.setName(specialization.getName());
            existingSpecialization.setImageUrl(specialization.getImageUrl());
            return specializationRepository.save(existingSpecialization);
        } else {
            return null;
        }
    }

    public boolean deleteSpecialization(Long id) {
        Optional<Specialization> optionalSpecialization = specializationRepository.findById(id);

        if (optionalSpecialization.isPresent()) {
            specializationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
