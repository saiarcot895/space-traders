import java.util.Random;
import java.util.ArrayList;

public class SolarSystem {
    private String systemName;
    private Planet planet;
    private double locX;
    private double locY;
    private int techLevel;
    private int resourceType;
    
    private static final int MAX_TECH_LEVEL = 7;
    private static final int MAX_RESOURCE_TYPE = 12;

    public SolarSystem(String systemName) {
        this.systemName = systemName;
        this.planet = new Planet();

        Random rand = new Random();
        this.techLevel = rand.nextInt(MAX_TECH_LEVEL + 1);
        this.resourceType = rand.nextInt(MAX_RESOURCE_TYPE + 1);
        this.locX = rand.nextDouble();
        this.locY = rand.nextDouble();
    }

    public String getSystemName() {
        return this.systemName;
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public String techLevelString() {
        final String[] techLevels = new String[] {
            "Pre-Agriculture",
            "Agriculture",
            "Medieval",
            "Renaissance",
            "Early Industrial",
            "Industrial",
            "Post-Industrial",
            "Hi-Tech",
        };
        return techLevels[this.techLevel];
    }

    public String resourceTypeString() {
        final String[] resourceTypes = new String[] {
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
        return resourceTypes[this.resourceType];
    }

    @Override
    public String toString() {
        return "<System: " + this.systemName +
                ", Tech: " + this.techLevelString() +
                ", Resource: " + this.resourceTypeString() +
                ", Loc: (" + locX + ", " + locY + ")" + ">";
    }
}
