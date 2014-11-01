package hyenas;

import hyenas.Models.Gadget;
import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.Shield;
import hyenas.Models.Ship;
import hyenas.Models.Weapon;
import hyenas.UI.AlertPane;
import hyenas.UI.AlertPane.AlertPaneType;
import hyenas.UI.ShipInfoPane;
import hyenas.UI.ShipyardItemsTableView;
import hyenas.UI.ShipyardPlayerTableView;
import hyenas.UI.ShipyardTab.ShipyardTabType;
import hyenas.UI.ShipyardTab;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML controller for buying and upgrading ships
 * @author Alex
 */
public class ShipyardController implements Initializable {
    
    @FXML
    private VBox boxPane;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label titleLabel;
    
    @FXML
    private BorderPane centerPane;
    
    @FXML
    private BorderPane tablePane;
    
    private ShipyardPlayerTableView playerShipTable =
            new ShipyardPlayerTableView(ShipyardTabType.SHIPS);
    private TableView shipsTable =
            new ShipyardItemsTableView(ShipyardTabType.SHIPS);
    private TableView weaponsTable =
            new ShipyardItemsTableView(ShipyardTabType.WEAPONS);
    private TableView gadgetsTable =
            new ShipyardItemsTableView(ShipyardTabType.GADGETS);
    private TableView shieldsTable =
            new ShipyardItemsTableView(ShipyardTabType.SHIELDS);
    private TableView currentTableView;
    
    private ShipInfoPane infoPane;
    
    private final int TAB_PANE_WIDTH = 600;
    
    private Ship ship;
    
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font titleFont = Font.loadFont(HyenasLoader.class.getResource("/hyenas/fonts/BlenderPro-Book.otf").toExternalForm(), 40);
        titleLabel.setFont(titleFont);
        titleLabel.setStyle("-fx-text-fill: rgba(0,231,255, .9); -fx-effect: dropshadow( gaussian, rgba(0,0,0,1), 0,0,2,2);");
        
        Player player = Player.getInstance();
        ship = player.getShip();
        
        tabPane = new TabPane();
        tabPane.setPrefWidth(TAB_PANE_WIDTH);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        Tab shipTab = new ShipyardTab(ShipyardTabType.SHIPS);
        Tab weaponsTab = new ShipyardTab(ShipyardTabType.WEAPONS);
        Tab shieldTab = new ShipyardTab(ShipyardTabType.SHIELDS);
        Tab gadgetTab = new ShipyardTab(ShipyardTabType.GADGETS);
        shipTab.setContent(shipsTable);
        weaponsTab.setContent(weaponsTable);
        shieldTab.setContent(shieldsTable);
        gadgetTab.setContent(gadgetsTable);
        tabPane.getTabs().addAll(shipTab, weaponsTab, shieldTab, gadgetTab);
        tabPane.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setupForTabChange(newValue));
        
        setupForTabChange(shipTab);
        
        playerShipTable.setEditable(false);
        playerShipTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> setSelectedSellItem(newValue));
        shipsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> setSelectedBuyItem(newValue));
        weaponsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> setSelectedBuyItem(newValue));
        shieldsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> setSelectedBuyItem(newValue));
        gadgetsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> setSelectedBuyItem(newValue));
        
        infoPane = new ShipInfoPane();
        Button buyButton = infoPane.getBuyButton();
        EventHandler<ActionEvent> buyEvent = (ActionEvent e) -> {
            buyItem(e);
        };
        buyButton.setOnAction(buyEvent);
        Button sellButton = infoPane.getSellButton();
        EventHandler<ActionEvent> sellEvent = (ActionEvent e) -> {
            sellItem(e);
        };
        sellButton.setOnAction(sellEvent);
        
        centerPane.setRight(infoPane);
        centerPane.setCenter(playerShipTable);
        centerPane.setLeft(tabPane);
        
        BorderPane.setMargin(tabPane, new Insets(50, 20, 50, 80));
        BorderPane.setMargin(infoPane, new Insets(50, 80, 50, 20));
        BorderPane.setMargin(playerShipTable, new Insets(80, 0, 50, 0));
    }
    
    /**
     * Set the item the user clicked on to be the selected item to sell.
     * @param item item the user clicked on
     */
    private void setSelectedSellItem(Object item) {
        if (item == null) {
            Button sellButton = infoPane.getSellButton();
            sellButton.setDisable(true);
        } else {
            Button buyButton = infoPane.getBuyButton();
            buyButton.setDisable(true);
            Button sellButton = infoPane.getSellButton();
            sellButton.setDisable(false);
            currentTableView.getSelectionModel().clearSelection();
        }
    }
    
    /**
     * Set the item the user clicked on to be the selected item to buy.
     * @param item item the user clicked on
     */
    private void setSelectedBuyItem(Object item) {
        if (item == null) {
            Button buyButton = infoPane.getBuyButton();
            buyButton.setDisable(true);
        } else {
            Button buyButton = infoPane.getBuyButton();
            buyButton.setDisable(false);
            Button sellButton = infoPane.getSellButton();
            sellButton.setDisable(true);
            playerShipTable.getSelectionModel().clearSelection();
        }
    }
    
    /**
     * Change the tab
     * @param tab new tab to display
     */
    public void setupForTabChange(Tab tab) {
        ShipyardTab shipyardTab = (ShipyardTab)tab;
        playerShipTable.setupTableForType(shipyardTab.getType());
        playerShipTable.getSelectionModel().clearSelection();
        centerPane.setCenter(playerShipTable);
        
        if (shipyardTab.getType() == ShipyardTabType.SHIPS) {
            currentTableView = shipsTable;
        } else if (shipyardTab.getType() == ShipyardTabType.WEAPONS) {
            currentTableView = weaponsTable;
        } else if (shipyardTab.getType() == ShipyardTabType.SHIELDS) {
            currentTableView = shieldsTable;
        } else if (shipyardTab.getType() == ShipyardTabType.GADGETS) {
            currentTableView = gadgetsTable;
        } else {
            currentTableView = null;
        }
        
        Label placeholderLabel = new Label(shipyardTab.getPlaceholder());
        placeholderLabel.getStyleClass().add("alertPaneTitleLabel");
        playerShipTable.setPlaceholder(placeholderLabel);
    }
    
    /**
     * Buy an item from the shipyard.
     * @param e unused
     */
    public void buyItem(ActionEvent e) {
        ShipyardTab shipyardTab = (ShipyardTab) tabPane.getSelectionModel()
                .getSelectedItem();
        Player player = Player.getInstance();
        if (shipyardTab.getType() == ShipyardTabType.WEAPONS) {
            Weapon item = (Weapon)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getWeaponSlots() > ship.getWeapons().size()) {
                if(player.getCredits() >= item.getPrice()) {
                    ship.getWeapons().add(item);
                    player.setCredits(player.getCredits() - item.getPrice());
                    
                    HyenasLoader.getInstance().getConnectionManager()
                            .getWeaponsTable().addRow(item, ship);
                }
                else {
                    displayAlert("Not Enough Credits", "You don't have enough credits to afford that.");
                }
            } else {
                displayAlert("No Weapon Slot", "There are no more slots for weapons on your ship");
            }
        }
        else if (shipyardTab.getType() == ShipyardTabType.SHIELDS) {
            Shield item = (Shield)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getShieldSlots() > ship.getShields().size())    {
                if(player.getCredits() >= item.getPrice()) {
                    ship.getShields().add(item);
                    player.setCredits(player.getCredits() - item.getPrice());
                }
                else {
                    displayAlert("Not Enough Credits", "You don't have enough credits to afford that.");
                }
            } else {
                displayAlert("No Shield Slot", "There are no more slots for shields on your ship");
            }
        }
        else if (shipyardTab.getType() == ShipyardTabType.GADGETS) {
            Planet planet = player.getTradingPlanet();
            Gadget item = (Gadget)currentTableView.getSelectionModel().getSelectedItem();
            if (ship.getGadgetSlots() > ship.getGadgets().size())    {
                if (planet.getTechLevel().ordinal() >= item.getMinTechLevel()) {
                    if (player.getCredits() >= item.getPrice()) {
                        ship.getGadgets().add(item);
                        player.setCredits(player.getCredits() - item.getPrice());
                    } else {
                        displayAlert("Not Enough Credits", "You don't have enough credits to afford that.");
                    }
                } else {
                    displayAlert("Insufficient Tech Level", "This planet doesn't have the tech level to sell this item.");
                }
            } else {
                displayAlert("No Gadget Slot", "There are no more slots for gadgets on your ship");
            }
        }
        else if (shipyardTab.getType() == ShipyardTabType.SHIPS) {
            Ship item = (Ship)currentTableView.getSelectionModel().getSelectedItem();
            if (item.getShipType() != ship.getShipType()) {
                int currentShipValue = (int) (ship.getPrice() * .8);
                if (player.getCredits() + currentShipValue >= item.getPrice()) {
                    int credits = player.getCredits();
                    player.setShip(item);
                    ship = item;
                    player.setCredits(credits + currentShipValue - item.getPrice());
                } else {
                    displayAlert("Not Enough Credits", "You don't have enough credits to afford that.");
                }
            } else {
                displayAlert("You already have this ship", "It doesn't make sense to buy a ship you already own.");
            }
        }
        infoPane.updateInfo();
        updatePlayerShipTable();
    }
    
    /**
     * Sell an item to the shipyard.
     * @param e unused
     */
    public void sellItem(ActionEvent e) {
        ShipyardTab shipyardTab = (ShipyardTab) tabPane.getSelectionModel()
                .getSelectedItem();
        Player player = Player.getInstance();
        if (shipyardTab.getType() == ShipyardTabType.WEAPONS) {
            Weapon item = (Weapon)playerShipTable.getSelectionModel().getSelectedItem();
            boolean removed = ship.getWeapons().remove(item);
            if (removed) {
                player.setCredits(player.getCredits() + item.getPrice());
            } else {
                displayAlert("No Weapon", "You do not have a weapon of this type on your ship.");
            }
        }
        else if (shipyardTab.getType() == ShipyardTabType.SHIELDS) {
            Shield item = (Shield)playerShipTable.getSelectionModel().getSelectedItem();
            boolean removed = ship.getShields().remove(item);
            if (removed) {
                player.setCredits(player.getCredits() + item.getPrice());
            } else {
                displayAlert("No Shield", "You do not have a shield of this type on your ship.");
            }
        }
        else if (shipyardTab.getType() == ShipyardTabType.GADGETS) {
            Gadget item = (Gadget)playerShipTable.getSelectionModel().getSelectedItem();
            boolean removed = ship.getGadgets().remove(item);
            if (removed) {
                player.setCredits(player.getCredits() + item.getPrice());
            } else {
                displayAlert("No Gadget", "You do not have a gadget of this type on your ship.");
            }
        } else if (shipyardTab.getType() == ShipyardTabType.SHIPS) {
            displayAlert("You can't sell your ship", "Your ship is automatically sold when you buy a new ship.");
        }
        infoPane.updateInfo();
        updatePlayerShipTable();
    }
    
    /**
     * Updates the player ship table.
     */
    private void updatePlayerShipTable() {
        ShipyardTab shipyardTab = (ShipyardTab) tabPane.getSelectionModel()
                .getSelectedItem();
        playerShipTable.setupTableForType(shipyardTab.getType());
    }
    
    /**
     * Return to the system screen
     * @param e unused
     */
    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToSystemScreen();
    }
    
    /**
     * Display an alert message
     * @param title title of the message
     * @param message message itself
     */
    private void displayAlert(String title, String message) {
        AlertPane alertPane = new AlertPane(AlertPaneType.ONEBUTTON);
        alertPane.setTitleText(title);
        alertPane.setMessageText(message);
        EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
            anchorPane.getChildren().remove(alertPane);
        };
        alertPane.getCloseButton().setOnAction(closeAction);
        anchorPane.getChildren().add(alertPane);
    }
}
