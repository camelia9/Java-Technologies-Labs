package database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection connection = new DatabaseConnection();
    private Connection conn;

    private DatabaseConnection() {
        try {
            Context ctx = new InitialContext();

            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/postgres");

            if (ds != null) {
                conn = ds.getConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DatabaseConnection getConnection(){
        return connection;
    }
    public Connection getConn(){
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }
}
