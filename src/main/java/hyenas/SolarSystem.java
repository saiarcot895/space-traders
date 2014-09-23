package hyenas;

import java.util.Random;

public class SolarSystem {
    private String systemName;
    private Planet planet;
    private double x;
    private double y;
    private int techLevel;
    private int resourceType;

    private static final int MAX_TECH_LEVEL = 7;
    private static final int MAX_RESOURCE_TYPE = 12;

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

    private static final String[] RESOURCE_TYPES = new String[] {
        "No Specialized Resources",
        "Mineral Rich",
        "Mineral Poor",
        "Desert",
        "Lots of Water",
        "Rich Soil",
        "Poor Soil",
        "Rich Fauna",
        "Lifeless",
        "Wierd Mushrooms",
        "Lots of Herbs",
        "Artistic",
        "Warlike",
    };

    public SolarSystem(String systemName) {
        this.systemName = systemName;
        this.planet = new Planet();

        Random rand = new Random();
        this.techLevel = rand.nextInt(MAX_TECH_LEVEL + 1);
        this.resourceType = rand.nextInt(MAX_RESOURCE_TYPE + 1);
        this.x = rand.nextDouble();
        this.y = rand.nextDouble();
    }

    /**
     * Get the name of the solar system.
     * @return name of solar system
     */
    public String getSystemName() {
        return this.systemName;
    }

    /**
     * Get the planet in the solar system. This may be later changed to an array
     * so that multiple planets may be returned.
     * @return planet in the solar system
     */
    public Planet getPlanet() {
        return this.planet;
    }

    /**
     * Get the tech level of the solar system.
     * @return tech level of the solar system
     */
    public String techLevelString() {
        return TECH_LEVELS[this.techLevel];
    }

    /**
     * Get the resource type the solar system has.
     * @return resource type of the solar system
     */
    public String resourceTypeString() {
        return RESOURCE_TYPES[this.resourceType];
    }

    @Override
    public String toString() {
        return "<System: " + this.systemName
            + ", Tech: " + this.techLevelString()
            + ", Resource: " + this.resourceTypeString()
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }
}
