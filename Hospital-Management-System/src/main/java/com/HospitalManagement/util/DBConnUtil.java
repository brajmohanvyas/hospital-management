package com.HospitalManagement.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    private static Connection connection = null;

    public static Connection getConnection(String connectionString, String[] properties) {
        try {
            Class.forName(properties[2]);
            System.out.println("Class loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(properties[3], properties[0], properties[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Connection established");
        return connection;
    }

    public static void main(String[] args) {
        
        String connectionString = "jdbc:mysql://localhost:3306/mydatabase";
        String[] connectionProperties = {"user", "password", "com.mysql.cj.jdbc.Driver", connectionString};

        Connection dbConnection = getConnection(connectionString, connectionProperties);

       
    }
}