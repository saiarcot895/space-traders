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
 * For use with shipyard. Displays weapons you can buy
 * @author Alex
 */
public class WeaponTypeTableView extends TableView {
    public WeaponTypeTableView() {
        setEditable(false);
        
        TableColumn nameCol = new MarketTableColumn("Name");
        TableColumn damageCol = new MarketTableColumn("Damage");
        
        nameCol.setCellValueFactory(new PropertyValueFactory<Weapon, String>("name"));
//        damageCol.setCellValueFactory(new PropertyValueFactory<Weapon, String>("cargoSlots"));
        
        List<Weapon> weapons = Weapon.getDefaultWeapons();
        ObservableList<Weapon> playerTableData = FXCollections.observableArrayList(weapons);
        setItems(playerTableData);
        getColumns().addAll(nameCol, damageCol);
    }
}
