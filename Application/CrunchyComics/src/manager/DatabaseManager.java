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
 */
public class DatabaseManager {

    private static DatabaseManager dbManager;
    private static EntityManager em;

    private Connection connection = null;

    /**
     * Default constructor. Attempts to create a connection with the MySQL
     * database server with a capstone schema.
     *
     * @throws Exception
     */
    public DatabaseManager() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstone_test?user=root&password=password");
    }

    /**
     * Gets an entity manager for communicating with the database.
     *
     * @return the entity manager.
     */
    public static EntityManager getEntityManager() {
        return em;
    }

    /**
     * Gets or creates the instance of a DatabaseManager object.
     * @return the database manager object.
     */
    public static DatabaseManager getInstance() {
        if (dbManager == null) {
            try {
                dbManager = new DatabaseManager();
                em = Persistence.createEntityManagerFactory("CrunchyComicsPU").createEntityManager();
                return dbManager;
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0); // TODO: Handle this error better.
                return null;
            }
        } else {
            return dbManager;
        }
    }
}
