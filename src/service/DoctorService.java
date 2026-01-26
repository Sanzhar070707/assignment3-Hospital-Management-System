package service;

import model.Doctor;
import repository.DoctorRepository;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;

import java.util.List;

public class DoctorService {
    private final DoctorRepository doctorRepository = new DoctorRepository();

    // CREATE
    public void addDoctor(Doctor doctor) {
        try {
            doctor.validate();
            doctorRepository.create(doctor);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    // READ ALL
    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAll();
    }

    // READ BY ID
    public Doctor getDoctorById(int id) {
        Doctor doctor = doctorRepository.getById(id);
        if (doctor == null) {
            throw new ResourceNotFoundException("Doctor with ID " + id + " not found");
        }
        return doctor;
    }

    // UPDATE
    public void updateDoctor(int id, Doctor doctor) {
        try {
            doctor.validate();
            doctorRepository.update(id, doctor);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    // DELETE
    public void deleteDoctor(int id) {
        doctorRepository.delete(id);
    }
}