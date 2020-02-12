package app;

import broker.ItemBroker;
import domain.*;
import manager.DatabaseManager;
import java.sql.SQLException;
import javafx.application.Application;
/**
 * MainDriver - Starting point for the Crunchy Comics Point of Sale and
 Inventory systems application.
 *
 * @author Vinicius Smith Goulart
 * @date January 23, 2020
 */
public class MainDriver{

    //Constants
    //Attributes
    //Constructors
    //Methods Accessors
    public static void main(String[] args) throws Exception {
        try {
            DatabaseManager test = new DatabaseManager();
            System.out.println("Connection successful.");
            ItemBroker ib = new ItemBroker(test);
           Item item = ib.getItemByID(1);
            System.out.println(item.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Methods Mutators
    //Methods Operational
}
