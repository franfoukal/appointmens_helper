package org.example.infrastructure.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {
    public static Connection connect(String url, String username, String password, String databaseName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connStr = "jdbc:mysql://" + url + "/" + databaseName;
            return DriverManager.getConnection(connStr, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to database, please check data");
            return null;
        }
    }
}
