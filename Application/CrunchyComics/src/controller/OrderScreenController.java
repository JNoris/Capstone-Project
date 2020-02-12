package controller;

import broker.TransactionBroker;
import domain.Item;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Noris. UMM I MEAN: CAPSTONE GROUP, OF COURSE, TIS NOT MY WORK BUT OUR
 *         WORK.
 * 
 * @Notes Please make sure to correct this code. Namely: Password as well as
 *        validation.
 * 
 */
public class OrderScreenController implements Initializable {
    
    @FXML
    private VBox searchDisplay;
    @FXML
    private Label saleIDDisplay;
    @FXML
    private TextField searchField;
    @FXML
    private Label subtotalDisplay;
    @FXML
    private Label taxDisplay;
    @FXML
    private Label finalPriceDisplay;
    @FXML
    private Label itemNameDisplay;
    @FXML
    private Label itemPriceDisplay;
    @FXML
    private Label itemQuantityDisplay;
    @FXML
    private VBox itemListDisplay;
    @FXML
    private VBox resultContainer;

    private Item item;
    private TransactionBroker tb;
    private Button result;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    /**
     * This method is called by clicking "Logout" which logs the user out of the
     * application and redirect to login screen
     * 
     * @param event
     * @throws IOException
     */
    public void logoutBtnClicked(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene logout = new Scene(loginParent);

        // This line grabs the Stage information
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        loginWindow.setScene(logout);
        loginWindow.show();
    }

    /**
     * When TextField searchField is initiated (by clicking on the search field in
     * the order screen), whatever is typed in the field will call
     * textfield.getText(); which grabs the fields input and outputs query
     * underneath in the results pane.
     * 
     * @param event
     * @throws IOException
     */
    public void initiateSearch(ActionEvent event) throws IOException 
    {
        // searchField is the fxid
        result = new Button();
        
        HBox itemContainer = new HBox(50);
        
        EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) 
            { 
                if(searchField.getText().equals(item.getName()))
                {
                    itemContainer.getChildren().addAll(new Label(item.getName()),
                                                            new Label(Integer.toString(item.getItemID())),
                                                                new Label(Float.toString(item.getPrice())),
                                                                    result);
                    //No idea how to add the button correctly
                    
                }
            } 
        }; 

        /**
         * When the enter key is pressed
         */
        searchField.setOnAction(e); 

    }

    /**
     * Grab an item to the sale side
     */
    public void addItemToSale()
    {
        HBox itemContainer = new HBox(10);

        /**
         * Need a getSaleNumber for the broker
         */
        if(result.isPressed())
        {
            //saleIDDisplay.setText("Sale Order " +  tb.getSaleNumber());
            itemNameDisplay.setText(item.getName());
            itemPriceDisplay.setText(Float.toString(item.getPrice()));
            itemQuantityDisplay.setText(Integer.toString(item.getQuantity()));

            itemContainer.getChildren().addAll(new Label( item.getName() ), 
                                            new Label( Float.toString(item.getPrice()) ),
                                            new Label( Integer.toString( item.getQuantity() ) ));

            /**
             * Add the item container to the itemListDisplay
             */
            itemListDisplay.getChildren().addAll(itemContainer.getChildren());
        }
    }
        
}