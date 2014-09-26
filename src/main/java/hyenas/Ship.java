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
    private int fuel;
    
    public Ship() {
        this.goods = new ArrayList<Good>();
    }
    
}
