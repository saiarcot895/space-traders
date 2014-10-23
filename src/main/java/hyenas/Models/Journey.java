/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.Models;

import javafx.scene.control.Button;

/**
 *
 * @author Alex
 */
public class Journey {
    private SolarSystem startingSolarSystem;
    private SolarSystem destinationSolarSystem;
    private Button startingSystemButton;
    private Button destinationSystemButton;
    private double distance;
    
    public Journey(SolarSystem starting, SolarSystem destination, Button startingButton, Button destinationButton, double distance) {
        startingSolarSystem = starting;
        destinationSolarSystem = destination;
        startingSystemButton = startingButton;
        destinationSystemButton = destinationButton;
        this.distance = distance;
    }
    
    public SolarSystem getStartingSolarSystem() {
        return startingSolarSystem;
    }
    
    public SolarSystem getDestinationSolarSystem() {
        return destinationSolarSystem;
    }
    
    public Button getStartingSystemButton() {
        return startingSystemButton;
    }
    
    public Button getDestinationSystemButton() {
        return destinationSystemButton;
    }
    
    public double getDistance() {
        return distance;
    }
}
