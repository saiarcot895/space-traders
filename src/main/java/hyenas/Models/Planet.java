package hyenas.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code Planet} class represents a planet in a solar system.
 * @author saikrishna
 */
public class Planet {
    private static final int NUM_ITEMS = 10;

    private int x;
    private int y;
    private int orbitRadius;
    private boolean clockwiseOrbit;
    private double size;
    private int techLevel;
    private String planetName;
    private String color;
    private PlanetType planetType;
    private PlanetEvent planetEvent;
    private List<Ware> wares;

    private static final String[] TECH_LEVELS = new String[] {
        "Pre-Agriculture",
        "Agriculture",
        "Medieval",
        "Renaissance",
        "Early Industrial",
        "Industrial",
        "Post-Industrial",
        "Hi-Tech",
    };
    
    public Planet(String planetName) {
        this.planetName = planetName;
        Random rand = new Random();
        clockwiseOrbit = rand.nextBoolean();
        size = 10 + rand.nextInt(10);
        
        techLevel = rand.nextInt(TECH_LEVELS.length);
        color = randomColorString();
        int randPlanetType = rand.nextInt(PlanetType.values().length);
        planetType = PlanetType.values()[randPlanetType];
        planetEvent = PlanetEvent.None;
    }
    
    public Planet(String planetName, boolean clockwiseOrbit, int techLevel,
            int planetType) {
        this(planetName);
        this.clockwiseOrbit = clockwiseOrbit;
        this.techLevel = techLevel;
        this.planetType = PlanetType.values()[planetType];
    }
    
    public String getPlanetName()   {
        return planetName;
    }
    
    public boolean isClockwiseOrbit()    {
        return clockwiseOrbit;
    }
    
    /**
     * Get the string tech level of the planet.
     * @return string, tech level of the planet
     */
    public String techLevelString() {
        return TECH_LEVELS[techLevel];
    }

    /**
     * Get the tech level of the planet.
     * @return int, the tech level of the planet
     */
    public int getTechLevel() {
        return techLevel;
    }

    @Override
    public String toString() {
        return "<Planet: " + planetName + ", Radius: " + orbitRadius +
                ", Tech: " + techLevelString() + ", Loc: (" + x + ", " + y +
                ")" + ">";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    private String randomColorString() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return String.format("rgb(%d, %d, %d, 1)", r, g, b);
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
     * Get the radius of the planet's orbit.
     * @return radius of the orbit
     */
    public int getOrbitRadius() {
        return orbitRadius;
    }
    
    public void setOrbitRadius(int radius)    {
        orbitRadius = radius;
    }
    
    public PlanetType getPlanetType() {
        return planetType;
    }
    
    public String getPlanetTypeString() {
        switch (planetType) {
            case None: return "No Specialized Resources";
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
            default: return "No Specialized Resources";
        }
    }
    
    public String getPlanetEventString() {
        switch (planetEvent) {
            case None: return "None";
            case Drought: return "Drought";
            case Cold: return "Cold";
            case Cropfail: return "Cropfail";
            case War: return "War";
            case Boredom: return "Boredom";
            case Plague: return "Plague";
            case LackOfWorkers: return "LackOfWorkers";
            default: return "None";
        }
    }
    
    public List<Ware> getWares() {
        if (wares == null) {
            produceWares();
        }
        return wares;
    }
    
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
    
    public void produceWares() {
        int numGoods = Good.values().length;
        List<Ware> wares = new ArrayList<Ware>(numGoods);
        AffectedGood affectedGoodForPlanetType = affectedGoodForPlanetType();
        List<AffectedGood> affectedGoodsForPlanetEvent = affectedGoodsForPlanetEvent();
        
        for (Good good: Good.values()) {
            Ware ware = new Ware(good);
            ware.setCurrentCondition("");
            int quantity = howMuchToProduce(good);
            int basePrice = ware.getBasePrice();
            int variance = 0;
            
            if (good == affectedGoodForPlanetType.getGood()) {
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
                // Double the base price for goods being affected by the event affecting the planet
                // Also, reduce available quantity by 70%
                if (good == affectedGood.getGood()) {
                    basePrice = 2 * basePrice;
                    quantity = (int) (quantity * .3);
                    ware.setCurrentCondition("Scarce");
                }
            }
            
            int price = basePrice + (ware.getPriceIncreasePerLevel() * (techLevel - ware.getMinimumTechLevelToProduce())) + variance;
            ware.setCurrentPrice(price);
            ware.setCurrentQuantity(quantity);
            wares.add(ware);
        }
        
        this.wares = wares;
    }

    private int howMuchToProduce(Good good) {
        Ware item = new Ware(good);
        if (techLevel < item.getMinimumTechLevelToProduce()) {
            return 0;
        } else {
            // TODO include affects caused by events and planet type
            return 10 - Math.abs(item.getTechLevelProduction() - techLevel);
        }
    }
    
    private class AffectedGood {
        private Good good;
        private boolean increasedPrice;
        
        public AffectedGood(Good good, boolean increasedPrice) {
            this.good = good;
            this.increasedPrice = increasedPrice;
        }
        
        public Good getGood() {
            return good;
        }
        
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
        switch (planetType) {
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
    
    public List<AffectedGood> affectedGoodsForPlanetEvent() {
        ArrayList<AffectedGood> affectedGoods = new ArrayList<AffectedGood>();
        switch (planetEvent) {
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
}
