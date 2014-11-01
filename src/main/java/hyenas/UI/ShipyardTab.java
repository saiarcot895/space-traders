package hyenas.UI;

import hyenas.UI.ShipyardItemsTableView.ShipyardTableType;
import javafx.scene.control.Tab;

/**
 * Represents tab to display in shipyard.
 * @author Alex
 */
public class ShipyardTab extends Tab {
    private ShipyardTableType type;
    private String placeholder;
    
    /**
     * Initializes a tab.
     * @param type the shipyard table type
     */
    public ShipyardTab(ShipyardTableType type) {
        setText(tabNameForType(type));
        this.type = type;
        this.placeholder = placeholderForType(type);
    }
    
    /**
     * Returns the name for a given shipyard type.
     * @param type the shipyard table type
     * @return the name for the tab
     */
    private String tabNameForType(ShipyardTableType type) {
        switch (type) {
            case SHIPS:
                return "Ships";
            case WEAPONS:
                return "Weapons";
            case SHIELDS:
                return "Shields";
            case GADGETS:
                return "Gadgets";
            default:
                return "Unnimplemented Name";
        }
    }
    
    /**
     * Returns the placeholder for a given shipyard type.
     * @param type the shipyard type
     * @return the placeholder for the tab
     */
    private String placeholderForType(ShipyardTableType type) {
        switch (type) {
            case SHIPS:
                return "[Error - player should have a ship]";
            case WEAPONS:
                return "No Weapons Added";
            case SHIELDS:
                return "No Shields Added";
            case GADGETS:
                return "No Gadgets Added";
            default:
                return "Unimplemented Paceholder";
        }
    }
    
    /**
     * Get the shipyard tab type.
     * @return the shipyard tab table type
     */
    public ShipyardTableType getType() {
        return type;
    }
    
    /**
     * Get the shipyard tab placeholder.
     * @return the shipyard tab placeholder
     */
    public String getPlaceholder() {
        return placeholder;
    }
}
