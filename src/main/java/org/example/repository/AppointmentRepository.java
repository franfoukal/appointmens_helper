package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentRepository {
    private final Connection connection;

    public AppointmentRepository(Connection connection) {
        this.connection = connection;
    }
}