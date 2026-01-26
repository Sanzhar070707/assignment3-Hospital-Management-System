package model;

import java.time.LocalDate;

public class Patient extends BaseEntity implements Validatable {
    private String lastName;
    private LocalDate birthDate;
    private String phone;

    public Patient(int id, String firstName, String lastName, LocalDate birthDate, String phone) {
        super(id, firstName); // BaseEntity хранит id и name (name = firstName)
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    @Override
    public void printInfo() {
        System.out.println("ID: " + getId() + ", Patient: " + getFirstName() + " " + lastName +
                ", BirthDate: " + birthDate + ", Phone: " + phone);
    }

    @Override
    public void validate() {
        if (getFirstName() == null || getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
    }

    @Override
    public boolean isValid() {
        return getFirstName() != null && !getFirstName().isEmpty()
                && lastName != null && !lastName.isEmpty()
                && birthDate != null;
    }

    // Getters/Setters
    public String getFirstName() {
        return getName(); // имя хранится в BaseEntity
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getId() {
        return super.getId(); // получаем id из BaseEntity
    }
}