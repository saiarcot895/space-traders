package hyenas;

import hyenas.Wares.Good;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Ship {
    private boolean insurance;
    private int upkeep;
    private ArrayList<Good> goods;
    private int weaponSlots[] = new int[2];
    private int shieldSlots[] = new int[2];
    private int gadgetSlots[] = new int[2];
//    private NPC[] crew; TODO: Implement NPC class
    private int fuel;
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
    
    public Ship() {
        this.goods = new ArrayList<Good>();
        
    }
    
    public int getFreeCargo() {
        return maxCargo - goods.size();
    }
    
    public ArrayList<Good> getCargo() {
        return goods;
    }
    
    public void addCargo(Good good) {
        goods.add(good);
    }
    
    public boolean removeCargo(Good good) {
        return goods.remove(good);
    }
    
}
