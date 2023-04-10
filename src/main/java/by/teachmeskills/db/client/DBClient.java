package by.teachmeskills.db.client;

import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class DBClient {

    //TODO Change to your DB host
    public static final String DB_URL = "jdbc:mysql://localhost:3308/teachmeskills";
    public static final String USER = "root";

    //TODO Change to your root password
    public static final String PASSWORD = "12345";

    protected Connection connection = null;
    protected Statement statement = null;

    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    public void executeUpdate(String query) {
        try {
            int i = statement.executeUpdate(query);
            log.info("Rows affected count: " + i);
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
    }
}