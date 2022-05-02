package dev.gonevski.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String connectionString = System.getenv("ERSDB_URL");

    public static Connection createConnection(){
        try {
            //System.out.println(connectionString);
            System.out.println("Connection Established.");
            return DriverManager.getConnection(connectionString);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
