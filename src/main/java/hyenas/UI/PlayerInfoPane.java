/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.Models.Player;
import hyenas.Models.Ship;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Alex
 */
public class PlayerInfoPane extends AnchorPane {
    @FXML
    private Label creditsLabel;
    
    @FXML
    private Label fuelLabel;
    
    @FXML
    private Label healthLabel;
    
    private final int PLAYER_INFO_PANE_WIDTH = 300;
    private final int PLAYER_INFO_PANE_HEIGHT = 60;
    
    public PlayerInfoPane() {
        setPrefSize(PLAYER_INFO_PANE_WIDTH, PLAYER_INFO_PANE_HEIGHT);
        
        creditsLabel = new Label();
        AnchorPane.setTopAnchor(creditsLabel, 10.0);
        AnchorPane.setLeftAnchor(creditsLabel, 0.0);
        
        healthLabel = new Label();
        AnchorPane.setLeftAnchor(healthLabel, 0.0);
        AnchorPane.setTopAnchor(healthLabel, 30.0);
        
        fuelLabel = new Label();
        AnchorPane.setTopAnchor(fuelLabel, 50.0);
        AnchorPane.setLeftAnchor(fuelLabel, 0.0);
        
        updateInfo();
        
        getChildren().addAll(creditsLabel, healthLabel, fuelLabel);
    }
    
    public void updateInfo() {
        Player player = Player.getInstance();
        Ship ship = player.getShip();
        
        String credits = "Credits: " + player.getCredits();
        String health = "Health: " + Math.floor(ship.getHealth()) + "/" + Math.floor(ship.getMaxHealth());
        String fuel = "Fuel: " + Math.floor(ship.getFuel()) + "/" + Math.floor(ship.getMaxFuel());
        
        creditsLabel.setText(credits);
        healthLabel.setText(health);
        fuelLabel.setText(fuel);
    }
}
