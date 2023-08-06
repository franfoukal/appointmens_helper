package org.example.repository;

import org.example.models.Employee;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Employee> getAll() {
        String getAllEmployeesSql = "SELECT * FROM employees";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(getAllEmployeesSql);
            ResultSet result = stmt.executeQuery();
            ArrayList<Employee> employeesList = new ArrayList<>();

            while(result.next()){
                String name = result.getString("name");
                int percentage = result.getInt("percentage");
                int id = result.getInt("id");

                Employee employee = new Employee(name, percentage, id);
                employeesList.add(employee);
            }

            return employeesList;
        } catch (SQLException e) {
            System.out.println("Error fetching all employees: " + e.getMessage());
            return null;
        }
    }
}