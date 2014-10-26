package hyenas.Models;

/**
 * A PlanetEvent, used to distinguish between events that can affect a Planet
 * @author Alex
 */
public enum PlanetEvent {
    None,           // Which resources become scarce:
    Drought,        // Water
    Cold,           // Furs
    Cropfail,       // Food
    War,            // Ore, firearms
    Boredom,        // Games, Narcotics
    Plague,         // Medicine
    LackOfWorkers,  // Machines, Robots
}

