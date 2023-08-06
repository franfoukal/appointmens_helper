package org.example.repository;

import org.example.models.Appointment;
import org.example.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentRepository {
    private final Connection connection;

    public AppointmentRepository(Connection connection) {
        this.connection = connection;
        this.createTable();
    }

    private void createTable() {
        String createAppointmentTableSQL = """
                CREATE TABLE IF NOT EXISTS appointments (
                id INT PRIMARY KEY AUTO_INCREMENT,
                employee_id INT NOT NULL,
                date DATETIME NOT NULL,
                client_name VARCHAR(50) NOT NULL,
                service VARCHAR(50) NOT NULL,
                payment DOUBLE NOT NULL,
                FOREIGN KEY (employee_id) REFERENCES employees(id)
                );
                """;
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(createAppointmentTableSQL);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void create(Appointment appointment) {
        String createNewAppointmentSQL = "INSERT INTO appointments (employee_id, date, client_name,service, payment) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(createNewAppointmentSQL);
            stmt.setString(3, appointment.getClientName());
            stmt.setInt(1, appointment.getEmployeeId());
            stmt.setDate(2, new java.sql.Date(appointment.getDate().getTime()));
            stmt.setDouble(5, appointment.getPayment());
            stmt.setString(4, appointment.getService());
            stmt.execute();
            System.out.println("Appointment saved successfully! \n");
        } catch (SQLException e) {
            System.out.println("Error creating new appointment: " + e.getMessage());
        }
    }
}