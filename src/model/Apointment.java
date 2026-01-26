package model;

import java.time.LocalDateTime;

public class Appointment extends BaseEntity implements Validatable {
    private Patient patient;
    private Doctor doctor;
    private Service service;
    private LocalDateTime date;

    public Appointment(int id, String name, Patient patient, Doctor doctor, Service service, LocalDateTime date) {
        super(id, name);
        this.patient = patient;
        this.doctor = doctor;
        this.service = service;
        this.date = date;
    }

    @Override
    public void printInfo() {
        System.out.println("Appointment: " + getName() + " with " + doctor.getName() + " for " + patient.getName());
    }

    @Override
    public void validate() {
        if (patient == null || doctor == null || service == null) {
            throw new IllegalArgumentException("Appointment must have patient, doctor, and service");
        }
    }

    @Override
    public boolean isValid() {
        return patient != null && doctor != null && service != null;
    }

    // Getters/Setters
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}