package hyenas.Models;

import java.util.HashMap;
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
    private int planetType;

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
    
    //planet type name, good, up/down

    private static final String[] PLANET_TYPES = new String[] {
        // DO NOT CHANGE, unless you also update goodAffectedForPlanetType
        "No Specialized Resources",     // How resource is affected: 
        "Mineral Rich",                 // Ore+
        "Mineral Poor",                 // Ore-
        "Lots of Water",                // Water+
        "Desert",                       // Water-
        "Rich Soil",                    // Food+
        "Poor Soil",                    // Food-
        "Rich Fauna",                   // Furs+
        "Lifeless",                     // Furs-
        "Weird Mushrooms",              // Narcotics+
        "Lots of Herbs",                // Medicine+
        "Artistic",                     // Games+
        "Warlike",                      // Firearms+
    };
    
    // Events temporarily affect a planet
    private static final String[] EVENT_TYPES = new String[] {
        "Drought",      // Water
        "Cold",         // Furs
        "Cropfail",     // Food
        "War",          // Ore, Firearms
        "Boredom",      // Games, Narcotics
        "Plague",       // Medicine
        "LackOfWorkers" // Machines, Robots
    };

    public Planet(String planetName) {
        this.planetName = planetName;
        Random rand = new Random();
        clockwiseOrbit = rand.nextBoolean();
        size = 10 + rand.nextInt(10);
        
        techLevel = rand.nextInt(TECH_LEVELS.length);
        color = randomColorString();
        planetType = rand.nextInt(PLANET_TYPES.length);
    }
    
    public Planet(String planetName, int techLevel, int resourceType) {
        this(planetName);
        this.techLevel = techLevel;
    }
    
    public String getPlanetName()   {
        return planetName;
    }
    
    public boolean getClockWiseOrbit()    {
        return clockwiseOrbit;
    }
    
    /**
     * Get the tech level of the planet.
     * @return tech level of the planet
     */
    public String techLevelString() {
        return TECH_LEVELS[techLevel];
    }

    public int getTechLevel() {
        return techLevel;
    }

    @Override
    public String toString() {
        return "<Planet: " + planetName
                + ", Radius: " + orbitRadius
                + ", Tech: " + techLevelString()
                + ", Loc: (" + x + ", " + y + ")" + ">";
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
    
    public String getPlanetTypeString() {
        return PLANET_TYPES[planetType];
    }
    
    /* Returns hashmap of which good is affected (and how it is affected) for a
     * given planet type.
     *
     * @param planettype, the planet type. Example: Mineral rich, mineral poor
     * @return HashMap of Good and Integer. Good is the good affected, and the
     * integer is whether the price goes up or down. Returns null if no Good is
     * affected by the given planet
     */
    public HashMap<Good, Integer> goodAffectedForPlanetType(int planetType) {
        switch (planetType) {
            case 0: return null; // No good is affected
            case 1: 
                return new HashMap<Good, Integer>(Good.Ore.ordinal(), 0); // Mineral Rich
            case 2: 
                return new HashMap<Good, Integer>(Good.Ore.ordinal(), 1); // Mineral Poor
            case 3:
                return new HashMap<Good, Integer>(Good.Water.ordinal(), 0); // Lots of water
            case 4:
                return new HashMap<Good, Integer>(Good.Water.ordinal(), 1); // Desert
            case 5:
                return new HashMap<Good, Integer>(Good.Food.ordinal(), 0); // Rich soil
            case 6:
                return new HashMap<Good, Integer>(Good.Food.ordinal(), 1); // Poor soil
            case 7:
                return new HashMap<Good, Integer>(Good.Furs.ordinal(), 0); // Rich Fauna
            case 8:
                return new HashMap<Good, Integer>(Good.Furs.ordinal(), 1); // Lifeless
            case 9:
                return new HashMap<Good, Integer>(Good.Narcotics.ordinal(), 0); // Weird Mushrooms
            case 10:
                return new HashMap<Good, Integer>(Good.Medicine.ordinal(), 0); // Lots of Herbs
            case 11:
                return new HashMap<Good, Integer>(Good.Games.ordinal(), 0); // Artistic
            case 12:
                return new HashMap<Good, Integer>(Good.Firearms.ordinal(), 0); // Warlike
            default: break;
        }
        return null;
    }
}
