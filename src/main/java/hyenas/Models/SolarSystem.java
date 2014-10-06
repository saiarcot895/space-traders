package hyenas;

import java.util.Random;

public class SolarSystem {
    private String systemName;
    private Planet[] planets;
    private int x;
    private int y;
    private double size;
    private String color;

    public SolarSystem(String systemName) {
        this.systemName = systemName;       //CHANGE NAME PROTOCOL TODO
        Random rand = new Random();
        size = 10 + (rand.nextDouble() * 10);
        int numPlanets = rand.nextInt(5)+1;
        planets = new Planet[numPlanets];
        for(int i=0; i < numPlanets; i++)   {
            planets[i] = new Planet(systemName);
        }
        color = randomColorString();
    }
    
    /**
     * Get x
     */
    public int getX()   {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY()   {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
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
    public Planet[] getPlanets() {
        return this.planets;
    }

    public Planet getPlanetByName(String name)  {
        for(Planet planet: planets) {
            if(planet.getPlanetName().equals(name))
                return planet;
        }
        return null;
    }
    
    private String randomColorString() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return String.format("rgb(%d, %d, %d, 1)", r, g, b);
    }
    
    public String getColorString()  {
        return color;
    }

    @Override
    public String toString() {
        return "<System: " + this.systemName
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }
}
