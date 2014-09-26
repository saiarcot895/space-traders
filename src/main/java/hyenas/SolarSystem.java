package hyenas;

import java.util.Random;

public class SolarSystem {
    private String systemName;
    private Planet planet;
    private double x;
    private double y;

    public SolarSystem(String systemName) {
        this.systemName = systemName;
        this.planet = new Planet();

        Random rand = new Random();
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


    @Override
    public String toString() {
        return "<System: " + this.systemName
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }
}
