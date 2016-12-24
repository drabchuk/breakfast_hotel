package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Denis on 20.11.2016.
 */
public class DatabaseConnector {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/study";
    private static final String USER = "denis";
    private static final String PASS = "denis";

    /*static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            e.printStackTrace();
        }
    }*/

    public DatabaseConnector() throws SQLException{
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
