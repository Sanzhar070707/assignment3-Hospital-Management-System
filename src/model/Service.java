package model;

public class Service extends BaseEntity implements Validatable {
    private String description;
    private double price;

    public Service(int id, String name, String description, double price) {
        super(id, name);
        this.description = description;
        this.price = price;
    }

    @Override
    public void printInfo() {
        System.out.println("Service: " + getName() + ", Price: " + price);
    }

    @Override
    public void validate() {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
    }

    @Override
    public boolean isValid() {
        return price > 0;
    }

    // Getters/Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}