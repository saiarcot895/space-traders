package hyenas.UI;

import hyenas.Models.Gadget;
import hyenas.Models.Player;
import hyenas.Models.Shield;
import hyenas.Models.Ship;
import hyenas.Models.Weapon;
import hyenas.UI.ShipyardTab.ShipyardTabType;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * For displaying the player's ship items in the shipyard.
 * @author Alex
 */
public class ShipyardPlayerTableView extends TableView {
    private ShipyardTabType type;
    /**
     * Initializes a table.
     * @param type the tab type
     */
    public ShipyardPlayerTableView(ShipyardTabType type) {
        setupTableForType(type);
        this.type = type;
        setEditable(false);
    }
    
    /**
     * Sets up the table for a given tab type.
     * @param type the tab type
     */
    public void setupTableForType(ShipyardTabType type) {
        Player player = Player.getInstance();
        Ship ship = player.getShip();
        switch (type) {
            case SHIPS: {
                TableColumn mainCol = new MarketTableColumn("Your Ship");
                mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mainCol.prefWidthProperty().bind(widthProperty());
                getColumns().setAll(mainCol);

                List<Ship> ships = new ArrayList<>();
                ships.add(ship);
                ObservableList<Ship> shipData = FXCollections.observableArrayList(ships);
                setItems(shipData);
                break;
            }
            case WEAPONS: {
                TableColumn mainCol = new MarketTableColumn("Your Weapons");
                mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mainCol.prefWidthProperty().bind(widthProperty());
                getColumns().setAll(mainCol);

                List<Weapon> weapons = ship.getWeapons();
                ObservableList<Weapon> weaponData = FXCollections.observableArrayList(weapons);
                setItems(weaponData);
                break;
            }
            case SHIELDS: {
                TableColumn mainCol = new MarketTableColumn("Your Shields");
                mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mainCol.prefWidthProperty().bind(widthProperty());
                getColumns().setAll(mainCol);

                List<Shield> shields = ship.getShields();
                ObservableList<Shield> shieldData = FXCollections.observableArrayList(shields);
                setItems(shieldData);
                break;
            }
            case GADGETS: {
                TableColumn mainCol = new MarketTableColumn("Your Gadgets");
                mainCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mainCol.prefWidthProperty().bind(widthProperty());
                getColumns().setAll(mainCol);

                List<Gadget> gadgets = ship.getGadgets();
                ObservableList<Gadget> gadgetData = FXCollections.observableArrayList(gadgets);
                setItems(gadgetData);
                break;
            }
        }
    }
}
