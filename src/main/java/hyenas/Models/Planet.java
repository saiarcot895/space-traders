package hyenas.Models;

import hyenas.UI.UIHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code Planet} class represents a planet in a solar system.
 * @author saikrishna
 */
public class Planet {
    private int orbitRadius;
    private boolean clockwiseOrbit;
    private double size;
    private String planetName;
    private String color;
    private PlanetTechLevel techLevel;
    private PlanetType type;
    private PlanetEvent event;
    private List<Ware> wares;
    
    /**
     * Standard constructor for initializing planet.
     * @param planetName, the name of the planet
     */
    public Planet(String planetName) {
        this.planetName = planetName;
        Random rand = new Random();
        clockwiseOrbit = rand.nextBoolean();
        size = 10 + rand.nextInt(10);
        
        int randPlanetTech = rand.nextInt(PlanetTechLevel.values().length);
        techLevel = PlanetTechLevel.values()[randPlanetTech];
        color = UIHelper.randomColorString();
        int randPlanetType = rand.nextInt(PlanetType.values().length);
        type = PlanetType.values()[randPlanetType];
        event = PlanetEvent.None;
    }
    
    /**
     * Constructor for Planet when loading from database. Overrides values set
     * at random in original constructor with values stored in table.
     * 
     * @param planetName, the name of the planet
     * @param clockwiseOrbit, whether the planet's orbit is clockwise
     * @param techLevel, the tech level of the planet
     * @param planetType, the type of the planet
     */
    public Planet(String planetName, boolean clockwiseOrbit, int techLevel,
            int planetType) {
        this(planetName);
        this.clockwiseOrbit = clockwiseOrbit;
        this.techLevel = PlanetTechLevel.values()[techLevel];
        this.type = PlanetType.values()[planetType];
    }
    
    /**
     * Get the planet's name
     * @return planetName, the name of the planet
     */
    public String getPlanetName()   {
        return planetName;
    }
    
    /**
     * Get the planet's size
     * @return size, the size of the planet
     */
    public double getSize() {
        return size;
    }
    
    /**
     * Set the planet's size
     * @param size, the size of the planet
     */
    public void setSize(double size) {
        this.size = size;
    }
    
    /**
     * Get whether the planet's orbit is clockwise
     * @return clockwiseOrbit, whether the planet's orbit is clockwise
     */
    public boolean isClockwiseOrbit()    {
        return clockwiseOrbit;
    }
    
    /**
     * Get the name of the planet's tech level
     * @return string, the name of the tech level of the planet
     */
    public String techLevelString() {
        switch (techLevel) {
            case PreAgriculture: return "Pre-Agriculture";
            case Agriculture: return "Agriculture";
            case Medieval: return "Medieval";
            case Renaissance: return "Renaissance";
            case EarlyIndustrial: return "Early Industrial";
            case Industrial: return "Industrial";
            case PostIndustrial: return "Post-Industrial";
            case HiTech: return "Hi-Tech";
            default: return "Error";
        }
    }

    /**
     * Get the tech level of the planet.
     * @return techLevel, the tech level of the planet
     */
    public PlanetTechLevel getTechLevel() {
        return techLevel;
    }

    @Override
    public String toString() {
        return "<Planet: " + planetName + ", Radius: " + orbitRadius +
                ", Tech: " + techLevelString() + ">";
    }

    /**
     * Get the color of the planet. The color is returned in the form
     * "rgb(r, g, b, 1)", where "r" is the red channel (0-255), "g" is the green
     * channel (0-255), and "b" is the blue channel (0-255). The final 1 is the
     * transparency (opaque).
     * @return color of the solar system
     */
    public String getColorString()  {
        return color;
    }

    /**
     * Get the planet's orbit radius
     * @return orbitRadius, the orbit radius of the planet
     */
    public int getOrbitRadius() {
        return orbitRadius;
    }
    
    /**
     * Set the planet's orbit radius
     * @param orbitRadius, the orbitRadius of the planet
     */
    public void setOrbitRadius(int orbitRadius)    {
        this.orbitRadius = orbitRadius;
    }
    
    /**
     * Get the planet's type
     * @return type, the planet's type
     */
    public PlanetType getPlanetType() {
        return type;
    }
    
    /**
     * Get a string representation of the planet's type
     * @return string, the string representation of the planet's type
     */
    public String getPlanetTypeString() {
        switch (type) {
            case None: return "Normal";
            case MineralRich: return "Mineral Rich";
            case MineralPoor: return "Mineral Poor";
            case LotsOfWater: return "Lots of Water";
            case Desert: return "Desert";
            case RichSoil: return "Rich Soil";
            case PoorSoil: return "Poor Soil";
            case RichFauna: return "Rich Fauna";
            case Lifeless: return "Lifeless";
            case WeirdMushrooms: return "Weird Mushrooms";
            case LotsOfHerbs: return "Lots of Herbs";
            case Artistic: return "Artistic";
            case Warlike: return "Warlike";
            default: return "Normal";
        }
    }
    
    /**
     * Get a string representation of the planet's event
     * @return string, the string representation of the planet's event
     */
    public String getPlanetEventString() {
        switch (event) {
            case None: return "None";
            case Drought: return "Drought";
            case Cold: return "Cold";
            case Cropfail: return "Cropfail";
            case War: return "War";
            case Boredom: return "Boredom";
            case Plague: return "Plague";
            case LackOfWorkers: return "Lack of Workers";
            default: return "None";
        }
    }
    
    /**
     * Get the planet's wares
     * @return wares, the planet's wares
     */
    public List<Ware> getWares() {
        if (wares == null) {
            produceWares();
        }
        return wares;
    }
    
    /**
     * Add a ware to the planet's wares
     * @param newWare, the ware to add
     */
    public void addWare(Ware newWare) {
        Good good = newWare.getGood();
        List<Ware> wares = getWares();
        for (Ware ware: wares) {
            if (ware.getGood() == good) {
                int quantity = ware.getCurrentQuantity();
                ware.setCurrentQuantity(++quantity);
            }
        }
    }
    
    /**
     * Produces the appropriate amount of wares for a planet based on the
     * properties of the planet (planetEvent, planetType). Also sets matching
     * price values for each ware. For use with marketplace.
     */
    public void produceWares() {
        int numGoods = Good.values().length;
        List<Ware> wares = new ArrayList<>(numGoods);
        AffectedGood affectedGoodForPlanetType = affectedGoodForPlanetType();
        List<AffectedGood> affectedGoodsForPlanetEvent = affectedGoodsForPlanetEvent();
        
        for (Good good: Good.values()) {
            Ware ware = new Ware(good);
            ware.setCurrentCondition("");
            int quantity = howMuchToProduce(good);
            int basePrice = ware.getBasePrice();
            int variance = 0;
            
            if (affectedGoodForPlanetType != null &&
                    good == affectedGoodForPlanetType.getGood()) {
                // Add or deduct 10% of good's base price based on planet's type
                // Also triple or reduce by 60% the available quantity
                boolean priceIncreased = affectedGoodForPlanetType.isIncreasedPrice();
                int affectedAmount = (int) (basePrice * .1);
                
                if (priceIncreased) {
                    quantity = (int) (quantity * .4);
                    variance = variance + affectedAmount;
                    ware.setCurrentCondition("Scarce");
                } else {
                    quantity = quantity * 3;
                    variance = variance - affectedAmount;
                    if (quantity > 0) {
                        ware.setCurrentCondition("Abundant");
                    }
                }
            }
            
            for (AffectedGood affectedGood: affectedGoodsForPlanetEvent) {
                // Double the base price for goods being affected by the event
                // affecting the planet. Also, reduce available quantity by 70%
                if (good == affectedGood.getGood()) {
                    basePrice = 2 * basePrice;
                    quantity = (int) (quantity * .3);
                    ware.setCurrentCondition("Scarce");
                }
            }
            
            int price = basePrice + (ware.getPriceIncreasePerLevel() * (techLevel.ordinal() - ware.getMinimumTechLevelToProduce())) + variance;
            ware.setCurrentPrice(price);
            ware.setCurrentQuantity(quantity);
            wares.add(ware);
        }
        
        this.wares = wares;
    }

    /**
     * Returns the appropriate amount of a given good to produce based on the
     * planet's tech level and properties of the good.
     * 
     * @param good, the good
     * @return int, the number to produce
     */
    private int howMuchToProduce(Good good) {
        Ware item = new Ware(good);
        if (techLevel.ordinal() < item.getMinimumTechLevelToProduce()) {
            return 0;
        } else {
            return 10 - Math.abs(item.getTechLevelProduction() - techLevel.ordinal());
        }
    }
    
    /**
     * Represents a good affected by either the Planet's Type, or a PlanetEvent
     */
    private class AffectedGood {
        private Good good;
        private boolean increasedPrice;
        
        /**
         * Initializes AffectedGood instance
         * @param good, the Good type
         * @param increasedPrice, whether the affected good's price increases
         */
        public AffectedGood(Good good, boolean increasedPrice) {
            this.good = good;
            this.increasedPrice = increasedPrice;
        }
        
        /**
         * Getter for the good
         * @return good, the Good type
         */
        public Good getGood() {
            return good;
        }
        
        /**
         * Getter for whether the price is increased
         * @return increasedPrice, whether the price is increased
         */
        public boolean isIncreasedPrice() {
            return increasedPrice;
        }
    }
    
    /**
     * Returns AffectedGood of which good is affected due to the planet's type
     * as well as how the price of the good is affected
     * 
     * @return AffectedGood with the good affected, and a boolean of whether
     * the price is increased or not
     */
    public AffectedGood affectedGoodForPlanetType() {
        switch (type) {
            case None: return null;
            case MineralRich: return new AffectedGood(Good.Ore, false);
            case MineralPoor: return new AffectedGood(Good.Ore, true);
            case LotsOfWater: return new AffectedGood(Good.Water, false);
            case Desert: return new AffectedGood(Good.Water, true);
            case RichSoil: return new AffectedGood(Good.Food, false);
            case PoorSoil: return new AffectedGood(Good.Food, true);
            case RichFauna: return new AffectedGood(Good.Furs, false);
            case Lifeless: return new AffectedGood(Good.Furs, true);
            case WeirdMushrooms: return new AffectedGood(Good.Narcotics, false);
            case LotsOfHerbs: return new AffectedGood(Good.Medicine, false);
            case Artistic: return new AffectedGood(Good.Games, false);
            case Warlike: return new AffectedGood(Good.Firearms, false);
            default: return null;
        }
    }
    
    /**
     * Returns a list of AffectedGoods that are affected by the Planet's
     * PlanetEvent. Each affected good contains a boolean of whether the good's
     * price should be increased or not.
     * 
     * @return affectedGoods, the goods affected by the given planet event
     */
    public List<AffectedGood> affectedGoodsForPlanetEvent() {
        ArrayList<AffectedGood> affectedGoods = new ArrayList<AffectedGood>();
        switch (event) {
            case None: break;
            case Drought: 
                AffectedGood water = new AffectedGood(Good.Water, true);
                affectedGoods.add(water);
                break;
            case Cold: 
                AffectedGood furs = new AffectedGood(Good.Furs, true);
                affectedGoods.add(furs);
                break;
            case Cropfail:
                AffectedGood food = new AffectedGood(Good.Food, true);
                affectedGoods.add(food);
                break;
            case War:
                AffectedGood ore = new AffectedGood(Good.Ore, true);
                AffectedGood firearms = new AffectedGood(Good.Firearms, true);
                affectedGoods.add(ore);
                affectedGoods.add(firearms);
                break;
            case Boredom:
                AffectedGood games = new AffectedGood(Good.Games, true);
                AffectedGood narcotics = new AffectedGood(Good.Narcotics, true);
                affectedGoods.add(games);
                affectedGoods.add(narcotics);
                break;
            case Plague:
                AffectedGood medicine = new AffectedGood(Good.Medicine, true);
                affectedGoods.add(medicine);
                break;
            case LackOfWorkers:
                AffectedGood machines = new AffectedGood(Good.Machines, true);
                AffectedGood robots = new AffectedGood(Good.Robots, true);
                affectedGoods.add(machines);
                affectedGoods.add(robots);
                break;
            default: break;
        }
        return affectedGoods;
    }
    
    public boolean canSellFuel() {
        return techLevel.ordinal() >= PlanetTechLevel.Medieval.ordinal();
    }
    
    private final int BASE_FUEL_COST = 5;
    
    public int getFuelCost() {
        int startingFuelTechLevel = PlanetTechLevel.Medieval.ordinal();
        int currentTechLevel = techLevel.ordinal();
        int difference = currentTechLevel - startingFuelTechLevel;
        int fuelCost = BASE_FUEL_COST - difference;
        if (fuelCost < 1) {
            return 1;
        } else {
            return fuelCost;
        }
    }
}
