package hyenas.UI;

import hyenas.Models.Gadget;
import hyenas.Models.Shield;
import hyenas.Models.Ship;
import hyenas.Models.Weapon;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Alex
 */
public class ShipyardItemsTableView extends TableView {
    private ShipyardTableType type;
    
    public enum ShipyardTableType {
        SHIPS,
        WEAPONS,
        SHIELDS,
        GADGETS,
    }
    
    public ShipyardItemsTableView(ShipyardTableType type) {
        setupTableForType(type);
        this.type = type;
        setEditable(false);
    }
    
    public void setupTableForType(ShipyardTableType type) {
        switch (type) {
            case SHIPS: {
                TableColumn nameCol = new MarketTableColumn("Name");
                TableColumn cargoCol = new MarketTableColumn("Cargo");
                TableColumn fuelCol = new MarketTableColumn("Fuel");
                TableColumn weaponsCol = new MarketTableColumn("Weapons");
                TableColumn shieldsCol = new MarketTableColumn("Shields");
                TableColumn gadgetsCol = new MarketTableColumn("Gadgets");
                TableColumn crewCol = new MarketTableColumn("Crew");
                TableColumn priceCol = new MarketTableColumn("Price");

                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                cargoCol.setCellValueFactory(new PropertyValueFactory<>("cargoSlots"));
                fuelCol.setCellValueFactory(new PropertyValueFactory<>("fuel"));
                weaponsCol.setCellValueFactory(new PropertyValueFactory<>("weaponSlots"));
                shieldsCol.setCellValueFactory(new PropertyValueFactory<>("shieldSlots"));
                gadgetsCol.setCellValueFactory(new PropertyValueFactory<>("gadgetSlots"));
                crewCol.setCellValueFactory(new PropertyValueFactory<>("crewSlots"));
                priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

                List<Ship> ships = Ship.getDefaultShips();
                ObservableList<Ship> playerTableData = FXCollections.observableArrayList(ships);
                setItems(playerTableData);
                getColumns().addAll(nameCol, cargoCol, fuelCol, weaponsCol, shieldsCol,
                        gadgetsCol, crewCol, priceCol);
                break;
            }
            case WEAPONS: {
                TableColumn nameCol = new MarketTableColumn("Name");
                TableColumn damageCol = new MarketTableColumn("Damage");
                TableColumn priceCol = new MarketTableColumn("Price");
                
                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                damageCol.setCellValueFactory(new PropertyValueFactory<>("damage"));
                priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

                List<Weapon> weapons = Weapon.getDefaultWeapons();
                ObservableList<Weapon> playerTableData = FXCollections.observableArrayList(weapons);
                setItems(playerTableData);
                getColumns().addAll(nameCol, damageCol, priceCol);
                break;
            }
            case SHIELDS: {
                TableColumn nameCol = new MarketTableColumn("Name");
                TableColumn strengthCol = new MarketTableColumn("Strength");
                TableColumn priceCol = new MarketTableColumn("Price");
                nameCol.setPrefWidth(150.0);

                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                strengthCol.setCellValueFactory(new PropertyValueFactory<>("strength"));
                priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

                List<Shield> shields = Shield.getDefaultShields();
                ObservableList<Shield> playerTableData = FXCollections.observableArrayList(shields);
                setItems(playerTableData);
                getColumns().addAll(nameCol, strengthCol, priceCol);
                break;
            }
            case GADGETS: {
                TableColumn nameCol = new MarketTableColumn("Name");
                TableColumn priceCol = new MarketTableColumn("Price");
                TableColumn minTechCol = new MarketTableColumn("Min Tech Level");
                nameCol.setPrefWidth(150.0);

                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                minTechCol.setCellValueFactory(new PropertyValueFactory<>("minTechLevel"));

                List<Gadget> gadgets = Gadget.getDefaultGadgets();
                ObservableList<Gadget> playerTableData = FXCollections.observableArrayList(gadgets);
                setItems(playerTableData);
                getColumns().addAll(nameCol, priceCol, minTechCol);
                break;
            }
            default: break;
        }
    }
}
