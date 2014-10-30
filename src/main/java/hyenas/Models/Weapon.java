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
    private int price;
    private int damage;
    
    public enum WeaponType {
        PULSE,
        BEAM,
        MILITARY,
    }
    
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
     * Get the price of the weapon
     * @return price, the price of the weapon
     */
    public int getPrice()    {
        return price;
    }
    
    /**
     * Get the damage of the weapon
     * @return damage, the damage of the weapon
     */
    public int getDamage()    {
        return damage;
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
