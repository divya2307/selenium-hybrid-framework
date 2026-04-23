package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//preparedstatements handles parameters safely and prevents SQL injection style issues.

public class DBUtils {

    public static String getSingleColumnValue(String query, String parameter, String columnName) {
        
        try (
            Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, parameter);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(columnName);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to execute query: " + e.getMessage(), e);
        }

        return null;
    }

    public static boolean isRecordPresent(String query, String parameter) {
        
        try (
            Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, parameter);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to execute query: " + e.getMessage(), e);
        }
    }
}
