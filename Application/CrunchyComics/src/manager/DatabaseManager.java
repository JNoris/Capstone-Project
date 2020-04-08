package manager;

import java.sql.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * DatabaseManager - Creates a database connection and provides methods to
 * perform statements against the connected database.
 *
 * @author Vinicius Smith Goulart
 * @date January 28, 2020
 */
public class DatabaseManager {

    private static DatabaseManager dbManager;
    private static EntityManager em;

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
    public static EntityManager getEntityManager(){
        return em;
    }
    public static DatabaseManager getInstance(){
        if(dbManager == null){
            try{
                dbManager = new DatabaseManager();
                em = Persistence.createEntityManagerFactory("CrunchyComicsPU").createEntityManager();
                return dbManager;
            }catch(Exception e){
                e.printStackTrace();
                System.exit(0); // TODO: Handle this error better.
                return null;
            }
        }else{
            return dbManager;
        }
    }
    // Methods Accessors
    // Methods Mutators
    // Methods Operational
}
