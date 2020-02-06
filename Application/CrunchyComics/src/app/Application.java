package app;

import manager.DatabaseManager;
import java.sql.SQLException;

/**
 * Application - Starting point for the Crunchy Comics Point of Sale and
 * Inventory systems application.
 *
 * @author Vinicius Smith Goulart
 * @date January 23, 2020
 */
public class Application {

    //Constants
    //Attributes
    //Constructors
    //Methods Accessors
    public static void main(String[] args) throws Exception {
        try {
            DatabaseManager test = new DatabaseManager();
            System.out.println("Connection successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Methods Mutators
    //Methods Operational
}
