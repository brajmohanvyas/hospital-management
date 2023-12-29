package com.HospitalManagement.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private static final String PROPERTY_FILE_PATH = "C:\\Users\\Hp\\eclipse-workspace\\Hospital-Management-System\\src\\main\\java\\config.properties"; // Adjust the file path as needed

    public static String getPropertyString() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTY_FILE_PATH)) {
            properties.load(input);

            String hostname = properties.getProperty("db.hostname");
            String dbname = properties.getProperty("db.dbname");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String port = properties.getProperty("db.port");

           
            return "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;

        } catch (IOException e) {
            e.printStackTrace(); 
            return null; 
        }
    }
}
