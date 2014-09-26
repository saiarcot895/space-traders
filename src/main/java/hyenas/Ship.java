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
    
    public boolean addCargo(Good good) {
        if (good.equals(Good.Water)) {
            goods.add(Good.Water);
        } else if (good.equals(Good.Furs)) {
            goods.add(Good.Furs);
        } else if (good.equals(Good.Food)) {
            goods.add(Good.Food);
        } else if (good.equals(Good.Ore)) {
            goods.add(Good.Ore);
        } else if (good.equals(Good.Games)) {
            goods.add(Good.Games);
        } else if (good.equals(Good.Firearms)) {
            goods.add(Good.Firearms);
        } else if (good.equals(Good.Machines)) {
            goods.add(Good.Machines);
        } else if (good.equals(Good.Medicine)) {
            goods.add(Good.Medicine);
        } else if (good.equals(Good.Narcotics)) {
            goods.add(Good.Narcotics);
        } else if (good.equals(Good.Robots)) {
            goods.add(Good.Robots);
        } else {
            throw new IllegalArgumentException("Not adding a valid good!");
        }
        return true;
    }
    
    public boolean removeCargo(Good good) {
        if (good.equals(Good.Water)) {
            if (goods.contains(Good.Water)) {
                goods.remove(Good.Water);
            }
        } else if (good.equals(Good.Furs)) {
            if (goods.contains(Good.Furs)) {
                goods.remove(Good.Furs);
            }
        } else if (good.equals(Good.Food)) {
            if (goods.contains(Good.Food)) {
                goods.remove(Good.Food);
            }
        } else if (good.equals(Good.Ore)) {
            if (goods.contains(Good.Ore)) {
                goods.remove(Good.Ore);
            }
        } else if (good.equals(Good.Games)) {
            if (goods.contains(Good.Games)) {
                goods.remove(Good.Games);
            }
        } else if (good.equals(Good.Firearms)) {
            if (goods.contains(Good.Firearms)) {
                goods.remove(Good.Firearms);
            }
        } else if (good.equals(Good.Machines)) {
            if (goods.contains(Good.Machines)) {
                goods.remove(Good.Machines);
            }
        } else if (good.equals(Good.Medicine)) {
            if (goods.contains(Good.Medicine)) {
                goods.remove(Good.Medicine);
            }
        } else if (good.equals(Good.Narcotics)) {
            if (goods.contains(Good.Narcotics)) {
                goods.remove(Good.Narcotics);
            }
        } else if (good.equals(Good.Robots)) {
            if (goods.contains(Good.Robots)) {
                goods.remove(Good.Robots);
            }
        } else {
            return false;
        }
        return true;
    }
    
}
