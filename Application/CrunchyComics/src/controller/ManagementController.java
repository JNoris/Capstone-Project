package controller;

import broker.ItemBroker;
import broker.OrderBroker;
import domain.Item;
import domain.OrderItem;
import domain.Orders;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.layout.VBox;
import manager.ControllerManager;
import manager.DatabaseManager;
import ui.ManagementUIItemElement;
import ui.ManagementUIOrderElement;

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
    @FXML
    private Button newBtn;

    private boolean itemMode = false;

    /**
     * Loads the <code>newBtn</code> to invisible until needed.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newBtn.visibleProperty().set(false);
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

    /**
     * Sets the <code>newBtn</code> to <code>true</code> and loads all of the items currently in the database.
     */
    public void populateItems() {
        newBtn.visibleProperty().set(true);
        itemMode = true;
        System.out.println("Populating area with Items...");

        populateArea.getChildren().clear();
        ItemBroker itemBroker = new ItemBroker(DatabaseManager.getInstance(), DatabaseManager.getEntityManager());
        for (Item i : itemBroker.getAllItems()) {
            populateArea.getChildren().add(new ManagementUIItemElement(this, i));
        }
    }
    
    /**
     * Sets the <code>newBtn</code> to <code>true</code> and loads all of the orders currently in the database.
     */
    public void populateOrders() {
        newBtn.visibleProperty().set(true);
        itemMode = false;
        populateArea.getChildren().clear();
        OrderBroker orderBroker = new OrderBroker();
        for (Orders o : orderBroker.getAllOrders()) {
            populateArea.getChildren().add(new ManagementUIOrderElement(this, o));
        }
        System.out.println("Populating area with Orders.");
    }
    
    /**
     * Creates the popup on top the Management scene and transfers control to the ManagementUIItemElementController.
     * @param element
     * @param item 
     */
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
            controller.setManagementController(this);
            controller.populate();
        } catch (IOException e) {
            System.out.println("Could not create management item popup. " + e.getMessage());
        }
    }

     /**
     * Creates the popup on top the Management scene and transfers control to the ManagementUIOrderElementController.
     * @param element
     * @param order
     */
    public void createOrderManagementPopup(ManagementUIOrderElement element, Orders order) {
        Popup popup = new Popup();
        ControllerManager.getInstance().setPopup(popup);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ManagementOrderPopup.fxml"));
        ManagementUIOrderController controller = new ManagementUIOrderController();
        loader.setController(controller);
        try {
            popup.getContent().add((Parent) loader.load());
            popup.show(ControllerManager.getInstance().getWindow());
            controller.setOrder(order);
            controller.populate();
        } catch (IOException e) {
            System.out.println("Could not create management order popup. " + e.getMessage());
        }
    }

     /**
     * Creates the popup on top the Management scene and transfers control to the ManagementNewOrderController.
     * @param order
     */
    public void createNewOrderPopup(Orders order) {
        Popup popup = new Popup();
        ControllerManager.getInstance().setPopup(popup);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ManagementNewOrderPopup.fxml"));
        ManagementNewOrderController controller = new ManagementNewOrderController();
        loader.setController(controller);
        try {
            popup.getContent().add((Parent) loader.load());
            popup.show(ControllerManager.getInstance().getWindow());
            controller.setOrder(order);
            controller.setManagementController(this);
        } catch (IOException e) {
            System.out.println("Could not create management new order popup. " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Creates the logic depending on what scene is shown. Shows the popup for either ItemManagementPopup or the new OrderPopup.
     */
    public void newBtnClicked() {
        if (itemMode) {
            Item item = new Item();
            createItemManagementPopup(null, item);
        } else {
            Orders order = new Orders();
            createNewOrderPopup(order);
        }
    }
    
    /**
     * Changes to the InventoryReportPopup as well as control to the Inventory Report Controller.
     */
    public void createInventoryReportClicked() {
        Popup popup = new Popup();
        ControllerManager.getInstance().setPopup(popup);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createInventoryReportPopup.fxml"));
        InventoryReportController controller = new InventoryReportController();
        loader.setController(controller);
        try {
            popup.getContent().add((Parent) loader.load());
            popup.show(ControllerManager.getInstance().getWindow());
        } catch (IOException e) {
            System.out.println("Could not create management inventory report popup. " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Changes to the SaleReportPopup as well as control to the SaleReportController.
     */
    public void createSalesReportClicked() {
        Popup popup = new Popup();
        ControllerManager.getInstance().setPopup(popup);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createSaleReportPopup.fxml"));
        SaleReportController controller = new SaleReportController();
        loader.setController(controller);
        try {
            popup.getContent().add((Parent) loader.load());
            popup.show(ControllerManager.getInstance().getWindow());
            
        } catch (IOException e) {
            System.out.println("Could not create management sale report popup. " + e.getMessage());
            e.printStackTrace();
        }
    }
}
