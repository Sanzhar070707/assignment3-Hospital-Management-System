package model;

public class Doctor extends BaseEntity implements Validatable {
    private String specialization;
    private String phoneNumber;

    public Doctor(int id, String name, String specialization, String phoneNumber) {
        super(id, name);
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void printInfo() {
        System.out.println("Doctor: " + getName() + ", Specialization: " + specialization);
    }

    @Override
    public void validate() {
        if (specialization == null || specialization.isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty");
        }
    }

    @Override
    public boolean isValid() {
        return specialization != null && !specialization.isEmpty();
    }

    // Getters/Setters
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}