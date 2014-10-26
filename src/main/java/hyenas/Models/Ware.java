package hyenas.Models;

import java.util.ArrayList;
import java.util.List;

public class Ware {

    private String name;
    private int minimumTechLevelToProduce;
    private int minimumTechLevelToUse;
    private int techLevelProduction;
    private int basePrice;
    private int priceIncreasePerLevel;
    private int variance;
    /* For Extra Credit TODO! */
    private String ie;
    /* For Extra Credit TODO! */
    private String cr;
    /* For Extra Credit TODO! */
    private String er;
    /* Min Price in space trade for good (Random Encounter) */
    private int mtl;
    /* Max Price in space trade for good (Random Encounter) */
    private int mth;
    
    private int currentPrice;
    private int currentQuantity;

    private Good good;

    public Ware(Good good) {
        this.good = good;
        setUp();
    }

    private void setUp() {
        switch(good) {
            case Water:
                name = "Water";
                minimumTechLevelToProduce = 0;
                minimumTechLevelToUse = 0;
                techLevelProduction = 2;
                basePrice = 30;
                priceIncreasePerLevel = 3;
                variance = 4;
                mtl = 30;
                mth = 50;
                break;
            case Furs:
                name = "Furs";
                minimumTechLevelToProduce = 0;
                minimumTechLevelToUse = 0;
                techLevelProduction = 0;
                basePrice = 250;
                priceIncreasePerLevel = 10;
                variance = 10;
                mtl = 230;
                mth = 280;
                break;
            case Food:
                name = "Food";
                minimumTechLevelToProduce = 1;
                minimumTechLevelToUse = 0;
                techLevelProduction = 1;
                basePrice = 100;
                priceIncreasePerLevel = 5;
                variance = 5;
                mtl = 90;
                mth = 160;
                break;
            case Ore:
                name = "Ore";
                minimumTechLevelToProduce = 2;
                minimumTechLevelToUse = 2;
                techLevelProduction = 3;
                basePrice = 350;
                priceIncreasePerLevel = 20;
                variance = 10;
                mtl = 350;
                mth = 420;
                break;
            case Games:
                name = "Games";
                minimumTechLevelToProduce = 3;
                minimumTechLevelToUse = 1;
                techLevelProduction = 6;
                basePrice = 250;
                priceIncreasePerLevel = -10;
                variance = 5;
                mtl = 160;
                mth = 270;
                break;
            case Firearms:
                name = "Firearms";
                minimumTechLevelToProduce = 3;
                minimumTechLevelToUse = 1;
                techLevelProduction = 5;
                basePrice = 1250;
                priceIncreasePerLevel = -75;
                variance = 100;
                mtl = 600;
                mth = 1100;
                break;
            case Medicine:
                name = "Medicine";
                minimumTechLevelToProduce = 4;
                minimumTechLevelToUse = 1;
                techLevelProduction = 6;
                basePrice = 650;
                priceIncreasePerLevel = -20;
                variance = 10;
                mtl = 400;
                mth = 700;
                break;
            case Machines:
                name = "Machines";
                minimumTechLevelToProduce = 4;
                minimumTechLevelToUse = 3;
                techLevelProduction = 5;
                basePrice = 900;
                priceIncreasePerLevel = -30;
                variance = 5;
                mtl = 600;
                mth = 800;
                break;
            case Narcotics:
                name = "Narcotics";
                minimumTechLevelToProduce = 5;
                minimumTechLevelToUse = 0;
                techLevelProduction = 5;
                basePrice = 3500;
                priceIncreasePerLevel = -125;
                variance = 150;
                mtl = 2000;
                mth = 3000;
                break;
            case Robots:
                name = "Robots";
                minimumTechLevelToProduce = 6;
                minimumTechLevelToUse = 4;
                techLevelProduction = 7;
                basePrice = 5000;
                priceIncreasePerLevel = -150;
                variance = 100;
                mtl = 3500;
                mth = 5000;
                break;
            default:
                // Do nothing
        }
    }

    public String getName() {
        return name;
    }
    
    public Good getGood() {
        return good;
    }

    public int getMTLP()    {
        return minimumTechLevelToProduce;
    }

    public int getMTLU()    {
        return minimumTechLevelToUse;
    }

    public int getTTP()    {
        return techLevelProduction;
    }

    public int getBasePrice()   {
        return basePrice;
    }

    public int getIPL() {
        return priceIncreasePerLevel;
    }

    public int getVar() {
        return variance;
    }

    public String getIE()   {
        return ie;
    }

    public String getCR()   {
        return cr;
    }

    public String getER()   {
        return er;
    }

    public int getMTL() {
        return mtl;
    }

    public int getMTH() {
        return mth;
    }
    
    public int getCurrentPrice() {
        return currentPrice;
    }
    
    public int getCurrentQuantity() {
        return currentQuantity;
    }
    
    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
    
    public static List<Ware> defaultWares() {
        ArrayList<Ware> wares = new ArrayList<Ware>(Good.values().length);
        for (Good good: Good.values()) {
            Ware ware = new Ware(good);
            wares.add(ware);
        }
        return wares;
    }
    
    public static List<Ware> waresForPlanet(Planet planet) {
        int numGoods = Good.values().length;
        ArrayList<Ware> wares = new ArrayList<Ware>(numGoods);
        int techLevel = planet.getTechLevel();
        
        for (Good good: Good.values()) {
//            Water,
//            Furs,
//            Food,
//            Ore,
//            Games,
//            Firearms,
//            Medicine,
//            Machines,
//            Narcotics,
//            Robots
            Ware ware = new Ware(good);
            
            ware.currentQuantity = howMuchToProduce(good, planet);
            // TODO: vaiance is not correct
            ware.currentPrice = ware.basePrice + (ware.priceIncreasePerLevel * (techLevel - ware.minimumTechLevelToProduce)) + ware.variance;
//            Name	MTLP	MTLU	TTP	Base Price	IPL	Var	IE	CR              ER	MTL	MTH
//            Water	0	0	2	30              3       4       DROUGHT	LOTSOFWATER	DESERT	30	50
            
            
//            (the base price) + (the IPL * (Planet Tech Level - MTLP)) + (variance)
            wares.add(ware);
            //only add if planet is able to produce?
            
        }
        
        return wares;
    }
    
    private static final int[] TTP = {2, 0, 1, 3, 6, 5, 6, 5, 5, 7};
    private static final int[] MTLP = {0, 0, 1, 2, 3, 3, 4, 4, 5, 6};
    
    private static int howMuchToProduce(Good good, Planet planet) {
        int techLevel = planet.getTechLevel();
        int index = good.ordinal();
        if (techLevel < MTLP[index]) {
            return 0;
        } else {
            // TODO include affects caused by events and planet type
            return 10 - Math.abs(TTP[index] - techLevel);
        }
    }
    
    @Override
    public String toString() {
        return "<Ware: " + name + ", Cost: " + currentPrice + ", Avl: " + currentQuantity + ">";
    }
}
