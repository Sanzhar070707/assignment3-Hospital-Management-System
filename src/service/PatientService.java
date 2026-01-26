package service;

import model.Patient;
import repository.PatientRepository;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;

import java.util.List;

public class PatientService {
    private final PatientRepository patientRepository = new PatientRepository();

    // CREATE
    public void addPatient(Patient patient) {
        try {
            patient.validate(); // проверка через Validatable
            patientRepository.create(patient);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    // READ ALL
    public List<Patient> getAllPatients() {
        return patientRepository.getAll();
    }

    // READ BY ID
    public Patient getPatientById(int id) {
        Patient patient = patientRepository.getById(id);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient with ID " + id + " not found");
        }
        return patient;
    }

    // UPDATE
    public void updatePatient(int id, Patient patient) {
        try {
            patient.validate();
            patientRepository.update(id, patient);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    // DELETE
    public void deletePatient(int id) {
        patientRepository.delete(id);
    }
}