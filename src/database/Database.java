package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is a base class to open the connection to the database and close
 * the existing connection.
 */
public class Database {
    static final String DB_URL = "jdbc:mysql://localhost:3306/todolist";
    static final String DB_USERNAME = "ppm";
    static final String DB_PASSWORD = "s3cure";

    /**
     * Open the connection with the database credentials.
     * @return a connection to the database
     */
    public static Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /**
     * Close the connection passed as the parameter. 
     * @param connection the connection to be closed
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
