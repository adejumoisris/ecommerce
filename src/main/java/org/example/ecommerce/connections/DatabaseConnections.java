package org.example.ecommerce.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnections {
    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false",
                    "root", "password");
            System.out.println("Connected to database");
        }
        return connection;
    }
//
//    public static void setConnection(Connection connection) {
//        DatabaseConnections.connection = connection;
//    }
}
