/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    
    
    public Ship() {
        this.goods = new ArrayList<Good>();
        
    }
    
}
