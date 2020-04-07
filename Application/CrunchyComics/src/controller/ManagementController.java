package controller;

import broker.ItemBroker;
import domain.Item;
import javafx.scene.input.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.fxml.*;
import javafx.scene.paint.Color;
import javafx.event.*;
import javafx.scene.layout.VBox;
import manager.ControllerManager;
import manager.DatabaseManager;
import ui.ManagementUIItemElement;

/**
 *
 * @author Noris. UMM I MEAN: CAPSTONE GROUP, OF COURSE, TIS NOT MY WORK BUT OUR
 * WORK.
 *
 * @Notes This is probably the most incomplete GUI as of Feb 17th. Change the
 * font to Arial Black, set a divider between tabs and the content they show,
 * etc.
 * @Events will have to be handled. My main question is is in the interaction
 * model we have things like the Edit button and all that, isn't this
 * repetitive? We already have Add/Edit Inventory on the left side.
 *
 */
public class ManagementController implements Initializable {

    @FXML
    private VBox populateArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println("populateArea: " + populateArea);
    }

    /**
     * Redirects user to Main Screen when user clicks "Home".
     *
     * @param event
     * @throws IOException
     */
    public void homeBtnClicked(ActionEvent event) throws IOException {
        ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getMainScreen());
    }

    public void populateItems() {
        System.out.println("Populating area with Items...");

        populateArea.getChildren().clear();
        ItemBroker itemBroker = new ItemBroker(DatabaseManager.getInstance(), DatabaseManager.getEntityManager());
        for (Item i : itemBroker.getAllItems()) {
            populateArea.getChildren().add(new ManagementUIItemElement(this, i));
        }
    }

    public void populateOrders() {
        populateArea.getChildren().clear();

        System.out.println("Populating area with Orders.");
    }

    public void createItemManagementPopup(ManagementUIItemElement element, Item item) {
        System.out.println("Popup " + item.getName());
        Popup popup = new Popup();
        ControllerManager.getInstance().setPopup(popup);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ManagementItemPopup.fxml"));
        ManagementUIItemElementController controller = new ManagementUIItemElementController();
        loader.setController(controller);
        try {
            popup.getContent().add((Parent) loader.load());
            popup.show(ControllerManager.getInstance().getWindow());
            controller.setItem(item);
            controller.setManagementUIItemElement(element);
            controller.populate();
        }catch (IOException e){
            System.out.println("Could not create management item popup.");
        }

    }
}
