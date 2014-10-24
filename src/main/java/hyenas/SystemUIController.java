/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.Models.Player;
import hyenas.Models.Planet;
import hyenas.Models.SolarSystem;
import hyenas.UI.UIHelper;
import hyenas.UI.PlanetButton;
import hyenas.UI.SolarSystemImageView;
import java.awt.Dimension;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class SystemUIController implements Initializable {
    
    @FXML
    private VBox boxPane;
    
    @FXML
    private Pane systemPane;
    
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
         List<Planet> planets = currentSystem.getPlanets();
        currentPlanet = Player.getInstance().getTradingPlanet();

        SolarSystemImageView currentSystemButton = new SolarSystemImageView();
        currentSystemButton.setupForSystemUI(currentSystem);

        double systemCenterX = UIHelper.SYSTEM_WIDTH / 2.0;
        double systemCenterY = UIHelper.SYSTEM_HEIGHT / 2.0;
        currentSystemButton.setLayoutX(systemCenterX - (currentSystemButton.getFitWidth() / 2.0));
        currentSystemButton.setLayoutY(systemCenterY - (currentSystemButton.getFitHeight() / 2.0));

        systemPane.setPrefHeight(UIHelper.SYSTEM_HEIGHT);
        systemPane.setPrefWidth(UIHelper.SYSTEM_WIDTH);
        systemPane.setMaxHeight(UIHelper.SYSTEM_HEIGHT);
        systemPane.setMaxWidth(UIHelper.SYSTEM_WIDTH);
        systemPane.setLayoutX(screenSize.getWidth() / 2);
        systemPane.getChildren().add(currentSystemButton);
        for(Planet planet : planets)  {
            PlanetButton button = new PlanetButton();
            button.setupForPlanet(planet);
            // button.relocate(systemCenterX + planet.getOrbitRadius() - (button.getPrefWidth() / 2.0), systemCenterY);
            button.setLayoutX(systemCenterX + planet.getOrbitRadius() - (button.getPrefWidth() / 2.0) - 10);
            button.setLayoutY(systemCenterY - 10);
            System.out.println("System: " + systemCenterX + "," + systemCenterY);
            Circle circle = new Circle(systemCenterX, systemCenterY, planet.getOrbitRadius());
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.5));
            circle.setStrokeWidth(1);
            circle.setFill(Color.TRANSPARENT);
            circle.setDisable(true);
            // circle.setStyle("-fx-stroke-dash-array: 12 2 4 2;"); 
            // circle.setStyle("-fx-stroke-dash-array: 12 2 4 2; -fx-stroke-width: 5;-fx-stroke: green;"); 

            if(currentPlanet == planet) {
                button.getStyleClass().add("currentPlanet");
                currentPlanetButton = button;
            }

            EventHandler<ActionEvent> event = (ActionEvent e) -> {
                Button button1 = (Button)e.getSource();
                setCurrentPlanetButton(button1, planet);
                Player.getInstance().setTradingPlanet(planet);
            };
            systemPane.getChildren().addAll(circle, button);
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

    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToMapScreen();
    }
}
