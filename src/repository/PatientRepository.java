package repository;

import model.Patient;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    // CREATE
    public void create(Patient patient) {
        String sql = "INSERT INTO patients (first_name, last_name, birth_date, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, Date.valueOf(patient.getBirthDate()));
            stmt.setString(4, patient.getPhone());
            stmt.executeUpdate();
            System.out.println("Patient created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating patient: " + e.getMessage());
        }
    }

    // READ ALL
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date").toLocalDate(),
                        rs.getString("phone")
                );
                patients.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching patients: " + e.getMessage());
        }
        return patients;
    }

    // READ BY ID
    public Patient getById(int id) {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date").toLocalDate(),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching patient by ID: " + e.getMessage());
        }
        return null;
    }

    // UPDATE
    public void update(int id, Patient patient) {
        String sql = "UPDATE patients SET first_name = ?, last_name = ?, birth_date = ?, phone = ? WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, Date.valueOf(patient.getBirthDate()));
            stmt.setString(4, patient.getPhone());
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Patient updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
    }
}