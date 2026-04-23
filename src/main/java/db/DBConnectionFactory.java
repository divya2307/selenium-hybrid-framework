package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dataProvider.ConfigReader;

public class DBConnectionFactory {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    ConfigReader.getProperty("db.url"),
                    ConfigReader.getProperty("db.username"),
                    ConfigReader.getProperty("db.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to database: " + e.getMessage(), e);
        }
    }
}
