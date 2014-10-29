package hyenas.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * A weapon that can be added to a ship
 * @author Alex
 */
public class Weapon {
    private WeaponType type;
    private String name;
    
    // TODO: setup ivars, weapon enum types
    
    public enum WeaponType {
        PULSE,
        BEAM,
        MILITARY,
    }
    
    public Weapon(WeaponType type) {
        switch (type) {
            case PULSE:
                name = "Pulse";
                break;
            case BEAM:
                name = "Beam";
                break;
            case MILITARY:
                name = "Military";
                break;
            default: break;
        }
        this.type = type;
    }
    
    /**
     * Get the name of the weapon
     * @return name, the name of the weapon
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * Get the list of default buyable weapons
     * @return weapons, the list of weapons
     */
    public static List<Weapon> getDefaultWeapons() {
        ArrayList<Weapon> weapons = new ArrayList<>(WeaponType.values().length);
        for (WeaponType type: WeaponType.values()) {
            Weapon weapon = new Weapon(type);
            weapons.add(weapon);
        }
        return weapons;
    }
}
