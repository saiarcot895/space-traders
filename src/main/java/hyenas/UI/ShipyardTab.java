package hyenas.UI;

import javafx.scene.control.Tab;

/**
 * Represents tab to display in shipyard.
 * @author Alex
 */
public class ShipyardTab extends Tab {
    /**
     * The shipyard tab type.
     */
    private ShipyardTabType type;
    /**
     * The tab placeholder text. To be displayed when tab table view is empty.
     */
    private String placeholder;
    
    /**
     * A ShipyardTabType, used to distinguish between what to populate.
     */
    public enum ShipyardTabType {
        /**
         * The ships tab.
         */
        SHIPS,
        /**
         * The weapons tab.
         */
        WEAPONS,
        /**
         * The shields tab.
         */
        SHIELDS,
        /**
         * The gadgets tab.
         */
        GADGETS,
    }
    
    /**
     * Initializes a tab.
     * @param type the shipyard tab type
     */
    public ShipyardTab(ShipyardTabType type) {
        setText(tabNameForType(type));
        this.type = type;
        this.placeholder = placeholderForType(type);
    }
    
    /**
     * Returns the name for a given shipyard type.
     * @param type the shipyard tab type
     * @return the name for the tab
     */
    private String tabNameForType(ShipyardTabType type) {
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
     * Returns the placeholder for a given shipyard tab type.
     * @param type the shipyard tab type
     * @return the placeholder for the tab
     */
    private String placeholderForType(ShipyardTabType type) {
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
    public ShipyardTabType getType() {
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
