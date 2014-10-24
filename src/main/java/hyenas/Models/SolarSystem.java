package hyenas.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code SolarSystem} class represents a solar system in the galaxy.
 * @author saikrishna
 */
public class SolarSystem {
    private String systemName;
    private List<Planet> planets;
    private int x;
    private int y;
    private double size;
    private String color;

    public SolarSystem(String systemName) {
        this.systemName = systemName;       //CHANGE NAME PROTOCOL TODO
        Random rand = new Random();
        size = 10 + (rand.nextDouble() * 10);
        int numPlanets = rand.nextInt(5) + 1;
        planets = new ArrayList<>();
        for (int i = 0; i < numPlanets; i++) {
            planets.add(new Planet(systemName));
        }
        color = randomColorString();
    }

    /**
     * Get the x-coordinate of the solar system.
     * @return x-coordinate of the system
     */
    public int getX()   {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the y-coordinate of the solar system.
     * @return y-coordinate of the system
     */
    public int getY()   {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the size of the solar system.
     * @return size of the system
     */
    public double getSize()   {
        return size;
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
    public List<Planet> getPlanets() {
        return this.planets;
    }

    /**
     * Get a planet of the solar system based on its name.
     * @param name name of the planet
     * @return planet matching the name passed in. If no such planet exists,
     * then null
     */
    public Planet getPlanetByName(String name)  {
        for (Planet planet: planets) {
            if (planet.getPlanetName().equals(name)) {
                return planet;
            }
        }
        return null;
    }

    /**
     * Create a random color to be assigned to the solar system.
     * @return generated color string
     */
    private static String randomColorString() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return String.format("rgb(%d, %d, %d, 1)", r, g, b);
    }

    /**
     * Get the color of the solar system. The color is returned in the form
     * "rgb(r, g, b, 1)", where "r" is the red channel (0-255), "g" is the green
     * channel (0-255), and "b" is the blue channel (0-255). The final 1 is the
     * transparency (opaque).
     * @return color of the solar system
     */
    public String getColorString()  {
        return color;
    }

    @Override
    public String toString() {
        return "<System: " + this.systemName
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }
}
