package domain;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

public class Transaction {

    private ArrayList<Item> itemList = new ArrayList<Item>();
    // ObservableList<String> itemList =
    // FXCollections.<String>observableArrayList();
    private int total;
    private int quantity;
    Item item;
    private ListView itemListView = new ListView();

    /**
     * Temporary hardcoded data for Use Case #1
     */
    public Transaction() {
    }

    /**
     * addItem adds an item and all it's essential information and loads all of it
     * to the GUI
     */
    public void addItem() {
        itemList.add(new Item(00000000, "Ice Ice Baby", 100.99, "It's an 'Ice Ice Baby'", 2000013293, "Drink", 8));
        itemList.add(new Item(00000000, "Ice Ice Baby", 100.99, "It's an 'Ice Ice Baby'", 2000013293, "Drink", 8));
        itemList.add(new Item(00000002, "Iced Tea", 1.99, "Iced Tea Drink from Nestea", 2000012693, "Drink", 20));
        itemList.add(new Item(00000003, "Iron Man Vol 1", 9.99, "Original 1968 Iron Man Comic Vol #1", 161484100,
                "Comic", 3));
        itemList.add(new Item(00000004, "Iron Man Vol 2", 9.99, "Original 1969 Iron Man Comic Vol #2", 161484101,
                "Comic", 1));
        itemList.add(new Item(00000005, "Iron Man Vol 3", 9.99, "Original 1969 Iron Man Comic Vol #3", 161484102,
                "Comic", 2));
        itemList.add(item);
        for (Item i : itemList) {
            itemListView.getItems().add(itemList);
            System.out.println(i);
        }
    }

    public void removeFromList() {
        itemList.remove(item);
        for (Item i : itemList) {
            System.out.println(i);
        }
    }
}