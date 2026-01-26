package model;

public class Patient extends BaseEntity implements Validatable {
    private int age;
    private String gender;
    private String phoneNumber;

    public Patient(int id, String name, int age, String gender, String phoneNumber) {
        super(id, name);
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void printInfo() {
        System.out.println("Patient: " + getName() + ", Age: " + age + ", Gender: " + gender);
    }

    @Override
    public void validate() {
        if (getName() == null || getName().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be empty");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
    }

    @Override
    public boolean isValid() {
        return age > 0 && getName() != null && !getName().isEmpty();
    }

    // Getters/Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}