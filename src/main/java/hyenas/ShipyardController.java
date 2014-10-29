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
import hyenas.Models.Ware;
import hyenas.Models.Weapon;
import hyenas.UI.MarketTableColumn;
import hyenas.UI.ShipInfoPane;
import hyenas.UI.ShipTypeTableView;
import hyenas.UI.WeaponTypeTableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    
    private Tab shipTab = new Tab("Ships");
    private Tab weaponsTab = new Tab("Weapons");
    private Tab shieldTab = new Tab("Shields");
    private Tab gadgetTab = new Tab("Gadgets");
    
    private TableView playerShipTable = new TableView();
    private TableView shipTable = new ShipTypeTableView();
    private TableView weaponTable = new WeaponTypeTableView();
    private TableView shieldTable = new ShipTypeTableView();
    private TableView gadgetTable = new ShipTypeTableView();
    
    private ShipInfoPane infoPane;
    
    private final int TAB_PANE_WIDTH = 600;
    private final int TAB_PANE_HEIGHT= 400;
    
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
        shipTab.setContent(shipTable);
        weaponsTab.setContent(weaponTable);
        shieldTab.setContent(shieldTable);
        gadgetTab.setContent(gadgetTable);
        tabPane.getTabs().addAll(shipTab, weaponsTab, shieldTab, gadgetTab);
        tabPane.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setupForTabChange(newValue));
        
        playerShipTable.setEditable(false);
        playerShipTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> setSelectedSellItem(newValue));
        
        
        infoPane = new ShipInfoPane();
        centerPane.setRight(infoPane);
        centerPane.setCenter(playerShipTable);
        centerPane.setLeft(tabPane);
        
        setupForTabChange(shipTab);
        
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
            //opposite table
//            planetTable.getSelectionModel().clearSelection();
        }
    }
    
    private void setSelectedBuyItem(Ware ware) {
        if (ware == null) {
//            Button buyButton = infoPane.getBuyButton();
//            buyButton.setDisable(true);
        } else {
//            Button buyButton = infoPane.getBuyButton();
//            buyButton.setDisable(false);
//            Button sellButton = infoPane.getSellButton();
//            sellButton.setDisable(true);
//            playerTable.getSelectionModel().clearSelection();
        }
    }
    
    public void setupForTabChange(Tab tab) {
        playerShipTable.setItems(null);
        centerPane.setCenter(playerShipTable);
        Label placeholderLabel = new Label();
        placeholderLabel.getStyleClass().add("alertPaneTitleLabel");
        
        if (tab == shipTab) {
            TableColumn mainCol = new MarketTableColumn("Your Ship");
            mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            mainCol.prefWidthProperty().bind(playerShipTable.widthProperty());
            playerShipTable.getColumns().setAll(mainCol);
            
            List<Ship> ships = new ArrayList<>();
            ships.add(ship);
            ObservableList<Ship> weaponData = FXCollections.observableArrayList(ships);
            playerShipTable.setItems(weaponData);
            
            placeholderLabel.setText("Error");
        } else if (tab == weaponsTab) {
            TableColumn mainCol = new MarketTableColumn("Your Weapons");
            mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            mainCol.prefWidthProperty().bind(playerShipTable.widthProperty());
            playerShipTable.getColumns().setAll(mainCol);
            
            List<Weapon> weapons = ship.getWeapons();
            ObservableList<Weapon> weaponData = FXCollections.observableArrayList(weapons);
            playerShipTable.setItems(weaponData);
            
            placeholderLabel.setText("No Weapons Added");
        } else if (tab == shieldTab) {
            TableColumn mainCol = new MarketTableColumn("Your Shields");
            mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            mainCol.prefWidthProperty().bind(playerShipTable.widthProperty());
            playerShipTable.getColumns().setAll(mainCol);
            
            List<Shield> shields = ship.getShields();
            ObservableList<Shield> shieldData = FXCollections.observableArrayList(shields);
            playerShipTable.setItems(shieldData);
            
            placeholderLabel.setText("No Shields Added");
        } else if (tab == gadgetTab) {
            TableColumn mainCol = new MarketTableColumn("Your Gadgets");
            mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            mainCol.prefWidthProperty().bind(playerShipTable.widthProperty());
            playerShipTable.getColumns().setAll(mainCol);
            
            List<Gadget> gadgets = ship.getGadgets();
            ObservableList<Gadget> gadgetData = FXCollections.observableArrayList(gadgets);
            playerShipTable.setItems(gadgetData);
            
            placeholderLabel.setText("No Gadgets Added");
        }
        
        playerShipTable.setPlaceholder(placeholderLabel);
    }
    
    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToSystemScreen();
    }
    
}
