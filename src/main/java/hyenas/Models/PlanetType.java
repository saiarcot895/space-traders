package hyenas.Models;

/**
 * A PlanetType, used to distinguish which resource is abundant/scare on a
 * planet
 * 
 * @author Alex
 */
public enum PlanetType {
    None,           // How resource is affected (+/-): 
    MineralRich,    // Ore+
    MineralPoor,    // Ore-
    LotsOfWater,    // Water+
    Desert,         // Water-
    RichSoil,       // Food+
    PoorSoil,       // Food-
    RichFauna,      // Furs+
    Lifeless,       // Furs-
    WeirdMushrooms, // Narcotics+
    LotsOfHerbs,    // Medicine+
    Artistic,       // Games+
    Warlike,        // Firearms+
}
