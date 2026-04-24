package com.cozycrochet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    // Updated credentials to match your database setup
    private static final String URL = "jdbc:derby://localhost:1527/CrochetStoreDB";
    private static final String USER = "crochet_admin";
    private static final String PASS = "password";

    public static Connection getConnection() throws SQLException {
        try {
            // Load the Derby driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
            throw new SQLException("Database driver not found.");
        }
    }
}