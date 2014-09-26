package hyenas;

import java.util.Random;

public class Planet {
    private double x;
    private double y;
    private double size;
    private int[] items = new int[10];
    private int techLevel;
    private int resourceType;
    private String[] planetName;

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

    public Planet() {
        Random rand = new Random();
        this.x = rand.nextDouble();
        this.y = rand.nextDouble();
        this.size = rand.nextDouble();
        this.techLevel = rand.nextInt(MAX_TECH_LEVEL + 1);
        this.resourceType = rand.nextInt(MAX_RESOURCE_TYPE + 1);
        // initialize item amounts;
    }

    public void produceWares() {
        // 
    }


    /**
     * Get the tech level of the planet.
     * @return tech level of the planet
     */
    public String techLevelString() {
        return TECH_LEVELS[this.techLevel];
    }

    /**
     * Get the resource type the planet has.
     * @return resource type of the planet
     */
    public String resourceTypeString() {
        return RESOURCE_TYPES[this.resourceType];
    }

    @Override
    public String toString() {
        return "<Planet: " + this.planetName
            + ", Tech: " + this.techLevelString()
            + ", Resource: " + this.resourceTypeString()
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }

}
