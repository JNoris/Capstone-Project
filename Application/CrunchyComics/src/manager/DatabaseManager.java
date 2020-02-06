package manager;

import java.sql.*;

/**
 * DatabaseManager - Creates a database connection and provides methods to
 * perform statements against the connected database.
 *
 * @author Vinicius Smith Goulart
 * @date January 28, 2020
 */
public class DatabaseManager {

    // Constants
    // Attributes
    private Connection connection = null;
    // Constructors
    public DatabaseManager() throws Exception {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/MkLN8tZlpQ?user=MkLN8tZlpQ&password=zlgBWqYw9d");
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/captstone_test?user=root&password=password");
    }
    // Methods Accessors
    // Methods Mutators
    // Methods Operational
}
