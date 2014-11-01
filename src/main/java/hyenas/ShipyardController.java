package hyenas;

import hyenas.Models.Gadget;
import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.Shield;
import hyenas.Models.Ship;
import hyenas.Models.Weapon;
import hyenas.UI.AlertPane;
import hyenas.UI.AlertPane.AlertPaneType;
import hyenas.UI.MarketTableColumn;
import hyenas.UI.ShipInfoPane;
import hyenas.UI.ShipyardItemsTableView;
import hyenas.UI.ShipyardItemsTableView.ShipyardTableType;
import hyenas.UI.ShipyardTab;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private TableView playerShipTable = new TableView();
    private TableView shipsTable =
            new ShipyardItemsTableView(ShipyardTableType.SHIPS);
    private TableView weaponsTable =
            new ShipyardItemsTableView(ShipyardTableType.WEAPONS);
    private TableView gadgetsTable =
            new ShipyardItemsTableView(ShipyardTableType.GADGETS);
    private TableView shieldsTable =
            new ShipyardItemsTableView(ShipyardTableType.SHIELDS);
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
        Tab shipTab = new ShipyardTab(ShipyardTableType.SHIPS);
        Tab weaponsTab = new ShipyardTab(ShipyardTableType.WEAPONS);
        Tab shieldTab = new ShipyardTab(ShipyardTableType.SHIELDS);
        Tab gadgetTab = new ShipyardTab(ShipyardTableType.GADGETS);
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
        playerShipTable.setItems(null);
        playerShipTable.getSelectionModel().clearSelection();
        centerPane.setCenter(playerShipTable);
        
        if (shipyardTab.getType() == ShipyardTableType.SHIPS) {
            TableColumn mainCol = new MarketTableColumn("Your Ship");
            mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            mainCol.prefWidthProperty().bind(playerShipTable.widthProperty());
            playerShipTable.getColumns().setAll(mainCol);
            
            List<Ship> ships = new ArrayList<>();
            ships.add(ship);
            ObservableList<Ship> weaponData = FXCollections.observableArrayList(ships);
            playerShipTable.setItems(weaponData);
            
            currentTableView = shipsTable;
        } else if (shipyardTab.getType() == ShipyardTableType.WEAPONS) {
            TableColumn mainCol = new MarketTableColumn("Your Weapons");
            mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            mainCol.prefWidthProperty().bind(playerShipTable.widthProperty());
            playerShipTable.getColumns().setAll(mainCol);
            
            List<Weapon> weapons = ship.getWeapons();
            ObservableList<Weapon> weaponData = FXCollections.observableArrayList(weapons);
            playerShipTable.setItems(weaponData);
            
            currentTableView = weaponsTable;
        } else if (shipyardTab.getType() == ShipyardTableType.SHIELDS) {
            TableColumn mainCol = new MarketTableColumn("Your Shields");
            mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            mainCol.prefWidthProperty().bind(playerShipTable.widthProperty());
            playerShipTable.getColumns().setAll(mainCol);
            
            List<Shield> shields = ship.getShields();
            ObservableList<Shield> shieldData = FXCollections.observableArrayList(shields);
            playerShipTable.setItems(shieldData);
            
            currentTableView = shieldsTable;
        } else if (shipyardTab.getType() == ShipyardTableType.GADGETS) {
            TableColumn mainCol = new MarketTableColumn("Your Gadgets");
            mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            mainCol.prefWidthProperty().bind(playerShipTable.widthProperty());
            playerShipTable.getColumns().setAll(mainCol);
            
            List<Gadget> gadgets = ship.getGadgets();
            ObservableList<Gadget> gadgetData = FXCollections.observableArrayList(gadgets);
            playerShipTable.setItems(gadgetData);
            
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
        Player player = Player.getInstance();
        ship = player.getShip();
        if(currentTableView == weaponsTable)    {
            Weapon item = (Weapon)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getWeaponSlots() > ship.getWeapons().size())    {
                if(Player.getInstance().getCredits() >= item.getPrice())    {
                    ship.getWeapons().add(item);
                    Player.getInstance().setCredits(Player.getInstance().getCredits()-item.getPrice());
                    
                    HyenasLoader.getInstance().getConnectionManager()
                            .getWeaponsTable().addRow(item, ship);
                }
                else    {
                    displayAlert("Not Enough Credits", "You don't have enough credits to afford that.");
                }
            }
            else    {
                displayAlert("No Weapon Slot", "There are no more slots for weapons on your ship");
            }
        }
        else if(currentTableView == shieldsTable)   {
            Shield item = (Shield)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getShieldSlots() > ship.getShields().size())    {
                if(Player.getInstance().getCredits() >= item.getPrice())    {
                    ship.getShields().add(item);
                    Player.getInstance().setCredits(Player.getInstance().getCredits()-item.getPrice());
                }
                else    {
                    displayAlert("Not Enough Credits", "You don't have enough credits to afford that.");
                }
            }
            else    {
                displayAlert("No Shield Slot", "There are no more slots for shields on your ship");
            }
        }
        else if (currentTableView == gadgetsTable) {
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
            }
            else {
                displayAlert("No Gadget Slot", "There are no more slots for gadgets on your ship");
            }
        }
        else if(currentTableView == shipsTable) {
            Ship currentShip = player.getShip();
            Ship item = (Ship)currentTableView.getSelectionModel().getSelectedItem();
            if (item.getShipType() != currentShip.getShipType()) {
                int currentShipValue = (int) (currentShip.getPrice() * .8);
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
        setupForTabChange(tabPane.getSelectionModel().getSelectedItem());
    }
    
    /**
     * Sell an item to the shipyard.
     * @param e unused
     */
    public void sellItem(ActionEvent e) {
        // Note: object type shouldn't necessarily be Object
        if(currentTableView == weaponsTable)    {
            Weapon item = (Weapon)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getWeapons().contains(item))    {
                ship.getWeapons().remove(item);
                Player.getInstance().setCredits(Player.getInstance().getCredits()+item.getPrice());
            }
            else    {
                displayAlert("No Weapon", "You do not have a weapon of this type on your ship.");
            }
        }
        else if(currentTableView == shieldsTable)   {
            Shield item = (Shield)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getShields().contains(item))    {
                ship.getShields().remove(item);
                Player.getInstance().setCredits(Player.getInstance().getCredits()+item.getPrice());
            }
            else    {
                displayAlert("No Shield", "You do not have a shield of this type on your ship.");
            }
        }
        else if(currentTableView == gadgetsTable)   {
            Gadget item = (Gadget)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getGadgets().contains(item))    {
                ship.getGadgets().remove(item);
                Player.getInstance().setCredits(Player.getInstance().getCredits()+item.getPrice());
            }
            else    {
                displayAlert("No Gadget", "You do not have a gadget of this type on your ship.");
            }
        } else if(currentTableView == shipsTable)   {
            displayAlert("You can't sell your ship", "Your ship is automatically sold when you buy a new ship.");
        }
        infoPane.updateInfo();
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
