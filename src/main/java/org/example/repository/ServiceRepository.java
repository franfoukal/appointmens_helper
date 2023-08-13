package org.example.repository;

import org.example.models.Employee;
import org.example.models.Service;

import java.sql.*;
import java.util.ArrayList;

public class ServiceRepository {
    private final Connection connection;

    public ServiceRepository(Connection connection) {
        this.connection = connection;
        this.createTable();
    }

    private void createTable() {
        String createServiceTableSQL = """
                CREATE TABLE IF NOT EXISTS services (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(50) NOT NULL,
                duration INT NOT NULL
                );
                """;
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(createServiceTableSQL);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void create(Service service) {
        String createNewServiceSQL = "INSERT INTO services (name, duration) VALUES (?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(createNewServiceSQL);
            stmt.setString(1, service.getName());
            stmt.setInt(2, service.getDuration());
            stmt.execute();
            System.out.println("Service saved successfully! \n");
        } catch (SQLException e) {
            System.out.println("Error creating new service: " + e.getMessage());
        }
    }

    public ArrayList<Service> getAll() {
        String getAllServiceSQL = """
                SELECT * FROM services
                """;

        try {
            PreparedStatement stmt = this.connection.prepareStatement(getAllServiceSQL);
            ResultSet result = stmt.executeQuery();
            ArrayList<Service> servicesList = new ArrayList<>();

            while (result.next()) {
                String name = result.getString("name");
                int duration = result.getInt("duration");
                int id = result.getInt("id");

                Service service = new Service(id, name, duration);
                servicesList.add(service);
            }

            return servicesList;
        } catch (SQLException e) {
            System.out.println("Error fetching all services: " + e.getMessage());
            return null;
        }
    }

}
