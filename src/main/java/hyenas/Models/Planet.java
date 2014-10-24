package hyenas.Models;

import java.util.Random;

/**
 * The {@code Planet} class represents a planet in a solar system.
 * @author saikrishna
 */
public class Planet {
    private static final int NUM_ITEMS = 10;
    private static final int[] TTP = {2, 0, 1, 3, 6, 5, 6, 5, 5, 7};
    private static final int[] MTLP = {0, 0, 1, 2, 3, 3, 4, 4, 5, 6};

    private int x;
    private int y;
    private int orbitRadius;
    private double size;
    private int[] items;
    private int techLevel;
    private int[] wareEvents = new int[NUM_ITEMS];
    private int resourceEvent;
    private int[] resourceEventEffects = new int[NUM_ITEMS];
    private String planetName;
    private String color;

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
    
    private static final String[] EVENT_TYPES = new String[] {
        "Drought",
        "Cold",
        "Cropfail",
        "War",
        "Boredom",
        "Plague",
        "LackOfWorkers"
    };

    public Planet(String planetName) {
        this.planetName = planetName;
        Random rand = new Random();
        size = 10 + rand.nextInt(10);
        orbitRadius = 110 + rand.nextInt(200);
        items = new int[NUM_ITEMS];
        techLevel = rand.nextInt(TECH_LEVELS.length);
        wareEvents[rand.nextInt(items.length)] = rand.nextInt(3) - 1;    //will be -1, 0, or 1
        color = randomColorString();
        int randResourceEvent = rand.nextInt(100);
        if(randResourceEvent < 40)  {
            resourceEvent = 0;
        }
        else if(randResourceEvent >= 40 && randResourceEvent < 45)  {
            resourceEvent = 1;
            resourceEventEffects[3] = 1;
        }
        else if(randResourceEvent >= 45 && randResourceEvent < 50)  {
            resourceEvent = 2;
            resourceEventEffects[3] = -1;
        }
        else if(randResourceEvent >= 50 && randResourceEvent < 55)  {
            resourceEvent = 3;
            resourceEventEffects[0] = 1;
        }
        else if(randResourceEvent >= 55 && randResourceEvent < 60)  {
            resourceEvent = 4;
            resourceEventEffects[0] = -1;
        }
        else if(randResourceEvent >= 60 && randResourceEvent < 65)  {
            resourceEvent = 5;
            resourceEventEffects[2] = -1;
        }
        else if(randResourceEvent >= 65 && randResourceEvent < 70)  {
            resourceEvent = 6;
            resourceEventEffects[2] = 1;
        }
        else if(randResourceEvent >= 70 && randResourceEvent < 75)  {
            resourceEvent = 7;
            resourceEventEffects[1] = -1;
        }
        else if(randResourceEvent >= 75 && randResourceEvent < 80)  {
            resourceEvent = 8;
            resourceEventEffects[1] = 1;
        }
        else if(randResourceEvent >= 80 && randResourceEvent < 85)  {
            resourceEvent = 9;
            resourceEventEffects[8] = -1;
        }
        else if(randResourceEvent >= 85 && randResourceEvent < 90)  {
            resourceEvent = 10;
            resourceEventEffects[6] = -1;
        }
        else if(randResourceEvent >= 90 && randResourceEvent < 95)  {
            resourceEvent = 11;
            resourceEventEffects[4] = -1;
        }
        else if(randResourceEvent >= 95 && randResourceEvent < 100)  {
            resourceEvent = 12;
            resourceEventEffects[5] = -1;
        }
        produceWares();
    }
    
    public Planet(String planetName, int techLevel, int resourceType) {
        this(planetName);
        items = new int[NUM_ITEMS];
        this.techLevel = techLevel;
        produceWares();
    }
    
    public String getPlanetName()   {
        return planetName;
    }

    /**
     * Adds new stock to wares after each round
     */
    public void produceWares() {
        changeWares(howMuchToProduce());
    }

    public void setWareEvent(int typeInt, int value)   {
        wareEvents[typeInt] = value;
    }
    
    public void setWareEvents(int[] arr)    {
        wareEvents = arr.clone();
    }
    
    public int getWareEvent(int index)   {
        return wareEvents[index];
    }
    
    public int[] getWareEvents()    {
        return wareEvents;
    }
    
    public int[] getResourceTypes()  {
        return resourceEventEffects;
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
                howMuchToProduce[i] = 10 - Math.abs(TTP[i] - techLevel) + resourceEventEffects[i];
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
                + ", Resources: [" + items[0] + ", "+items[1] + ", " + items[2] + ", "
                + items[3] + ", " + items[4] + ", " + items[5] + ", " + items[6] + ", "
                + items[7] + ", " + items[8] + ", " + items[9] + "]"
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
}
