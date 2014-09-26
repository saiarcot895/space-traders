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
        x = rand.nextDouble();
        y = rand.nextDouble();
        size = rand.nextDouble();
        techLevel = rand.nextInt(MAX_TECH_LEVEL + 1);
        resourceType = rand.nextInt(MAX_RESOURCE_TYPE + 1);
        // initialize item amounts;
    }

    public void produceWares() {
        //TODO
    	//will be called every turn or so to update product counts
    }


    /**
     * Get the tech level of the planet.
     * @return tech level of the planet
     */
    public String techLevelString() {
        return TECH_LEVELS[techLevel];
    }

    /**
     * Get the resources of the planet.
     * @return quantity of the resources of the planet
     */
    public int[] resourceTypeString() {
        return items;
    }

    @Override
    public String toString() {
        return "<Planet: " + planetName
            + ", Tech: " + techLevelString()
            + ", Resources: ["+items[0]+", "+items[1]+", "+items[2]+", "+items[3]+", "+items[4]+", "+items[5]+", "+items[6]+", "+items[7]+", "+items[8]+", "+items[9]+"]"
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }

}
