package repository;

import model.Doctor;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {

    // CREATE
    public void create(Doctor doctor) {
        String sql = "INSERT INTO doctors (first_name, last_name, specialization, hire_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setDate(4, Date.valueOf(doctor.getHireDate()));
            stmt.executeUpdate();
            System.out.println("Doctor created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating doctor: " + e.getMessage());
        }
    }

    // READ ALL
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("specialization"),
                        rs.getDate("hire_date").toLocalDate()
                );
                doctors.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching doctors: " + e.getMessage());
        }
        return doctors;
    }

    // READ BY ID
    public Doctor getById(int id) {
        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("specialization"),
                        rs.getDate("hire_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching doctor by ID: " + e.getMessage());
        }
        return null;
    }

    // UPDATE
    public void update(int id, Doctor doctor) {
        String sql = "UPDATE doctors SET first_name = ?, last_name = ?, specialization = ?, hire_date = ? WHERE doctor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setDate(4, Date.valueOf(doctor.getHireDate()));
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Doctor updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating doctor: " + e.getMessage());
        }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM doctors WHERE doctor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Doctor deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
        }
    }
}