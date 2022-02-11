package Model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {


    private static Connection connection = null;

    public static Connection getConnection() throws IOException, SQLException {
        if (connection == null) {
            // initialise the connection
            Properties props = new Properties();
            props.load(new FileReader("mysql.properties"));
            connection = DriverManager.getConnection(
                    props.getProperty("dburl"),
                    props.getProperty("dbuserid"),
                    props.getProperty("dbpassword"));
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null; // The fact you close a file doesn't mean that the object doesn't exist anymore. The object exists so if you want to 'delete' it, you must equalize to null so you will be able to open/close new connections. This way you can create multiple methods with the same structure but different intentionalities.
        }
    }

}
