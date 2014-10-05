/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.UI.SolarSystemButton;
import hyenas.UI.SolarSystemInfoPane;
import hyenas.UI.SolarSystemScrollPane;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
    private Button currentSolarSystemButton;
    
    @FXML
    private SolarSystemScrollPane scrollPane = new SolarSystemScrollPane();
    
    private final int INFO_PANE_SIZE = 200;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane scrollContentPane = new Pane();
        scrollContentPane.setPrefSize(UIHelper.GALAXY_SIZE, UIHelper.GALAXY_SIZE);
        scrollContentPane.setStyle("-fx-background-color: transparent;");
        
        HashMap<String, SolarSystem> solarSystems = Galaxy.getInstance().getSolarSystems();
        Set<String> solarSystemIDs = solarSystems.keySet();
        solarSystemIDs.stream().map((solarSystemID) -> {
            return solarSystems.get(solarSystemID);
        }).map((SolarSystem solarSystem) -> {
            SolarSystemButton button = new SolarSystemButton();
            button.setupForSolarSystem(solarSystem);
            Player player = Player.getInstance();
            SolarSystem currentSystem = player.getCurrentSystem();
            if (currentSystem == solarSystem) {
                button.getStyleClass().add("currentPlanet");
                currentSolarSystemButton = button;
            }
            return button;            
        }).map((button) -> {
            EventHandler<ActionEvent> event = (ActionEvent e) -> {
                Button button1 = (Button)e.getSource();
                String solarSystemID1 = button1.getId();
                SolarSystem solarSystem1 = Galaxy.getInstance().getSolarSystemForName(solarSystemID1);
                
                SolarSystemInfoPane newPane = new SolarSystemInfoPane();
                newPane.setPrefSize(INFO_PANE_SIZE, INFO_PANE_SIZE);
                newPane.setupForSolarSystem(solarSystem1);
                
                EventHandler<ActionEvent> travelEvent = (ActionEvent e1) -> {
                    travelToSystem(solarSystem1, button1);
                };
                newPane.getTravelButton().setOnAction(travelEvent);
                
                // Ensures entire pane stays in view region
                int x = solarSystem1.getX() + 40;
                int y = solarSystem1.getY() - (INFO_PANE_SIZE/2);
                if (x > UIHelper.GALAXY_SIZE - INFO_PANE_SIZE)
                    x = (int) UIHelper.GALAXY_SIZE - INFO_PANE_SIZE;
                if (y < 0) y = 0;
                if (y > UIHelper.GALAXY_SIZE - INFO_PANE_SIZE)
                    y = UIHelper.GALAXY_SIZE - INFO_PANE_SIZE;
                if (x < solarSystem1.getX())
                    x = solarSystem1.getX() - (INFO_PANE_SIZE + 20);
                
                scrollPane.setInfoPane(newPane);
                scrollContentPane.getChildren().addAll(newPane);
                newPane.setLayoutX(x);
                newPane.setLayoutY(y);
            };
            button.setOnAction(event);
            return button;
        }).forEach((button) -> {
            scrollContentPane.getChildren().addAll(button);
        });
        
        double x = currentSolarSystemButton.getLayoutX();
        double y = currentSolarSystemButton.getLayoutY();
        
        Dimension screenSize = UIHelper.getScreenSize();
        scrollPane.setPrefSize(screenSize.getWidth(), screenSize.getHeight());
        scrollPane.setContent(scrollContentPane);
        scrollPane.setHvalue(x / screenSize.getWidth());
        scrollPane.setVvalue(y / screenSize.getHeight());
        
        anchor.getChildren().add(scrollPane);
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
    
    public void goToSettings() {
        HyenasLoader.getInstance().goToSettingsScreen();
    }
    
    public void quitGame() {
        // TODO: Save game before quitting. (M7)
        // Don't write in methods you haven't implemented - or at least comment them out so we can still build
//        Player.getInstance().setCurrentState(false);

        HyenasLoader.getInstance().goToHomeScreen();
    }
    
}
