package hyenas;

import java.util.Random;

public class SolarSystem {
    private String systemName;
    private Planet[] planets;
    private int x;
    private int y;

    public SolarSystem(String systemName) {
        this.systemName = systemName;
        Random rand = new Random();
        this.x = rand.nextInt(600);
        this.y = rand.nextInt(600);
        int size = rand.nextInt(5)+1;
        planets = new Planet[size];
        for(int i=0; i<size; i++)	{
        	planets[i] = new Planet(systemName);
        }
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
