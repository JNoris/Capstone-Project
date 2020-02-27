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

    private static DatabaseManager dbManager;
    // Constants
    // Attributes
    private Connection connection = null;
    // Constructors
    public DatabaseManager() throws Exception {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/MkLN8tZlpQ?user=MkLN8tZlpQ&password=zlgBWqYw9d");
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstone_test?user=root&password=password");
    }
    
    public static DatabaseManager getInstance(){
        if(dbManager == null){
            try{
                dbManager = new DatabaseManager();
                return dbManager;
            }catch(Exception e){
                e.printStackTrace();
                System.exit(0);
                return null;
                //TODO: Remove singleton.
            }
        }else{
            return dbManager;
        }
    }
    // Methods Accessors
    // Methods Mutators
    // Methods Operational
}
