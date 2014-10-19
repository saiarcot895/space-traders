package hyenas.Models;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Ship {
    private ShipType type;
    private boolean insurance;
    private int upkeep;
    private List<Good> goods;
    private int weaponSlots[] = new int[2];
    private int shieldSlots[] = new int[2];
    private int gadgetSlots[] = new int[2];
//    private NPC[] crew; TODO: Implement NPC class
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
    private int maxCargo;

    public Ship(ShipType type) {
        switch (type) {
            case BUTTERFLY: {
                maxFuel = 4000.0;
                fuel = maxFuel;
                minTechLevel = 0;
                price = 0;
                bounty = 0;
                occurence = 0;
                hullStrength = 0;
                currentHull = 0;
                police = 0;
                trader = 0;
                pirate = 0;
                repairCost = 0;
                size = 0;
                maxCargo = 0;
                break;
            }
            case GNAT: {
                maxFuel = 2500.0;
                fuel = maxFuel;
                minTechLevel = 0;
                price = 0;
                bounty = 0;
                occurence = 0;
                hullStrength = 0;
                currentHull = 0;
                police = 0;
                trader = 0;
                pirate = 0;
                repairCost = 0;
                size = 0;
                maxCargo = 0;
                break;
            }
            case FLEA:
            default: {
                maxFuel = 700.0;
                fuel = maxFuel;
                minTechLevel = 0;
                price = 0;
                bounty = 0;
                occurence = 0;
                hullStrength = 0;
                currentHull = 0;
                police = 0;
                trader = 0;
                pirate = 0;
                repairCost = 0;
                size = 0;
                maxCargo = 0;
                break;
            }
        }
        this.type = type;
        this.goods = new ArrayList<Good>();
    }

    public int getFreeCargo() {
        return maxCargo - goods.size();
    }

    public List<Good> getCargo() {
        return goods;
    }

    public void addCargo(Good good) {
        goods.add(good);
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public double getFuel() {
        return fuel;
    }

    public boolean removeCargo(Good good) {
        return goods.remove(good);
    }

}