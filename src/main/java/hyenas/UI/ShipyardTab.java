package hyenas.UI;

import hyenas.UI.ShipyardItemsTableView.ShipyardTableType;
import javafx.scene.control.Tab;

/**
 *
 * @author Alex
 */
public class ShipyardTab extends Tab {
    private ShipyardTableType type;
    private String placeholder;
    
    public ShipyardTab(ShipyardTableType type) {
        setText(tabNameForType(type));
        this.type = type;
        this.placeholder = placeholderForType(type);
    }
    
    private String tabNameForType(ShipyardTableType type) {
        switch (type) {
            case SHIPS: return "Ships";
            case WEAPONS: return "Weapons";
            case SHIELDS: return "Shields";
            case GADGETS: return "Gadgets";
            default: return "Error";
        }
    }
    
    private String placeholderForType(ShipyardTableType type) {
        switch (type) {
            case SHIPS: return "Error"; // User should always have 1 ship
            case WEAPONS: return "No Weapons Added";
            case SHIELDS: return "No Shields Added";
            case GADGETS: return "No Gadgets Added";
            default: return "Error";
        }
    }
    
    public ShipyardTableType getType() {
        return type;
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
}
