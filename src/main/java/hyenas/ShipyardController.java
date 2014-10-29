/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.Models.Gadget;
import hyenas.Models.Player;
import hyenas.Models.Shield;
import hyenas.Models.Ship;
import hyenas.Models.Weapon;
import hyenas.UI.AlertPane;
import hyenas.UI.AlertPaneType;
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
 * For buying ship
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font titleFont = Font.loadFont(HyenasLoader.class.getResource("/hyenas/fonts/BlenderPro-Book.otf").toExternalForm(), 40);
        titleLabel.setFont(titleFont);
        titleLabel.setStyle("-fx-text-fill: rgba(0,231,255, .9); -fx-effect: dropshadow( gaussian, rgba(0,0,0,1), 0,0,2,2);");
        
        Player player = Player.getInstance();
        ship = player.getShip();
        
        TabPane tabPane = new TabPane();
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
    
    public void buyItem(ActionEvent e) {
        removeAlert();
        if(currentTableView == weaponsTable)    {
            Weapon item = (Weapon)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getWeaponSlots() > ship.getWeapons().size())    {
                if(Player.getInstance().getCredits() >= item.getPrice())    {
                    ship.getWeapons().add(item);
                    Player.getInstance().setCredits(Player.getInstance().getCredits()-item.getPrice());
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
        else if(currentTableView == gadgetsTable)   {
            Gadget item = (Gadget)currentTableView.getSelectionModel().getSelectedItem();
            if(ship.getGadgetSlots() > ship.getGadgets().size())    {
                if(Player.getInstance().getCredits() >= item.getPrice())    {
                    ship.getGadgets().add(item);
                    Player.getInstance().setCredits(Player.getInstance().getCredits()-item.getPrice());
                }
                else    {
                    displayAlert("Not Enough Credits", "You don't have enough credits to afford that.");
                }
            }
            else    {
                displayAlert("No Gadget Slot", "There are no more slots for gadgets on your ship");
            }
        }
        else if(currentTableView == shipsTable) {
            Ship item = (Ship)currentTableView.getSelectionModel().getSelectedItem();
            if(Player.getInstance().getCredits() >= item.getPrice())    {
                Player.getInstance().setShip(item);
                Player.getInstance().setCredits(Player.getInstance().getCredits()-item.getPrice());
            }
            else    {
                displayAlert("Not Enough Credits", "You don't have enough credits to afford that.");
           }
            
        }
        infoPane.updateInfo();
        // TODO:
        // Buying a ship has addition consequences (See wiki), so it needs to be
        // handled more carefully
        // For the rest, its really just interfacing with the Ship class, which
        // will need methods like addGadget and addShield (refer to addCargo method)
        // 
        // Make sure player has enough credits
        // Make sure player ahs enough slots for item they're buying
        // Example: If buying weapon, make sure weapon slots aren't filled
        // If any conditions are not met, use displayAlert() method to inform user
        // ... and probably more things I'm forgetting
    }

    public void sellItem(ActionEvent e) {
        removeAlert();
        
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
        }
        infoPane.updateInfo();
        
        // TODO:
        // Handle edge cases - for example, a player must have a ship (cant sell
        // if he only has one)
        // Must update the player's credits
        // Must remove the item from player's table after it is sold (or just refrsh the table)
        // ... and probably more things I'm forgetting
    }
    
    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToSystemScreen();
    }
    
    private void displayAlert(String title, String message) {
        AlertPane alertPane = new AlertPane(AlertPaneType.OneButton);
        alertPane.setTitleText(title);
        alertPane.setMessageText(message);
        EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
            anchorPane.getChildren().remove(alertPane);
        };
        alertPane.getCloseButton().setOnAction(closeAction);
        anchorPane.getChildren().add(alertPane);
    }
    
    private void removeAlert() {
        List children = anchorPane.getChildren();
        if (children.size() > 1) {
            children.remove(children.get(1));
        }
    }
}
