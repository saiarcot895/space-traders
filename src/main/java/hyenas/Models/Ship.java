package hyenas.Models;

import hyenas.Models.Ware.Good;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a ship used by the player
 * @author Alex
 */
public class Ship {
    private ShipType type;
    private String name;
    private boolean insurance;
    private int upkeep;
    private int weaponSlots;
    private int shieldSlots;
    private int gadgetSlots;
    private int crewSlots;
    private int cargoSlots;
    private List<Weapon> weapons = new ArrayList<>();
    private List<Shield> shields = new ArrayList<>();
    private List<Gadget> gadgets = new ArrayList<>();
    private List<Mercenary> crew = new ArrayList<>();
    private List<Ware> cargo = new ArrayList<>();
    private double fuel;
    private double maxFuel;
    private int minTechLevel;
    private int price;
    private int bounty;
    private int occurence;
    private int hullStrength;
    private int currentHull;
    private int police;
    private int trader;
    private int pirate;
    private int repairCost;
    private int size;
    private double health;
    private double maxHealth;
    
    public enum ShipType {
        FLEA,
        GNAT,
        FIREFLY,
    // TODO: add all ship types
    }

    /**
     * Initializes an instance of Ship
     * Sets default values based on the ship type
     * @param type, the type of ship
     */
    public Ship(ShipType type) {
        switch (type) {
            case FLEA:
                name = "Flea";
                maxFuel = 700.0;
                fuel = maxFuel;
                minTechLevel = 0;
                price = 100;
                bounty = 0;
                occurence = 0;
                hullStrength = 0;
                currentHull = 0;
                police = 0;
                trader = 0;
                pirate = 0;
                repairCost = 0;
                size = 0;
                maxHealth = 5000.0;
                health = maxHealth;
                weaponSlots = 0;
                shieldSlots = 0;
                gadgetSlots = 0;
                crewSlots = 0;
                cargoSlots = 5;
                break;
            case GNAT:
                name = "Gnat";
                maxFuel = 2500.0;
                fuel = maxFuel;
                minTechLevel = 0;
                price = 500;
                bounty = 0;
                occurence = 0;
                hullStrength = 0;
                currentHull = 0;
                police = 0;
                trader = 0;
                pirate = 0;
                repairCost = 0;
                size = 0;
                maxHealth = 2000.0;
                health = maxHealth;
                weaponSlots = 1;
                shieldSlots = 0;
                gadgetSlots = 1;
                crewSlots = 0;
                cargoSlots = 15;
                break;
            case FIREFLY:
                name = "Firefly";
                maxFuel = 4000.0;
                fuel = maxFuel;
                minTechLevel = 0;
                price = 1000;
                bounty = 0;
                occurence = 0;
                hullStrength = 0;
                currentHull = 0;
                police = 0;
                trader = 0;
                pirate = 0;
                repairCost = 0;
                size = 0;
                maxHealth = 5000.0;
                health = maxHealth;
                weaponSlots = 1;
                shieldSlots = 1;
                gadgetSlots = 1;
                crewSlots = 0;
                cargoSlots = 20;
                break;
            // TODO: add all ship types
            default: break;
        }
        this.type = type;
    }
    
    /**
     * Get the ship's name
     * @return name, the name of the ship
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the number of weapon slots
     * @return weaponSlots, the number of weapon slots
     */
    public int getWeaponSlots() {
        return weaponSlots;
    }
    
    /**
     * Get the number of shield slots
     * @return shieldSlots, the number of shield slots
     */
    public int getShieldSlots() {
        return shieldSlots;
    }
    
    /**
     * Get the number of gadget slots
     * @return gadgetSlots, the number of gadget slots
     */
    public int getGadgetSlots() {
        return gadgetSlots;
    }
    
    /**
     * Get the number of crew slots
     * @return crewSlots, the number of crew slots
     */
    public int getCrewSlots() {
        return crewSlots;
    }
    
    /**
     * Get the number of cargo slots
     * @return cargoSlots, the number of cargo slots
     */
    public int getCargoSlots() {
        return cargoSlots;
    }
    
    /**
     * Get how much free cargo space the ship has
     * @return the amount of free cargo space
     */
    public int getFreeCargo() {
        return cargoSlots - cargo.size();
    }

    /**
     * Get the ship's cargo
     * @return the ship's cargo
     */
    public List<Ware> getCargo() {
        return cargo;
    }
    
    /**
     * Get the ship's weapons
     * @return the ship's weapons
     */
    public List<Weapon> getWeapons() {
        return weapons;
    }
    
    /**
     * Get the ship's shields
     * @return the ship's shields
     */
    public List<Shield> getShields() {
        return shields;
    }
    
    /**
     * Get the ship's gadgets
     * @return the ship's gadgets
     */
    public List<Gadget> getGadgets() {
        return gadgets;
    }
    
    /**
     * Get the ship's crew
     * @return the ship's crew
     */
    public List<Mercenary> getCrew() {
        return crew;
    }
    
    /**
     * Get the ship's wares (the ship's cargo in a tradeable form)
     * @return the ship's wares
     */
    public List<Ware> getWares() {
        // Returns a list of the wares stored in the ships cargo, along with
        // each wares respective quantity
        List<Ware> defaultWares = Ware.defaultWares();
        List<Ware> shipCargo = getCargo();
        for (Ware ware: defaultWares) {
            Good good = ware.getGood();
            int count = 0;
            for (Ware cargoWare: shipCargo) {
                Good cargoGood = cargoWare.getGood();
                if (good == cargoGood) count ++;
            }
            ware.setCurrentQuantity(count);
        }
        return defaultWares;
    }

    /**
     * Add a ware to the ship's cargo. Returns true if successfully added
     * (there was enough free cargo space)
     * 
     * @param ware, the ware to add to the ship's cargo
     * @return whether the ware was added successfully
     */
    public boolean addCargo(Ware ware) {
        // Only allow cargo to be added if there is space
        int numGoods = cargo.size();
        if (++numGoods > cargoSlots) {
            return false;
        }
        cargo.add(ware);
        return true;
    }
    
    /**
     * Removes the first occurrence of a given ware from the ship's cargo
     * 
     * @param toRemove, the ware to remove from the ship's cargo
     * @return true if the ware was found and removed
     */
    public boolean removeCargo(Ware toRemove) {
        for (Ware ware: cargo) {
            if (ware.getGood() == toRemove.getGood()) {
                return cargo.remove(ware);
            }
        }
        return false;
    }
    
    /**
     * Get the ship's fuel
     * @return the ship's fuel
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * Set the ship's fuel
     * @param fuel, the ship's fuel
     */
    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    /**
     * Get the ship's max fuel
     * @return the ship's max fuel
     */
    public double getMaxFuel() {
        return maxFuel;
    }
    
    /**
     * Get the ship's health
     * @return the ship's health
     */
    public double getHealth() {
        return health;
    }
    
    /**
     * Set the ship's health
     * @param fuel, the ship's fuel
     */
    public void setHealth(double health) {
        this.health = health;
    }
    
    /**
     * Get the ship's max health
     * @return maxHealth, the ship's max health
     */
    public double getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * Get the ship's price
     * @return the ship's price
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * Get the list of default buyable ships
     * @return ships, the list of ships
     */
    public static List<Ship> getDefaultShips() {
        ArrayList<Ship> ships = new ArrayList<Ship>(ShipType.values().length);
        for (ShipType type: ShipType.values()) {
            Ship ship = new Ship(type);
            ships.add(ship);
        }
        return ships;
    }
}
