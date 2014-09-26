package hyenas;

import java.util.Random;

public class Planet {
    private static final int MAX_TECH_LEVEL = 7;
    private static final int MAX_RESOURCE_TYPE = 12;
    private static final int NUM_ITEMS = 10;

    private double x;
    private double y;
    private double size;
    private int[] items = new int[NUM_ITEMS];
    private int techLevel;
    private int resourceType;
    private String planetName;

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
        "Weird Mushrooms",
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

        // start out with 0 stock for all items. Maybe we should change this
        for (int i = 0; i < NUM_ITEMS; i++) {
            items[i] = 0;
        }
    }

    /**
     * Adds new stock to wares after each round
     */
    public void produceWares() {
        // TODO: formula for how much to produce per turn
        changeWares(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
    }

    /**
     * Changes the amount of each item by the amount passed in. This is for
     * buying and selling.
     * @param differences
     */
    public void changeWares(int[] differences) {
        for (int i = 0; i < items.length; i++) {
            items[i] += differences[i];
        }
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

    /**
     * Get the resources of the planet.
     * @return quantity of the resources of the planet
     */
    public int[] getItems() {
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
