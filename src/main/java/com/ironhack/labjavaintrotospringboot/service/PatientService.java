package com.ironhack.labjavaintrotospringboot.service;

import com.ironhack.labjavaintrotospringboot.model.user.Patient;
import com.ironhack.labjavaintrotospringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() { return patientRepository.findAll(); }
    public Patient addNewPatient(Patient patient) { return patientRepository.save(patient); }

    public void update(int id, Patient patient) {
        Optional<Patient> $patient = patientRepository.findById(id);
        Patient existingPatient = $patient.get();
        existingPatient.setName(patient.getName());
        existingPatient.setDoctor(patient.getDoctor());
        existingPatient.setDateOfBirth(patient.getDateOfBirth());
        patientRepository.save(existingPatient);
    }
}
