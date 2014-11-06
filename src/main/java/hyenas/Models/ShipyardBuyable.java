package hyenas.Models;

import java.util.List;

/**
 * An buyable item at the shipyard.
 * @author Alex
 */
public abstract class ShipyardBuyable {
    /**
     * The price of the item.
     */
    private int price;
    
    /**
     * Checks whether the ship has free slots for the item.
     * @param ship the ship to check
     * @return true if the ship has free slots; false otherwise
     */
    public abstract boolean hasFreeSlots(Ship ship);
    
    /**
     * Get the list of corresponding items on the ship.
     * @param ship the ship to get the items from
     * @return the list of corresponding items
     */
    public abstract List getShipItems(Ship ship);
    
    /**
     * Get the list of default buyable items.
     * @return  the list of items
     */
    public static List getDefaultItems() {
        return null;
    }
    
    /**
     * Get the price of the item.
     * @return the price of the item
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * Set the price of the item.
     * @param pprice the price of the item
     */
    public void setPrice(int pprice) {
        this.price = pprice;
    }
    
    /**
     * Whether the planet has a sufficient tech level to sell this item. Default
     * is true.
     * @param planet the planet to check
     * @return true if the planet can sell the item; false otherwise
     */
    public boolean hasSufficientTechLevel(Planet planet) {
        return true;
    }
}
