/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.Models;

/**
 *
 * @author Alex
 */

public enum PlanetType {
    None,           // How resource is affected: 
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
