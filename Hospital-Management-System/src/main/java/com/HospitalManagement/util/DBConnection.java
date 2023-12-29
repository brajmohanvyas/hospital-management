package com.HospitalManagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    static {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            connection = DriverManager.getConnection(PropertyUtil.getPropertyString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or rethrow with a custom exception
        }
    }
}
