package ru.hetoiiblpb.main;

import ru.hetoiiblpb.util.DBHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = DBHelper.getProperties();
        String url = DBHelper.getProperties().getProperty("url");
        String username = DBHelper.getProperties().getProperty("user");
        String password = DBHelper.getProperties().getProperty("password");
        System.out.println("Connecting...");

        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), username,password)){
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
