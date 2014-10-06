/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.UI.UIHelper;
import hyenas.UI.PlanetButton;
import hyenas.UI.SolarSystemButton;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;



import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class SystemUIController implements Initializable {
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private AnchorPane systemPane;
    
    @FXML
    private Button marketPlace;
    
    @FXML
    private MenuButton options;
    
    private Planet currentPlanet;
    
    private Button currentPlanetButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        Dimension screenSize = UIHelper.getScreenSize();
        
        SolarSystem currentSystem = Player.getInstance().getCurrentSystem();
        Planet[] planets = currentSystem.getPlanets();
        currentPlanet = Player.getInstance().getTradingPlanet();
        
        SolarSystemButton currentSystemButton = new SolarSystemButton();
        currentSystemButton.setupForSystemUI(currentSystem);
        currentSystemButton.setLayoutX(systemPane.getWidth() / 2);
        currentSystemButton.setLayoutY(systemPane.getHeight() / 2);
        systemPane.getChildren().add(currentSystemButton);
        
        for(Planet planet : planets)  {
            PlanetButton button = new PlanetButton();
            button.setupForPlanet(planet);
            
            if(currentPlanet == planet) {
                button.getStyleClass().add("currentPlanet");
                currentPlanetButton = button;
            }
            
            EventHandler<ActionEvent> event = (ActionEvent e) -> {
                Button button1 = (Button)e.getSource();
                setCurrentPlanetButton(button1, planet);
                Player.getInstance().setTradingPlanet(planet);
            };
            systemPane.getChildren().add(button);
        }
    }
    
    private void setCurrentPlanetButton(Button button, Planet planet)  {
        currentPlanetButton.getStyleClass().remove("currentPlanet");
        button.getStyleClass().add("currentPlanet");
        currentPlanetButton = button;
        currentPlanet = planet;
    }
    
    public void goToMarketplace(ActionEvent e) {
        HyenasLoader.getInstance().goToMarketplace();
    }
}
