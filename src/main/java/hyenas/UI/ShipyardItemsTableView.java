package hyenas.UI;

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

                nameCol.setCellValueFactory(new PropertyValueFactory<Ship, String>("name"));
                cargoCol.setCellValueFactory(new PropertyValueFactory<Ship, String>("cargoSlots"));
                fuelCol.setCellValueFactory(new PropertyValueFactory<Ship, String>("fuel"));
                weaponsCol.setCellValueFactory(new PropertyValueFactory<Ship, Integer>("weaponSlots"));
                shieldsCol.setCellValueFactory(new PropertyValueFactory<Ship, Integer>("shieldSlots"));
                gadgetsCol.setCellValueFactory(new PropertyValueFactory<Ship, Integer>("gadgetSlots"));
                crewCol.setCellValueFactory(new PropertyValueFactory<Ship, Integer>("crewSlots"));

                List<Ship> ships = Ship.getDefaultShips();
                ObservableList<Ship> playerTableData = FXCollections.observableArrayList(ships);
                setItems(playerTableData);
                getColumns().addAll(nameCol, cargoCol, fuelCol, weaponsCol, shieldsCol,
                        gadgetsCol, crewCol);
                break;
            }
            case WEAPONS: {
                TableColumn nameCol = new MarketTableColumn("Name");
                TableColumn damageCol = new MarketTableColumn("Damage");

                nameCol.setCellValueFactory(new PropertyValueFactory<Weapon, String>("name"));
//                damageCol.setCellValueFactory(new PropertyValueFactory<Weapon, String>("damage"));

                List<Weapon> weapons = Weapon.getDefaultWeapons();
                ObservableList<Weapon> playerTableData = FXCollections.observableArrayList(weapons);
                setItems(playerTableData);
                getColumns().addAll(nameCol, damageCol);
                break;
            }
            case SHIELDS: {
                
                break;
            }
            case GADGETS: {
                
                break;
            }
            default: break;
        }
        
        
    }
    
}
