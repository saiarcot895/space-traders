/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.UI.SolarSystemButton;
import hyenas.UI.SolarSystemInfoPane;
import hyenas.UI.UIHelper;
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
 * @author Abhishek
 */
public class MapUIController implements Initializable {

    @FXML
    private Button menu;
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private SolarSystemInfoPane infoPane;

    @FXML
    private Button currentSolarSystemButton;
    
    private final int INFO_PANE_SIZE = 200;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dimension screenSize = UIHelper.getScreenSize();
        System.out.println(screenSize.getWidth() + "," + screenSize.getHeight());
                
        HashMap<String, SolarSystem> solarSystems = Galaxy.getInstance().getSolarSystems();
        Set<String> solarSystemIDs = solarSystems.keySet();
        for (String solarSystemID: solarSystemIDs) {
            SolarSystem solarSystem = solarSystems.get(solarSystemID);
            SolarSystemButton button = new SolarSystemButton();
            button.setupForSolarSystem(solarSystem);
            
            Player player = Player.getInstance();
            SolarSystem currentSystem = player.getCurrentSystem();
            if (currentSystem == solarSystem) {
                button.getStyleClass().add("currentPlanet");
                currentSolarSystemButton = button;
            }
            
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Button button = (Button)e.getSource();
                    String solarSystemID = button.getId();
                    SolarSystem solarSystem = Galaxy.getInstance().getSolarSystemForName(solarSystemID);
                    
                    if (infoPane != null) {
                        anchor.getChildren().remove(infoPane);
                    }
                    
                    SolarSystemInfoPane newPane = new SolarSystemInfoPane();
                    newPane.setPrefSize(INFO_PANE_SIZE, INFO_PANE_SIZE);
                    newPane.setupForSolarSystem(solarSystem);
                    
                    Button travelButton = new Button("Travel");
                    travelButton.getStyleClass().add("infoPaneButton");
                    
                    EventHandler<ActionEvent> travelEvent = new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            travelToSystem(solarSystem, button);
                        }
                    };
                    travelButton.setOnAction(travelEvent);
                    AnchorPane.setBottomAnchor(travelButton, 10.0);
                    AnchorPane.setRightAnchor(travelButton, 10.0);
                    AnchorPane.setLeftAnchor(travelButton, 10.0);
                    newPane.getChildren().add(travelButton);
                    infoPane = newPane;
                    
                    anchor.getChildren().addAll(newPane);
                    
                    // Ensures entire pane stays in view region
                    int x = solarSystem.getX() + 40;
                    int y = solarSystem.getY() - (INFO_PANE_SIZE/2);
                    if (x > screenSize.getWidth() - INFO_PANE_SIZE) x = (int) screenSize.getWidth() - INFO_PANE_SIZE;
                    if (y < 0) y = 0;
                    if (y > screenSize.getHeight() - INFO_PANE_SIZE) y = (int)screenSize.getHeight() - INFO_PANE_SIZE;
                    if (x < solarSystem.getX()) x = solarSystem.getX() - (INFO_PANE_SIZE + 20);
                    
                    infoPane.setLayoutX(x);
                    infoPane.setLayoutY(y);
                }
            };
            button.setOnAction(event);
            anchor.getChildren().addAll(button);
        }
    }
    
    private void travelToSystem(SolarSystem solarSystem, Button solarSystemButton) {
        Player player = Player.getInstance();
        SolarSystem currentSystem = player.getCurrentSystem();
        Ship ship = player.getShip();
        
//        TODO: Deduct fuel, calculate fuel cost
//        TODO: Animate moving from planet
        player.setCurrentSystem(solarSystem);
        
        currentSolarSystemButton.getStyleClass().remove("currentPlanet");
        solarSystemButton.getStyleClass().add("currentPlanet");
        currentSolarSystemButton = solarSystemButton;
    }
    
    public void goToMarketplace(ActionEvent e) {
        HyenasLoader.getInstance().goToMarketplace();
    }

    public void jump(ActionEvent t) {
        System.out.println("Jump");
        // TODO: Jump to planet, randomized.
    }
    
}
