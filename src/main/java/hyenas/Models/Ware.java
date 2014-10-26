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
    private int spaceTradeMinPrice;
    /* Max Price in space trade for good (Random Encounter) */
    private int spaceTradeMaxPrice;
    
    private int currentPrice;
    private int currentQuantity;
    private String currentCondition;

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
                spaceTradeMinPrice = 30;
                spaceTradeMaxPrice = 50;
                break;
            case Furs:
                name = "Furs";
                minimumTechLevelToProduce = 0;
                minimumTechLevelToUse = 0;
                techLevelProduction = 0;
                basePrice = 250;
                priceIncreasePerLevel = 10;
                variance = 10;
                spaceTradeMinPrice = 230;
                spaceTradeMaxPrice = 280;
                break;
            case Food:
                name = "Food";
                minimumTechLevelToProduce = 1;
                minimumTechLevelToUse = 0;
                techLevelProduction = 1;
                basePrice = 100;
                priceIncreasePerLevel = 5;
                variance = 5;
                spaceTradeMinPrice = 90;
                spaceTradeMaxPrice = 160;
                break;
            case Ore:
                name = "Ore";
                minimumTechLevelToProduce = 2;
                minimumTechLevelToUse = 2;
                techLevelProduction = 3;
                basePrice = 350;
                priceIncreasePerLevel = 20;
                variance = 10;
                spaceTradeMinPrice = 350;
                spaceTradeMaxPrice = 420;
                break;
            case Games:
                name = "Games";
                minimumTechLevelToProduce = 3;
                minimumTechLevelToUse = 1;
                techLevelProduction = 6;
                basePrice = 250;
                priceIncreasePerLevel = -10;
                variance = 5;
                spaceTradeMinPrice = 160;
                spaceTradeMaxPrice = 270;
                break;
            case Firearms:
                name = "Firearms";
                minimumTechLevelToProduce = 3;
                minimumTechLevelToUse = 1;
                techLevelProduction = 5;
                basePrice = 1250;
                priceIncreasePerLevel = -75;
                variance = 100;
                spaceTradeMinPrice = 600;
                spaceTradeMaxPrice = 1100;
                break;
            case Medicine:
                name = "Medicine";
                minimumTechLevelToProduce = 4;
                minimumTechLevelToUse = 1;
                techLevelProduction = 6;
                basePrice = 650;
                priceIncreasePerLevel = -20;
                variance = 10;
                spaceTradeMinPrice = 400;
                spaceTradeMaxPrice = 700;
                break;
            case Machines:
                name = "Machines";
                minimumTechLevelToProduce = 4;
                minimumTechLevelToUse = 3;
                techLevelProduction = 5;
                basePrice = 900;
                priceIncreasePerLevel = -30;
                variance = 5;
                spaceTradeMinPrice = 600;
                spaceTradeMaxPrice = 800;
                break;
            case Narcotics:
                name = "Narcotics";
                minimumTechLevelToProduce = 5;
                minimumTechLevelToUse = 0;
                techLevelProduction = 5;
                basePrice = 3500;
                priceIncreasePerLevel = -125;
                variance = 150;
                spaceTradeMinPrice = 2000;
                spaceTradeMaxPrice = 3000;
                break;
            case Robots:
                name = "Robots";
                minimumTechLevelToProduce = 6;
                minimumTechLevelToUse = 4;
                techLevelProduction = 7;
                basePrice = 5000;
                priceIncreasePerLevel = -150;
                variance = 100;
                spaceTradeMinPrice = 3500;
                spaceTradeMaxPrice = 5000;
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

    public int getMinimumTechLevelToProduce()    {
        return minimumTechLevelToProduce;
    }

    public int getMinimumTechLevelToUse()    {
        return minimumTechLevelToUse;
    }

    public int getTechLevelProduction()    {
        return techLevelProduction;
    }

    public int getBasePrice()   {
        return basePrice;
    }

    public int getPriceIncreasePerLevel() {
        return priceIncreasePerLevel;
    }

    public int getVariance() {
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
        return spaceTradeMinPrice;
    }

    public int getMTH() {
        return spaceTradeMaxPrice;
    }
    
    public int getCurrentPrice() {
        return currentPrice;
    }
    
    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
    
    public int getCurrentQuantity() {
        return currentQuantity;
    }
    
    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
    
    public String getCurrentCondition() {
        return currentCondition;
    }
    
    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }
    
    public static List<Ware> defaultWares() {
        ArrayList<Ware> wares = new ArrayList<Ware>(Good.values().length);
        for (Good good: Good.values()) {
            Ware ware = new Ware(good);
            wares.add(ware);
        }
        return wares;
    }
    
    @Override
    public String toString() {
        return "<Ware: " + name + ", Cost: " + currentPrice + ", Avl: " + currentQuantity + ">";
    }
}
