package hyenas.UI;

import hyenas.Models.Gadget;
import hyenas.Models.Shield;
import hyenas.Models.Ship;
import hyenas.Models.Weapon;
import hyenas.UI.ShipyardTab.ShipyardTabType;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Table for displaying buy items in shipyard.
 * @author Alex
 */
public class ShipyardItemsTableView extends TableView {
    private ShipyardTabType type;
    
    /**
     * Initializes a table.
     * @param type the tab type
     */
    public ShipyardItemsTableView(ShipyardTabType type) {
        setupTableForType(type);
        this.type = type;
        setEditable(false);
    }
    
    /**
     * Sets up the table for a given tab type.
     * @param type the tab type
     */
    public void setupTableForType(ShipyardTabType type) {
        setupColumnsForType(type);
        setupDataForType(type);
    }
    
    /**
     * Sets up the table columns for a given tab type.
     * @param type the tab type
     */
    private void setupColumnsForType(ShipyardTabType type) {
        switch (type) {
            case SHIPS: {
                TableColumn nameCol = nameColumn();
                TableColumn cargoCol = new MarketTableColumn("Cargo");
                TableColumn fuelCol = new MarketTableColumn("Fuel");
                TableColumn weaponsCol = new MarketTableColumn("Weapons");
                TableColumn shieldsCol = new MarketTableColumn("Shields");
                TableColumn gadgetsCol = new MarketTableColumn("Gadgets");
                TableColumn crewCol = new MarketTableColumn("Crew");
                TableColumn priceCol = priceColumn();

                cargoCol.setCellValueFactory(new PropertyValueFactory<>("cargoSlots"));
                fuelCol.setCellValueFactory(new PropertyValueFactory<>("fuel"));
                weaponsCol.setCellValueFactory(new PropertyValueFactory<>("weaponSlots"));
                shieldsCol.setCellValueFactory(new PropertyValueFactory<>("shieldSlots"));
                gadgetsCol.setCellValueFactory(new PropertyValueFactory<>("gadgetSlots"));
                crewCol.setCellValueFactory(new PropertyValueFactory<>("crewSlots"));
                
                getColumns().addAll(nameCol, cargoCol, fuelCol, weaponsCol,
                        shieldsCol, gadgetsCol, crewCol, priceCol);
                break;
            }
            case WEAPONS: {
                TableColumn nameCol = nameColumn();
                TableColumn damageCol = new MarketTableColumn("Damage");
                TableColumn priceCol = priceColumn();
                
                damageCol.setCellValueFactory(new PropertyValueFactory<>("damage"));

                getColumns().addAll(nameCol, damageCol, priceCol);
                break;
            }
            case SHIELDS: {
                TableColumn nameCol = nameColumn();
                TableColumn strengthCol = new MarketTableColumn("Strength");
                TableColumn priceCol = priceColumn();
                nameCol.setPrefWidth(150.0);

                strengthCol.setCellValueFactory(new PropertyValueFactory<>("strength"));

                getColumns().addAll(nameCol, strengthCol, priceCol);
                break;
            }
            case GADGETS: {
                TableColumn nameCol = nameColumn();
                TableColumn minTechCol = new MarketTableColumn("Min Tech Level");
                TableColumn priceCol = priceColumn();
                nameCol.setPrefWidth(150.0);

                minTechCol.setCellValueFactory(new PropertyValueFactory<>("minTechLevel"));

                getColumns().addAll(nameCol, minTechCol, priceCol);
                break;
            }
        }
    }
    
    /**
     * Sets up the table data for a given tab type.
     * @param type the tab type
     */
    private void setupDataForType(ShipyardTabType type) {
        switch (type) {
            case SHIPS:
                List<Ship> ships = Ship.getDefaultShips();
                ObservableList<Ship> shipItems = FXCollections.observableArrayList(ships);
                setItems(shipItems);
                break;
            case WEAPONS:
                List<Weapon> weapons = Weapon.getDefaultWeapons();
                ObservableList<Weapon> weaponItems = FXCollections.observableArrayList(weapons);
                setItems(weaponItems);
                break;
            case SHIELDS:
                List<Shield> shields = Shield.getDefaultShields();
                ObservableList<Shield> shieldItems = FXCollections.observableArrayList(shields);
                setItems(shieldItems);
                break;
            case GADGETS:
                List<Gadget> gadgets = Gadget.getDefaultGadgets();
                ObservableList<Gadget> gadgetItems = FXCollections.observableArrayList(gadgets);
                setItems(gadgetItems);
                break;
        }
    }
    
    /**
     * Helper for creating a name column.
     * @return a name column
     */
    private TableColumn nameColumn() {
        TableColumn nameCol = new MarketTableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return nameCol;
    }
    
    /**
     * Helper for creating a name price.
     * @return a price column
     */
    private TableColumn priceColumn() {
        TableColumn priceCol = new MarketTableColumn("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        return priceCol;
    }
}
