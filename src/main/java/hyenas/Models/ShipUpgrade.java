package hyenas.Models;

/**
 * Interface for buyable items
 * @author Alex
 */
public class ShipUpgrade {
    private int basePrice;
    
    public int getBuyPrice() {
        return basePrice;
    }
    
    public int getSellPrice() {
        return (int) (basePrice * .8);
    }
}
