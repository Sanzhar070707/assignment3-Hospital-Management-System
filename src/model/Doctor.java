package model;

import java.time.LocalDate;

public class Doctor extends BaseEntity implements Validatable {
    private String lastName;
    private String specialization;
    private LocalDate hireDate;

    public Doctor(int id, String firstName, String lastName, String specialization, LocalDate hireDate) {
        super(id, firstName); // BaseEntity хранит id и name (name = firstName)
        this.lastName = lastName;
        this.specialization = specialization;
        this.hireDate = hireDate;
    }

    @Override
    public void printInfo() {
        System.out.println("ID: " + getId() + ", Doctor: " + getFirstName() + " " + lastName +
                ", Specialization: " + specialization + ", Hire Date: " + hireDate);
    }

    @Override
    public void validate() {
        if (getFirstName() == null || getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (specialization == null || specialization.isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty");
        }
        if (hireDate == null) {
            throw new IllegalArgumentException("Hire date cannot be null");
        }
    }

    @Override
    public boolean isValid() {
        return getFirstName() != null && !getFirstName().isEmpty()
                && lastName != null && !lastName.isEmpty()
                && specialization != null && !specialization.isEmpty()
                && hireDate != null;
    }

    // Getters/Setters
    public String getFirstName() {
        return getName(); // имя хранится в BaseEntity
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public int getId() {
        return super.getId(); // получаем id из BaseEntity
    }
}