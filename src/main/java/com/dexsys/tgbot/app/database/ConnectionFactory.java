package com.dexsys.tgbot.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConnectionFactory {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/BirthdayBot";

    static final String USER = "user";
    static final String PASSWORD = "password";

    public Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (Throwable e) {
            throw new RuntimeException("Error while get connection");
        }
    }

    public Statement getStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Error while create statement");
        }
    }

    public ResultSet executeStatement(Statement statement, String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("Error while create statement");
        }
    }
}
