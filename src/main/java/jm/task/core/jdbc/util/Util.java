package jm.task.core.jdbc.util;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String Url = "jdbc:mysql://localhost/new_schema";
    private static final String Username = "root";
    private static final String Password = "76315as";
    

    public static Connection getConnection()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Url, Username, Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
