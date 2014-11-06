package hyenas.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * A weapon that can be added to a ship.
 * @author Alex
 */
public class Weapon implements ShipyardBuyable {
    /**
     * The weapon type.
     */
    private WeaponType type;
    /**
     * The weapon name.
     */
    private String name;
    /**
     * The price of the weapon.
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
     * @param ptype the type of weapon
     */
    public Weapon(WeaponType ptype) {
        switch (ptype) {
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
        this.type = ptype;
    }
    
    /**
     * Get the name of the weapon.
     * @return the name of the weapon
     */
    public String getName() {
        return name;
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
    
    @Override
    public int getPrice() {
        return price;
    }
    
    @Override
    public boolean hasFreeSlots(Ship ship) {
        return ship.getWeaponSlots() > ship.getWeapons().size();
    }
    
    @Override
    public boolean hasSufficientTechLevel(Planet planet) {
        return true;
    }

    @Override
    public List getShipItems(Ship ship) {
        return ship.getWeapons();
    }
    
    /**
     * Get the list of default weapons.
     * @return the list of weapons
     */
    public static List getDefaultWeapons() {
        ArrayList<Weapon> weapons = new ArrayList<>(WeaponType.values().length);
        for (WeaponType atype: WeaponType.values()) {
            Weapon weapon = new Weapon(atype);
            weapons.add(weapon);
        }
        return weapons;
    }
}