package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

public class AppointmentRepository {
    private final Connection connection;

    public AppointmentRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(int patientId, int doctorId, LocalDate date, String notes) throws SQLException {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, notes) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setDate(3, Date.valueOf(date));
            ps.setString(4, notes);
            ps.executeUpdate();
        }
    }

    public List<String> getAll() throws SQLException {
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
        }
        return appointments;
    }
}