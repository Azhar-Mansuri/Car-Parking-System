package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection{
    private static  final String url = "jdbc:mysql://localhost:3306/car_parking_system";
    private static final String username = "root";
    private static final String password = "Azhar@123";
    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
