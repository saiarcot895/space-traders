package hyenas.Models;

import hyenas.Models.Ware.Good;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a ship used by the player.
 * @author Alex
 */
public class Ship {
    /**
     * The ship type.
     */
    private ShipType type;
    /**
     * The ship name.
     */
    private String name;
    /**
     * The ship number of weapon slots.
     */
    private int weaponSlots;
    /**
     * The ship number of shield slots.
     */
    private int shieldSlots;
    /**
     * The ship number of gadget slots.
     */
    private int gadgetSlots;
    /**
     * The ship number of crew slots.
     */
    private int crewSlots;
    /**
     * The ship number of cargo slots.
     */
    private int cargoSlots;
    /**
     * The ship weapons.
     */
    private List<Weapon> weapons = new ArrayList<>();
    /**
     * The ship shields.
     */
    private List<Shield> shields = new ArrayList<>();
    /**
     * The ship gadgets.
     */
    private List<Gadget> gadgets = new ArrayList<>();
    /**
     * The ship crew members.
     */
    private List<Mercenary> crew = new ArrayList<>();
    /**
     * The ship cargo.
     */
    private List<Ware> cargo = new ArrayList<>();
    /**
     * The ship fuel.
     */
    private double fuel;
    /**
     * The ship max fuel.
     */
    private double maxFuel;
    /**
     * The min tech level required to obtain the ship.
     */
    private int minTechLevel;
    /**
     * The ship price.
     */
    private int price;
    /**
     * The ship current hull.
     */
    private int currentHull;
    /**
     * The ship hull strength.
     */
    private int hullStrength;
    /**
     * The ship repair cost.
     */
    private int repairCost;
    /**
     * The ship health.
     */
    private double health;
    /**
     * The ship max health.
     */
    private double maxHealth;
    
    /**
     * A ShipType, used to distinguish between the types of ships.
     */
    public enum ShipType {
        /**
         * The flea ship (most basic).
         */
        FLEA,
        /**
         * The gnat ship (basic).
         */
        GNAT,
        /**
         * The firefly ship (standard).
         */
        FIREFLY,
        /**
         * The mostquito ship (advanced).
         */
        MOSQUITO,
        /**
         * The bumblebee ship (very advanced).
         */
        BUMBLEBEE,
    }

    /**
     * Initializes an instance of Ship, sets default values based on the type.
     * @param ptype the type of ship
     */
    public Ship(ShipType ptype) {
        switch (ptype) {
            case FLEA:
                setupFlea();
                break;
            case GNAT:
                setupGnat();
                break;
            case FIREFLY:
                setupFirefly();
                break;
            case MOSQUITO:
                setupMosquito();
                break;
            case BUMBLEBEE:
                setupBumblebee();
                break;
        }
        this.type = ptype;
    }

    /**
     * Sets up the flea ship.
     */
    private void setupFlea() {
        name = "Flea";
        maxFuel = 700.0;
        fuel = maxFuel;
        minTechLevel = 0;
        price = 100;
        hullStrength = 25;
        currentHull = hullStrength;
        repairCost = 0;
        maxHealth = 5000.0;
        health = maxHealth;
        weaponSlots = 0;
        shieldSlots = 0;
        gadgetSlots = 0;
        crewSlots = 0;
        cargoSlots = 5;
    }

    /**
     * Sets up the gnat ship.
     */
    private void setupGnat() {
        name = "Gnat";
        maxFuel = 2500.0;
        fuel = maxFuel;
        minTechLevel = 0;
        price = 500;
        hullStrength = 100;
        currentHull = hullStrength;
        repairCost = 0;
        maxHealth = 2000.0;
        health = maxHealth;
        weaponSlots = 1;
        shieldSlots = 0;
        gadgetSlots = 1;
        crewSlots = 1;
        cargoSlots = 15;
    }

    /**
     * Sets up the firefly ship.
     */
    private void setupFirefly() {
        name = "Firefly";
        maxFuel = 4000.0;
        fuel = maxFuel;
        minTechLevel = 0;
        price = 1000;
        hullStrength = 300;
        currentHull = hullStrength;
        repairCost = 0;
        maxHealth = 5000.0;
        health = maxHealth;
        weaponSlots = 1;
        shieldSlots = 1;
        gadgetSlots = 1;
        crewSlots = 1;
        cargoSlots = 20;
    }

    /**
     * Sets up the mosquito ship.
     */
    private void setupMosquito() {
        name = "Mosquito";
        maxFuel = 5000.0;
        fuel = maxFuel;
        minTechLevel = 0;
        price = 4000;
        hullStrength = 400;
        currentHull = hullStrength;
        repairCost = 0;
        maxHealth = 5000.0;
        health = maxHealth;
        weaponSlots = 2;
        shieldSlots = 2;
        gadgetSlots = 1;
        crewSlots = 2;
        cargoSlots = 15;
    }

    /**
     * Sets up the bumblebee ship.
     */
    private void setupBumblebee() {
        name = "Bumblebee";
        maxFuel = 8000.0;
        fuel = maxFuel;
        minTechLevel = 0;
        price = 10000;
        hullStrength = 500;
        currentHull = hullStrength;
        repairCost = 0;
        maxHealth = 5000.0;
        health = maxHealth;
        weaponSlots = 3;
        shieldSlots = 3;
        gadgetSlots = 2;
        crewSlots = 3;
        cargoSlots = 25;
    }
    
    /**
     * Get the ShipType.
     * @return the ship type
     */
    public ShipType getShipType() {
        return type;
    }
    
    /**
     * Set the Ship's type.
     * @param ptype the ship type
     */
    public void setShipType(ShipType ptype) {
        this.type = ptype;
    }
    
    /**
     * Get the ship's name.
     * @return name the name of the ship
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the number of weapon slots.
     * @return weaponSlots the number of weapon slots
     */
    public int getWeaponSlots() {
        return weaponSlots;
    }
    
    /**
     * Get the number of shield slots.
     * @return shieldSlots the number of shield slots
     */
    public int getShieldSlots() {
        return shieldSlots;
    }
    
    /**
     * Get the number of gadget slots.
     * @return gadgetSlots the number of gadget slots
     */
    public int getGadgetSlots() {
        return gadgetSlots;
    }
    
    /**
     * Get the number of crew slots.
     * @return crewSlots the number of crew slots
     */
    public int getCrewSlots() {
        return crewSlots;
    }
    
    /**
     * Get the number of cargo slots.
     * @return cargoSlots the number of cargo slots
     */
    public int getCargoSlots() {
        return cargoSlots;
    }
    
    /**
     * Get how much free cargo space the ship has.
     * @return the amount of free cargo space
     */
    public int getFreeCargo() {
        return cargoSlots - cargo.size();
    }

    /**
     * Get the ship's cargo.
     * @return the ship's cargo
     */
    public List<Ware> getCargo() {
        return cargo;
    }
    
    /**
     * Get the ship's weapons.
     * @return the ship's weapons
     */
    public List<Weapon> getWeapons() {
        return weapons;
    }
    
    /**
     * Get the ship's shields.
     * @return the ship's shields
     */
    public List<Shield> getShields() {
        return shields;
    }
    
    /**
     * Get the ship's gadgets.
     * @return the ship's gadgets
     */
    public List<Gadget> getGadgets() {
        return gadgets;
    }
    
    /**
     * Get the ship's crew.
     * @return the ship's crew
     */
    public List<Mercenary> getCrew() {
        return crew;
    }
    
    /**
     * Get the ship's wares (the ship's cargo in a tradeable form).
     * @return the ship's wares
     */
    public List<Ware> getWares() {
        // Returns a list of the wares stored in the ships cargo, along with
        // each wares respective quantity
        List<Ware> defaultWares = Ware.defaultWares();
        List<Ware> shipCargo = getCargo();
        defaultWares.stream().forEach((ware) -> {
                Good good = ware.getGood();
                int count = 0;
                count = shipCargo.stream().map((cargoWare) -> 
                        cargoWare.getGood()).filter((cargoGood) -> 
                                (good == cargoGood)).map
                                    ((item) -> 1).reduce(count, Integer::sum);
                ware.setCurrentQuantity(count);
            });
        return defaultWares;
    }

    /**
     * Add a ware to the ship's cargo. Returns true if successfully added
     * (there was enough free cargo space).
     * 
     * @param ware the ware to add to the ship's cargo
     * @return true if the ware was successfully added; false otherwise
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
     * Removes the first occurrence of a given ware from the ship's cargo.
     * 
     * @param toRemove the ware to remove from the ship's cargo
     * @return true if the ware was found and removed; false otherwise
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
     * Get the ship's fuel.
     * @return the ship's fuel
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * Set the ship's fuel.
     * @param pfuel the ship's fuel
     */
    public void setFuel(double pfuel) {
        this.fuel = pfuel;
    }

    /**
     * Get the ship's max fuel.
     * @return the ship's max fuel
     */
    public double getMaxFuel() {
        return maxFuel;
    }
    
    /**
     * Get the ship's health.
     * @return the ship's health
     */
    public double getHealth() {
        return health;
    }
    
    /**
     * Set the ship's health.
     * @param phealth the ship health
     */
    public void setHealth(double phealth) {
        this.health = phealth;
    }
    
    /**
     * Get the ship max health.
     * @return the ship max health
     */
    public double getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * Get the ship price.
     * @return the price of the ship
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * Get the list of default buyable ships.
     * @return ships, the list of ships
     */
    public static List<Ship> getDefaultShips() {
        ArrayList<Ship> ships = new ArrayList<>(ShipType.values().length);
        for (ShipType type: ShipType.values()) {
            Ship ship = new Ship(type);
            ships.add(ship);
        }
        return ships;
    }
}
