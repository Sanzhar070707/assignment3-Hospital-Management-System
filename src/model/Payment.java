package model;

public class Payment extends BaseEntity implements Payable, Validatable {
    private Appointment appointment;
    private double amount;
    private String status;

    public Payment(int id, String name, Appointment appointment, double amount) {
        super(id, name);
        this.appointment = appointment;
        this.amount = amount;
        this.status = "PENDING";
    }

    @Override
    public void printInfo() {
        System.out.println("Payment: " + getName() + ", Amount: " + amount + ", Status: " + status);
    }

    @Override
    public void validate() {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
        if (appointment == null) {
            throw new IllegalArgumentException("Payment must be linked to an appointment");
        }
    }

    @Override
    public boolean isValid() {
        return appointment != null && amount > 0;
    }

    @Override
    public void pay(double amount) {
        this.amount = amount;
        this.status = "COMPLETED";
        System.out.println("Payment of " + amount + " processed successfully.");
    }

    // Getters/Setters
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}