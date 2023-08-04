package org.example.repository;

import org.example.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRepository {
    private final Connection connection;

    public EmployeeRepository(Connection connection) {
        this.connection = connection;
        this.createTable();
    }

    private void createTable() {
        String createEmployeesTableSQL = """
                CREATE TABLE IF NOT EXISTS employees (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(50) NOT NULL,
                percentage INT NOT NULL
                );
                """;
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(createEmployeesTableSQL);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void create(Employee employee) {
        String createNewEmployeeSQL = "INSERT INTO employees (name, percentage) VALUES (?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(createNewEmployeeSQL);
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getPercentage());
            stmt.execute();
            System.out.println("Employee saved successfully! \n");
        } catch (SQLException e) {
            System.out.println("Error creating new employee: " + e.getMessage());
        }
    }
}
