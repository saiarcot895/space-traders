package hyenas.Models;

import hyenas.UI.UIHelper;
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
    private final int ORBIT_TOLERANCE = 30;

    /**
     * Initializes an instance of SolarSyem and sets initial values
     * @param systemName, the name of the system
     */
    public SolarSystem(String systemName) {
        this.systemName = systemName;
        Random rand = new Random();
        size = 10 + (rand.nextDouble() * 10);
        color = UIHelper.randomColorString();
        setupPlanets();
    }
    
    /**
     * Sets up a random number (1-5) of planets for the solar system.
     */
    private void setupPlanets() {
        final String[] planetNames = new String[] {
            "6 Echo",
            "Cybertron",
            "Curie 3",
            "Nzinga",
            "Lithios",
            "Reach",
            "Halo",
            "Reconciliation",
            "New Mombasa",
            "Draconis",
            "Pegasus",
            "Zeta",
            "Hellespont",
            "Jericho",
            "Hydra",
            "Alpha",
            "Librae",
            "Legit",
            "Halcyon",
            "Ceres",
            "Gaia",
            "Cronus",
            "Metis",
            "Pandora",
            "Moonbase Alpha",
            "Eris",
            "Demetris",
            "Threshold",
            "Harvest",
            "Arcadia",
            "Installation 04",
            "Onyx",
            "Origin",
            "Cryptum",
            "Glasslands",
            "Escalation",
            "Cole Protocol",
            "Midlothian",
            "Valar",
            "Arda",
            "Castleguard",
            "Midgard",
            "Earendel",
            "Middle Earth",
            "Earth",
            "Melkor",
            "Eru",
            "Silmarillion",
            "Illuvatar",
            "Valinor",
            "Luthien",
            "Arnor",
            "Isildur",
            "Pelennor",
            "Arwen",
            "Numenor",
            "Narsil",
            "Barahir",
            "Denethor",
            "Tinuviel",
            "Thranduil",
            "Harad",
            "Caradhras",
            "Anduin",
            "Lothlorien",
            "Rauros",
            "Dunharrow",
            "Telcontar",
            "Haradrim",
            "Eldarion",
            "Easterlings",
            "Ithilien",
            "Imrahil",
            "Orthanc",
            "Dunadan",
            "Evinyatar",
            "Qatar",
            "Estel",
            "Cirdan",
            "Narya",
            "Aelia",
            "Aquila",
            "Atilius",
            "Aulus",
            "Avilius",
            "Avitus",
            "Cassia",
            "Celsus",
            "Drusa",
            "Ennius",
            "Felix",
            "Gallus",
            "Hadrianus",
            "Horatia",
            "Livia",
            "Nerva",
            "Nova",
            "Quintus",
            "Seneca",
            "Sergius",
            "Tacitus",
            "Tiber",
            "Valeria",
            "Valhalla",
            "Narnia",
            "Constantine",
            "Vita",
            "Mithril",
            "Istari",
            "Ichitari",
            "Erebor",
            "Don Guldur",
            "Thrain",
            "Gladden",
            "Bree",
            "Glamdring",
            "Beorn",
            "Gwaihir",
            "Mearas",
            "Grima",
            "Shadowfax",
            "Tirith",
            "Angmar",
            "Imrahil",
            "Rath",
            "Dinen",
            "Orodruin",
            "Undying",
            "Sotheby",
            "Dolomite",
            "Dale",
            "Stiver"
        };
        
        this.planets = new ArrayList<>();
        
        Random rand = new Random();
        int numPlanets = rand.nextInt(5) + 1;
        
        for (int i = 0; i < numPlanets; i++) {
            String randomPlanetName;
            do {
                // Make sure we don't get a duplicate planet name in the same system
                int randomIndex = rand.nextInt(planetNames.length);
                randomPlanetName = planetNames[randomIndex];
            } while (planetNameUsed(randomPlanetName));
            
            Planet newPlanet = new Planet(randomPlanetName);
            
            int orbitRadius;
            do {
                // Make sure orbits don't overlap
                orbitRadius = 110 + rand.nextInt(200);
            } while (orbitRadiusUsed(orbitRadius));
            
            newPlanet.setOrbitRadius(orbitRadius);
            planets.add(newPlanet);
        }
    }
    
    /**
     * Returns whether a given name has already been used for a planet
     * @param name, the name to check
     * @return boolean, whether the planet's name has been used
     */
    private boolean planetNameUsed(String name) {
        for (Planet planet: this.planets) {
            if (planet.getPlanetName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns whether a given orbit radius has been used (within a tolerance)
     * to prevent planet orbits from overlapping
     * @param orbitRadius, the orbit radius to check
     * @return boolean, whether the planet's orbit radius overlaps another's
     */
    private boolean orbitRadiusUsed(int orbitRadius) {
        for (Planet planet: this.planets) {
            if (Math.abs(planet.getOrbitRadius() - orbitRadius) < ORBIT_TOLERANCE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the x-coordinate of the solar system
     * @return x, the x-coordinate of the system
     */
    public int getX()   {
        return x;
    }
    
    /**
     * Set the x-coordinate of the solar system
     * @param x, x-coordinate of the system
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the y-coordinate of the solar system
     * @return y, the y-coordinate of the system
     */
    public int getY()   {
        return y;
    }
    
    /**
     * Set the y-coordinate of the solar system
     * @param y, y-coordinate of the system
     */
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
        return "<System: " + this.systemName + ", Loc: (" + x + ", " + y + ")"
                + ">";
    }
}
