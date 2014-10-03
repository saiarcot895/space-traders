/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.UI.UIHelper;
import hyenas.UI.PlanetButton;
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
    private Button menu;
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Button currentPlanetButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        Dimension screenSize = UIHelper.getScreenSize();
        
        Planet[] planets = Player.getInstance().getCurrentSystem().getPlanets();
        Planet currentPlanet = Player.getInstance().getTradingPlanet();
        
        for(Planet planet:planets)  {
            PlanetButton button = new PlanetButton();
            button.setupForPlanet(planet);
            
            if(currentPlanet == planet) {
                button.getStyleClass().add("currentPlanet");
                currentPlanetButton = button;
            }
            
            EventHandler<ActionEvent> event = (ActionEvent e) -> {
                    Button button1 = (Button)e.getSource();
                    String planetId = button1.getId();
                    setCurrentPlanetButton(button1);
                    Player.getInstance().setTradingPlanet(planet);
                };
        }
    }
    
    private void setCurrentPlanetButton(Button button)  {
        currentPlanetButton.getStyleClass().remove("currentPlanet");
        button.getStyleClass().add("currentPlanet");
        currentPlanetButton = button;
        currentPlanet = planet;
    }
    
    public void goToMarketplace(ActionEvent e) {
        HyenasLoader.getInstance().goToMarketplace();
    }
}
