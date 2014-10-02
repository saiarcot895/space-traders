package hyenas;

import java.util.Random;

public class SolarSystem {
    private String systemName;
    private Planet[] planets;
    private int x;
    private int y;
    private int size;

    public SolarSystem(String systemName) {
        this.systemName = systemName;       //CHANGE NAME PROTOCOL TODO
        Random rand = new Random();
        size = 10 + rand.nextInt(10);
        int numPlanets = rand.nextInt(5)+1;
        planets = new Planet[numPlanets];
        for(int i=0; i < numPlanets; i++)   {
            planets[i] = new Planet(systemName);
        }
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
    
    public int getSize()   {
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


    @Override
    public String toString() {
        return "<System: " + this.systemName
            + ", Loc: (" + x + ", " + y + ")" + ">";
    }
}
