package hyenas;

import java.util.Random;

public class SolarSystem {
    private String systemName;
    private Planet[] planets = new Planet[1];
    private double x;
    private double y;

    public SolarSystem(String systemName) {
        this.systemName = systemName;
        this.planets[0] = new Planet();

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
     * Get the planets in the solar system.
     * @return planets in the solar system
     */
    public Planet[] getPlanets() {
        return this.planets;
    }


    @Override
    public String toString() {
        return "<System: " + this.systemName
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }
}
