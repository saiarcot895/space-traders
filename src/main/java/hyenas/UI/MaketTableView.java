package hyenas.UI;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Table view for use with Marketplace.
 * @author Alex
 */
public class MaketTableView extends TableView {
    /**
     * The name property value for use with populating column.
     */
    private static final String NAME_PROPERTY_VALUE = "name";
    /**
     * The current quantity property value for use with populating column.
     */
    private static final String CURRENT_QUANTITY_PROPERTY_VALUE = "currentQuantity";
    
    /**
     * For distinguishing between market tables.
     */
    public enum MarketTableType {
        /**
         * For the planet market table.
         */
        PLANET,
        /**
         * For the player market table.
         */
        PLAYER
    }
    
    /**
     * Initializes table view, sets up columns for type.
     * @param ptype the market table type
     */
    public MaketTableView(MarketTableType ptype) {
        switch (ptype) {
            case PLANET: {
                TableColumn wareCol = new MarketTableColumn("Ware");
                TableColumn availableCol = new MarketTableColumn("Available");
                TableColumn priceCol = new MarketTableColumn("Price");
                TableColumn contitionsCol = new MarketTableColumn("Conditions");
                wareCol.setCellValueFactory(
                        new PropertyValueFactory<>(NAME_PROPERTY_VALUE)
                );
                availableCol.setCellValueFactory(
                        new PropertyValueFactory<>(CURRENT_QUANTITY_PROPERTY_VALUE)
                );
                priceCol.setCellValueFactory(
                        new PropertyValueFactory<>("currentPrice")
                );
                contitionsCol.setCellValueFactory(
                        new PropertyValueFactory<>("currentCondition")
                );
                
                getColumns().addAll(wareCol, availableCol, priceCol, contitionsCol);
                break;
            }
            case PLAYER: {
                TableColumn playerWareCol = new MarketTableColumn("Your Ware");
                TableColumn cargoCol = new MarketTableColumn("Your Cargo");
                playerWareCol.setCellValueFactory(
                        new PropertyValueFactory<>(NAME_PROPERTY_VALUE)
                );
                cargoCol.setCellValueFactory(
                        new PropertyValueFactory<>(CURRENT_QUANTITY_PROPERTY_VALUE)
                );
                
                getColumns().addAll(playerWareCol, cargoCol);
                break;
            }
        }
    }
}
