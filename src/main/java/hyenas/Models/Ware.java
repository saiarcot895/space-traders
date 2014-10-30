package hyenas.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Ware object, for use when buying/selling Goods
 */
public class Ware {
    private String name;
    private Good good;
    private int minimumTechLevelToProduce;
    private int minimumTechLevelToUse;
    private int techLevelProduction;
    private int basePrice;
    private int priceIncreasePerLevel;
    private int variance;
    private int spaceTradeMinPrice;
    private int spaceTradeMaxPrice;
    
    private int currentPrice;
    private int currentQuantity;
    private String currentCondition;
    
    /**
     * A Good, used to distinguish between the types of goods
     */
    public enum Good {
        Water,
        Furs,
        Food,
        Ore,
        Games,
        Firearms,
        Medicine,
        Machines,
        Narcotics,
        Robots
    }

    /**
     * Initializes a Ware and sets its initial values based on a given Good
     * @param good, the ware's good
     */
    public Ware(Good good) {
        this.good = good;
        setUp();
    }
    
    /**
     * Sets the initial default values for a Ware
     */
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

    /**
     * Get the name of the ware
     * @return name, the name of the ware
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the good
     * @return good, the good of the ware
     */
    public Good getGood() {
        return good;
    }

    /**
     * Get the minimum tech level to produce
     * @return minimumTechLevelToProduce, the min tech level to produce
     */
    public int getMinimumTechLevelToProduce() {
        return minimumTechLevelToProduce;
    }

    /**
     * Get the minimum tech level to use
     * @return minimumTechLevelToUse, the min tech level to use
     */
    public int getMinimumTechLevelToUse() {
        return minimumTechLevelToUse;
    }

    /**
     * Get the tech level production
     * @return techLevelProduction, the tech level production
     */
    public int getTechLevelProduction() {
        return techLevelProduction;
    }

    /**
     * Get the base price
     * @return basePrice, the base price
     */
    public int getBasePrice()   {
        return basePrice;
    }

    /**
     * Get the price increase per tech level
     * @return priceIncreasePerLevel, the price increase per tech level
     */
    public int getPriceIncreasePerLevel() {
        return priceIncreasePerLevel;
    }

    /**
     * Get the price increase per tech level
     * @return variance, the price increase per tech level
     */
    public int getVariance() {
        return variance;
    }
    
    /**
     * Get the space trade minimum price
     * @return spaceTradeMinPrice, the space trade min price
     */
    public int getSpaceTradeMinPrice() {
        return spaceTradeMinPrice;
    }

    /**
     * Get the space trade maximum price
     * @return spaceTradeMaxPrice, the space trade max price
     */
    public int getSpaceTradeMaxPrice() {
        return spaceTradeMaxPrice;
    }
    
    /**
     * Get the current price of the ware
     * @return currentPrice, the current price
     */
    public int getCurrentPrice() {
        return currentPrice;
    }
    
    /**
     * Set the current price of the ware
     * @param currentPrice, the current price
     */
    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
    
    /**
     * Get the current quantity of the ware
     * @return currentQuantity, the current quantity
     */
    public int getCurrentQuantity() {
        return currentQuantity;
    }
    
    /**
     * Set the current quantity of the ware
     * @param currentQuantity, the current quantity
     */
    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
    
    /**
     * Get the current condition of the ware
     * @return currentCondition, the current condition
     */
    public String getCurrentCondition() {
        return currentCondition;
    }
    
    /**
     * Set the current condition of the ware
     * @param currentCondition, the current condition
     */
    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }
    
    /**
     * Get the list of the default wares
     * @return wares, the list of the default wares
     */
    public static List<Ware> defaultWares() {
        ArrayList<Ware> wares = new ArrayList<>(Good.values().length);
        for (Good good: Good.values()) {
            Ware ware = new Ware(good);
            wares.add(ware);
        }
        return wares;
    }
    
    @Override
    public String toString() {
        return "<Ware: " + name + ", Cost: " + currentPrice + ", Avl: " +
                currentQuantity + ">";
    }
}
