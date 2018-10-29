package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    /*
        Not best practice :D
     */
    private static final String USERNAME = "vpfwfbqcejndnx";
    private static final String PASSWORD = "11681540ce013005c36666af5d01a9e67a529bffe077dff87df9d76650987929";
    private static final String HOST = "ec2-46-137-75-170.eu-west-1.compute.amazonaws.com";
    private static final String DATABASE = "d4qttr3ugp08um";
    private static final String DATABASE_TYPE = "postgresql";
    private static final int PORT = 5432;
    private static final String URI = String.format(
            "jdbc:%s://%s:%d/%s?sslmode=require&user=%s&password=%s",
            DATABASE_TYPE, HOST, PORT, DATABASE, USERNAME, PASSWORD
    );

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URI);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
