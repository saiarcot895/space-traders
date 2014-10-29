package hyenas.Models;

import java.util.ArrayList;
import java.util.List;
/**
 * A shield that can be added to a ship
 * @author Alex
 */
public class Shield {
    // TODO: setup ivars, shield enum types
    private ShieldType type;
    private String name;
    private int price;
    
    public enum ShieldType {
        CIVILIAN_ENERGY,
        MILITIA_ENERGY,
        MILITARY_ENERGY,
        CIVILIAN_REFLECTIVE,
        MILITIA_REFLECTIVE,
        MILITARY_REFLECTIVE
    }
    
    public Shield(ShieldType type) {
        switch (type) {
            case CIVILIAN_ENERGY:
                name = "Civilian Energy";
                price = 100;
                break;
            case MILITIA_ENERGY:
                name = "Militia Energy";
                price = 200;
                break;
            case MILITARY_ENERGY:
                name = "Military Energy";
                price = 300;
                break;
            case CIVILIAN_REFLECTIVE:
                name = "Civilian Reflective";
                price = 400;
                break;
            case MILITIA_REFLECTIVE:
                name = "Militia Reflective";
                price = 500;
                break;
            case MILITARY_REFLECTIVE:
                name = "Military Reflective";
                price = 600;
                break;
            default: break;
        }
        this.type = type;
    }
    
    /**
     * Get the name of the shield
     * @return name, the name of the shield
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the price of the shield
     * @return price, the price of the shield
     */
    public int getPrice()    {
        return price;
    }
    
    /**
     * Get the list of default buyable shields
     * @return shields, the list of shields
     */
    public static List<Shield> getDefaultShields() {
        ArrayList<Shield> shields = new ArrayList<>(ShieldType.values().length);
        for (ShieldType type: ShieldType.values()) {
            Shield shield = new Shield(type);
            shields.add(shield);
        }
        return shields;
    }
}
