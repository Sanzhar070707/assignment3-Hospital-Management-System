package repository;

import exception.DatabaseOperationException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository implements CrudRepository<String> {
    private final Connection connection;

    public AppointmentRepository(Connection connection) {
        this.connection = connection;
    }

    // ✅ Реализация метода из CrudRepository
    @Override
    public void create(String appointmentData) {
        throw new UnsupportedOperationException("Use create(patientId, doctorId, date, notes) instead.");
    }

    // ✅ Основной метод создания записи
    public void create(int patientId, int doctorId, LocalDate date, String notes) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, notes) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setDate(3, Date.valueOf(date));
            ps.setString(4, notes);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error creating appointment", e);
        }
    }

    @Override
    public List<String> getAll() {
        List<String> appointments = new ArrayList<>();
        String sql = "SELECT appointment_id, patient_id, doctor_id, appointment_date, notes FROM appointments";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                appointments.add("Appointment ID: " + rs.getInt("appointment_id") +
                        ", Patient ID: " + rs.getInt("patient_id") +
                        ", Doctor ID: " + rs.getInt("doctor_id") +
                        ", Date: " + rs.getDate("appointment_date") +
                        ", Notes: " + rs.getString("notes"));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching appointments", e);
        }
        return appointments;
    }

    @Override
    public String getById(int id) {
        String sql = "SELECT appointment_id, patient_id, doctor_id, appointment_date, notes FROM appointments WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Appointment ID: " + rs.getInt("appointment_id") +
                        ", Patient ID: " + rs.getInt("patient_id") +
                        ", Doctor ID: " + rs.getInt("doctor_id") +
                        ", Date: " + rs.getDate("appointment_date") +
                        ", Notes: " + rs.getString("notes");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching appointment by ID", e);
        }
        return null;
    }

    @Override
    public void update(int id, String updatedNotes) {
        String sql = "UPDATE appointments SET notes = ? WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, updatedNotes);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error updating appointment", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting appointment", e);
        }
    }
}