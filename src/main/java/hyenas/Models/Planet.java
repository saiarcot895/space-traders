package hyenas.Models;

import hyenas.Models.Ware.Good;
import hyenas.UI.UIHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Planet class represents a planet in a solar system.
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
     * A PlanetTechType, used to distinguish between the types of planets.
     */
    public enum PlanetType {
        NONE,
        MINERALRICH,
        MINERALPOOR,
        LOTSOFWATER,
        DESERT,
        RICHSOIL,
        POORSOIL,
        RICHFAUNA,
        LIFELESS,
        WIERDMUSHROOMS,
        LOTSOFHERBS,
        ARTISTIC,
        WARLIKE,
    }
    
    /**
     * A PlanetTechLevel, used to distinguish between Planet tech levels.
     */
    public enum PlanetTechLevel {
        PREAGRICULTURE,
        AGRICULTURE,
        MEDIEVAL,
        RENAISSANCE,
        EARLYINDUSTRIAL,
        INDUSTRIAL,
        POSTINDUSTRIAL,
        HITECH,
    }
    
    /**
     * A PlanetEvent, used to distinguish between events that can affect a
     * Planet.
     */
    public enum PlanetEvent {
        NONE,
        DROUGHT,
        COLD,
        CROPFAIL,
        WAR,
        BOREDOM,
        PLAGUE,
        LACKOFWORKERS,
    }
    
    /**
     * Standard constructor for initializing planet.
     * @param planetName the name of the planet
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
        event = PlanetEvent.NONE;
    }
    
    /**
     * Constructor for Planet when loading from database. Overrides values set
     * at random in original constructor with values stored in table.
     * 
     * @param name the name of the planet
     * @param clockwiseOrbit whether the planet's orbit is clockwise
     * @param techLevel the tech level of the planet
     * @param planetType the type of the planet
     */
    public Planet(String name, boolean clockwiseOrbit, int techLevel,
            int planetType) {
        this(name);
        this.clockwiseOrbit = clockwiseOrbit;
        this.techLevel = PlanetTechLevel.values()[techLevel];
        this.type = PlanetType.values()[planetType];
    }
    
    /**
     * Get the planet's name.
     * @return planetName the name of the planet
     */
    public String getPlanetName()   {
        return planetName;
    }
    
    /**
     * Get the planet's size.
     * @return size the size of the planet
     */
    public double getSize() {
        return size;
    }
    
    /**
     * Set the planet's size.
     * @param size the size of the planet
     */
    public void setSize(double size) {
        this.size = size;
    }
    
    /**
     * Get whether the planet's orbit is clockwise.
     * @return clockwiseOrbit, whether the planet's orbit is clockwise
     */
    public boolean isClockwiseOrbit()    {
        return clockwiseOrbit;
    }
    
    /**
     * Get the name of the planet's tech level.
     * @return string, the name of the tech level of the planet
     */
    public String techLevelString() {
        switch (techLevel) {
            case PREAGRICULTURE:
                return "Pre-Agriculture";
            case AGRICULTURE:
                return "Agriculture";
            case MEDIEVAL:
                return "Medieval";
            case RENAISSANCE:
                return "Renaissance";
            case EARLYINDUSTRIAL:
                return "Early Industrial";
            case INDUSTRIAL:
                return "Industrial";
            case POSTINDUSTRIAL:
                return "Post-Industrial";
            case HITECH:
                return "Hi-Tech";
            default:
                return "Error";
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
        return "<Planet: " + planetName + ", Radius: " + orbitRadius
                + ", Tech: " + techLevelString() + ">";
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
     * Get the planet's orbit radius.
     * @return orbitRadius the orbit radius of the planet
     */
    public int getOrbitRadius() {
        return orbitRadius;
    }
    
    /**
     * Set the planet's orbit radius.
     * @param orbitRadius the orbitRadius of the planet
     */
    public void setOrbitRadius(int orbitRadius)    {
        this.orbitRadius = orbitRadius;
    }
    
    /**
     * Get the planet's type.
     * @return type the planet's type
     */
    public PlanetType getPlanetType() {
        return type;
    }
    
    /**
     * Get a string representation of the planet's type.
     * @return string the string representation of the planet's type
     */
    public String getPlanetTypeString() {
        switch (type) {
            case NONE:
                return "Normal";
            case MINERALRICH:
                return "Mineral Rich";
            case MINERALPOOR:
                return "Mineral Poor";
            case LOTSOFWATER:
                return "Lots of Water";
            case DESERT:
                return "Desert";
            case RICHSOIL:
                return "Rich Soil";
            case POORSOIL:
                return "Poor Soil";
            case RICHFAUNA:
                return "Rich Fauna";
            case LIFELESS:
                return "Lifeless";
            case WIERDMUSHROOMS:
                return "Weird Mushrooms";
            case LOTSOFHERBS:
                return "Lots of Herbs";
            case ARTISTIC:
                return "Artistic";
            case WARLIKE:
                return "Warlike";
            default:
                return "Unknown Type";
        }
    }
    
    /**
     * Get a string representation of the planet's event.
     * @return string, the string representation of the planet's event
     */
    public String getPlanetEventString() {
        switch (event) {
            case NONE:
                return "None";
            case DROUGHT:
                return "Drought";
            case COLD:
                return "Cold";
            case CROPFAIL:
                return "Cropfail";
            case WAR:
                return "War";
            case BOREDOM:
                return "Boredom";
            case PLAGUE:
                return "Plague";
            case LACKOFWORKERS:
                return "Lack of Workers";
            default:
                return "Unknown Event";
        }
    }
    
    /**
     * Get the planet's wares.
     * @return wares the planet's wares
     */
    public List<Ware> getWares() {
        if (wares == null) {
            produceWares();
        }
        return wares;
    }
    
    /**
     * Add a ware to the planet's wares.
     * @param newWare the ware to add
     */
    public void addWare(Ware newWare) {
        Good good = newWare.getGood();
        List<Ware> planetWares = getWares();
        for (Ware ware: planetWares) {
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
        List<Ware> producedWares = new ArrayList<>(numGoods);
        AffectedGood affectedGoodForPlanetType = affectedGoodForPlanetType();
        List<AffectedGood> affectedGoodsForPlanetEvent = affectedGoodsForPlanetEvent();
        
        for (Good good: Good.values()) {
            Ware ware = new Ware(good);
            ware.setCurrentCondition("");
            int quantity = howMuchToProduce(good);
            int basePrice = ware.getBasePrice();
            int variance = 0;
            
            if (affectedGoodForPlanetType != null
                    && good == affectedGoodForPlanetType.getGood()) {
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
            producedWares.add(ware);
        }
        
        this.wares = producedWares;
    }

    /**
     * Returns the appropriate amount of a given good to produce based on the
     * planet's tech level and properties of the good.
     * 
     * @param good the good
     * @return the number to produce
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
     * Represents a good affected by either the Planet's Type, or a PlanetEvent.
     */
    private class AffectedGood {
        private Good good;
        private boolean increasedPrice;
        
        /**
         * Initializes AffectedGood instance.
         * @param good the Good type
         * @param increasedPrice whether the affected good's price increases
         */
        public AffectedGood(Good good, boolean increasedPrice) {
            this.good = good;
            this.increasedPrice = increasedPrice;
        }
        
        /**
         * Getter for the good.
         * @return the Good type
         */
        public Good getGood() {
            return good;
        }
        
        /**
         * Getter for whether the price is increased.
         * @return whether the price is increased
         */
        public boolean isIncreasedPrice() {
            return increasedPrice;
        }
    }
    
    /**
     * Returns AffectedGood of which good is affected due to the planet's type
     * as well as how the price of the good is affected.
     * 
     * @return the good affected, and whether the price is increased or not
     */
    public AffectedGood affectedGoodForPlanetType() {
        switch (type) {
            case NONE:
                return null;
            case MINERALRICH:
                return new AffectedGood(Good.ORE, false);
            case MINERALPOOR:
                return new AffectedGood(Good.ORE, true);
            case LOTSOFWATER:
                return new AffectedGood(Good.WATER, false);
            case DESERT:
                return new AffectedGood(Good.WATER, true);
            case RICHSOIL:
                return new AffectedGood(Good.FOOD, false);
            case POORSOIL:
                return new AffectedGood(Good.FOOD, true);
            case RICHFAUNA:
                return new AffectedGood(Good.FURS, false);
            case LIFELESS:
                return new AffectedGood(Good.FURS, true);
            case WIERDMUSHROOMS:
                return new AffectedGood(Good.NARCOTICS, false);
            case LOTSOFHERBS: 
                return new AffectedGood(Good.MEDICINE, false);
            case ARTISTIC:
                return new AffectedGood(Good.GAMES, false);
            case WARLIKE:
                return new AffectedGood(Good.FIREARMS, false);
            default:
                return null;
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
        ArrayList<AffectedGood> affectedGoods = new ArrayList<>();
        switch (event) {
            case NONE:
                break;
            case DROUGHT: 
                AffectedGood water = new AffectedGood(Good.WATER, true);
                affectedGoods.add(water);
                break;
            case COLD: 
                AffectedGood furs = new AffectedGood(Good.FURS, true);
                affectedGoods.add(furs);
                break;
            case CROPFAIL:
                AffectedGood food = new AffectedGood(Good.FOOD, true);
                affectedGoods.add(food);
                break;
            case WAR:
                AffectedGood ore = new AffectedGood(Good.ORE, true);
                AffectedGood firearms = new AffectedGood(Good.FIREARMS, true);
                affectedGoods.add(ore);
                affectedGoods.add(firearms);
                break;
            case BOREDOM:
                AffectedGood games = new AffectedGood(Good.GAMES, true);
                AffectedGood narcotics = new AffectedGood(Good.NARCOTICS, true);
                affectedGoods.add(games);
                affectedGoods.add(narcotics);
                break;
            case PLAGUE:
                AffectedGood medicine = new AffectedGood(Good.MEDICINE, true);
                affectedGoods.add(medicine);
                break;
            case LACKOFWORKERS:
                AffectedGood machines = new AffectedGood(Good.MACHINES, true);
                AffectedGood robots = new AffectedGood(Good.ROBOTS, true);
                affectedGoods.add(machines);
                affectedGoods.add(robots);
                break;
            default:
                break;
        }
        return affectedGoods;
    }
    
    /**
     * Gets whether a planet has a high enough tech level or a shipyard.
     * @return true if the planet has a shipyard; false otherwise
     */
    public boolean hasShipyard() {
        return techLevel.ordinal() >= PlanetTechLevel.MEDIEVAL.ordinal();
    }
    
    private final int BASE_FUEL_COST = 5;
    
    /**
     * Gets the cost of fuel on the planet.
     * @return fuelCost the cost of fuel
     */
    public int getFuelCost() {
        int startingFuelTechLevel = PlanetTechLevel.MEDIEVAL.ordinal();
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
