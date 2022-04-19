package dev.gonevski.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String password = System.getenv("API_PASSW");
    private static final String username = System.getenv("API_USERNAME");
    private static final String urlAWS = System.getenv("POSTGRES_AWS");
    private static final String connectionString = urlAWS + "user=" + username + "&password=" + password;

    public static Connection createConnection(){
        try {
            Connection conn = DriverManager.getConnection(connectionString);
            return conn;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
