package hyenas.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * A weapon that can be added to a ship.
 * @author Alex
 */
public class Weapon {
    /**
     * The weapon type.
     */
    private WeaponType type;
    /**
     * The weapon name.
     */
    private String name;
    /**
     * The weapon price.
     */
    private int price;
    /**
     * The weapon damage.
     */
    private int damage;
    
    /**
     * A WeaponType, used to distinguish between the types of weapon.
     */
    public enum WeaponType {
        /**
         * Pulse weapon (Weakest).
         */
        PULSE,
        /**
         * Beam weapon (Medium strength).
         */
        BEAM,
        /**
         * Military weapon (Strongest).
         */
        MILITARY,
    }
    
    /**
     * Initializes an instance of Weapon.
     * @param type the type of weapon
     */
    public Weapon(WeaponType type) {
        switch (type) {
            case PULSE:
                name = "Pulse";
                price = 100;
                damage = 100;
                break;
            case BEAM:
                name = "Beam";
                price = 200;
                damage = 300;
                break;
            case MILITARY:
                name = "Military";
                price = 300;
                damage = 500;
                break;
            default:
                break;
        }
        this.type = type;
    }
    
    /**
     * Get the name of the weapon.
     * @return the name of the weapon
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the price of the weapon.
     * @return the price of the weapon
     */
    public int getPrice()    {
        return price;
    }
    
    /**
     * Get the damage of the weapon.
     * @return the damage of the weapon
     */
    public int getDamage()    {
        return damage;
    }

    /**
     * Get the type of the weapon.
     * @return the type of the weapon
     */
    public WeaponType getType() {
        return type;
    }
    
    /**
     * Get the list of default buyable weapons.
     * @return the list of weapons
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
