package com.facebook.DAOConnection;

import java.sql.DriverManager;

public class Connection {

    static java.sql.Connection connection;
    public static java.sql.Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/facebook","postgres","vasanth16");
            System.out.println("Connection successfully");
            return connection;
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }
}
