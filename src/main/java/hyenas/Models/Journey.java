package hyenas.Models;

import javafx.scene.control.Button;

/**
 * Represents the Journey a player makes when traveling between solar systems.
 * @author Alex
 */
public class Journey {
    private SolarSystem startingSolarSystem;
    private SolarSystem destinationSolarSystem;
    private Button startingSystemButton;
    private Button destinationSystemButton;
    private double distance;
    
    /**
     * Initializes a Journey instance.
     * @param starting the starting Solar System
     * @param destination the destination Solar System
     * @param startingButton the starting button
     * @param destinationButton the destination button
     * @param distance the distance between starting and destination
     */
    public Journey(SolarSystem starting, SolarSystem destination, Button
            startingButton, Button destinationButton, double distance) {
        startingSolarSystem = starting;
        destinationSolarSystem = destination;
        startingSystemButton = startingButton;
        destinationSystemButton = destinationButton;
        this.distance = distance;
    }
    
    /**
     * Getter for starting solar system.
     * @return startingSolarSystem the starting solar system
     */
    public SolarSystem getStartingSolarSystem() {
        return startingSolarSystem;
    }
    
    /**
     * Getter for destination solar system.
     * @return destinationSolarSystem the destination solar system
     */
    public SolarSystem getDestinationSolarSystem() {
        return destinationSolarSystem;
    }
    
    /**
     * Getter for starting solar system button.
     * @return startingSystemButton the starting system button
     */
    public Button getStartingSystemButton() {
        return startingSystemButton;
    }
    
    /**
     * Getter for destination solar system button.
     * @return destinationSystemButton the destination system button
     */
    public Button getDestinationSystemButton() {
        return destinationSystemButton;
    }
    
    /**
     * Getter for distance between starting and destination solar systems.
     * @return distance the distance between starting and destination
     */
    public double getDistance() {
        return distance;
    }
}
