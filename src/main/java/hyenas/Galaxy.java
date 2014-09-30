package hyenas;

public class Galaxy {
    private SolarSystem[] solarSystems;
    
    private static Galaxy instance;

    private Galaxy() {
        setupSolarSystems();
    }
    
    public static Galaxy getInstance() {
        if (instance == null) {
            instance = new Galaxy();
        }
        return instance;
    }

    private void setupSolarSystems() {
        final String[] systemNames = new String[] {
            "Earth616",
            "Utopia",
            "Omega",
            "Tantalos",
            "Carzon",
            "Exo",
            "Destiny",
            "Frolix",
        };

        solarSystems = new SolarSystem[systemNames.length];

        for (int x = 0; x < systemNames.length; x++) {
            String systemName = systemNames[x];
            SolarSystem solarSystem = new SolarSystem(systemName);
            solarSystems[x] = solarSystem;
        }
    }

    /**
     * Get the solar systems in the galaxy.
     *
     * @return Array of solar systems in the galaxy
     */
    public SolarSystem[] getSolarSystems() {
        return solarSystems;
    }

    public static void main(String[] args) {
        Galaxy galaxy = new Galaxy();
        SolarSystem[] solarSystems = galaxy.getSolarSystems();
        for (int x = 0; x < solarSystems.length; x++) {
            SolarSystem system = solarSystems[x];
            System.out.println(system);
        }
    }
}
