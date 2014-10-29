package hyenas.UI;

import hyenas.Models.Ship;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * For use with shipyard. Displays ships you can buy
 * @author Alex
 */
public class ShipTypeTableView extends TableView {
    public ShipTypeTableView() {
        setEditable(false);
        
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
    }
}
