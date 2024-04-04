package com.ironhack.labjavaintrotospringboot.service;

import com.ironhack.labjavaintrotospringboot.model.user.Doctor;
import com.ironhack.labjavaintrotospringboot.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() { return doctorRepository.findAll(); }
    public Doctor addNewDoctor(Doctor doctor) { return doctorRepository.save(doctor); }

    public void updateStatus(int id, Doctor doctor) {
        Optional<Doctor> $doctor = doctorRepository.findById(id);
        Doctor existingDoctor = $doctor.get();
        existingDoctor.setStatus(doctor.getStatus());
        doctorRepository.save(existingDoctor);
    }

    public void updateDepartment(int id, Doctor doctor) {
        Optional<Doctor> $doctor = doctorRepository.findById(id);
        Doctor existingDoctor = $doctor.get();
        existingDoctor.setDepartment(doctor.getDepartment());
        doctorRepository.save(existingDoctor);
    }
}
