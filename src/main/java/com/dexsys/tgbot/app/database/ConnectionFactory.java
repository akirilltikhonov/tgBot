package com.dexsys.tgbot.app.database;

import com.dexsys.tgbot.app.userMock.UserDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.UUID;

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

    public Statement createStatement(Connection connection) {
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

    private String getQuery() {
        return "SELECT * FROM USERS";
    }

    private UserDto processResultSet(ResultSet resultSet) throws SQLException {
        UserDto userDto = new UserDto();
        
        while (resultSet.next()) {
//            userDto = UserDto.builder()
//                    .id(UUID.fromString(resultSet.getString("id")))
//                    .firstName(resultSet.getString("firstName"))
//                    .secondName(resultSet.getString("secondName"))
//                    .middleName(resultSet.getString("middleName"))
//                    .birthDay(resultSet.getDate("birthDay"))
//                    .phone(resultSet.getString("phone"))
//                    .chatId(resultSet.getString("chatId"))
//                    .male(resultSet.getBoolean("male"))
//                    .build();
            userDto.setId(UUID.fromString(resultSet.getString("id")));
            userDto.setFirstName(resultSet.getString("firstName"));
            userDto.setSecondName(resultSet.getString("secondName"));
            userDto.setMiddleName(resultSet.getString("middleName"));
            userDto.setBirthDay(resultSet.getDate("birthDay"));
            userDto.setPhone(resultSet.getString("phone"));
            userDto.setChatId(resultSet.getString("chatId"));
            userDto.setMale(resultSet.getBoolean("male"));

        }
        return userDto;
    }

    public UserDto process() throws SQLException {

        String query = getQuery();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            try {
                statement = createStatement(connection);
                try {
                    resultSet = executeStatement(statement, query);
                    return processResultSet(resultSet);
                } finally {
                    assert resultSet != null;
                    resultSet.close();
                }
            } finally {
                assert statement != null;
                statement.close();
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("All is very BAD");
            }
        }

    }

    public Connection getConnectionTransactional() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (Throwable e) {
            throw new RuntimeException("Error while get connection");
        }
    }

    public UserDto processTransactional() throws SQLException {

        String query = getQuery();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            try {
                statement = createStatement(connection);
                try {
                    resultSet = executeStatement(statement, query);
                    final UserDto userDto = processResultSet(resultSet);
                    connection.commit();
                    return userDto;
                } finally {
                    assert resultSet != null;
                    resultSet.close();
                }
            } finally {
                assert statement != null;
                statement.close();
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("All is very BAD");
            }
        }

    }
}
