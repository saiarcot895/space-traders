package hyenas;

import java.util.Random;

public class Planet {
    private static final int NUM_ITEMS = 10;
    private static final int[] TTP = {2, 0, 1, 3, 6, 5, 6, 5, 5, 7};
    private static final int[] MTLP = {0, 0, 1, 2, 3, 3, 4, 4, 5, 6};

    private int x;
    private int y;
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

    public Planet(String planetName) {
        this.planetName = planetName;
        Random rand = new Random();
        x = rand.nextInt(600);
        y = rand.nextInt(600);
        size = rand.nextDouble();
        techLevel = rand.nextInt(TECH_LEVELS.length);
        resourceType = rand.nextInt(RESOURCE_TYPES.length);
        produceWares();
    }

    /**
     * Adds new stock to wares after each round
     */
    public void produceWares() {
        changeWares(howMuchToProduce());
    }


    /**
     * Calculates how much of each product to produce every turn based on
     * minimum tech level to produce item and tech level that produces the
     * highest amount of that item.
     * @return an array containing how much of each item to produce
     */
    private int[] howMuchToProduce() {
        int[] howMuchToProduce = new int[NUM_ITEMS];
        for (int i = 0; i < NUM_ITEMS; i++) {
            if (techLevel < MTLP[i]) {
                howMuchToProduce[i] = 0;
            } else {
                howMuchToProduce[i] = 1 + Math.abs(TTP[i] - techLevel);
            }
        }
        return howMuchToProduce;
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
            + ", Resources: ["+items[0]+", "+items[1]+", "+items[2]+", "
                +items[3]+", "+items[4]+", "+items[5]+", "+items[6]+", "
                +items[7]+", "+items[8]+", "+items[9]+"]"
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }

}
