package hyenas;

import hyenas.Models.Player;
import hyenas.Models.Planet;
import hyenas.Models.SolarSystem;
import hyenas.UI.AlertPane;
import hyenas.UI.UIHelper;
import hyenas.UI.PlanetButton;
import hyenas.UI.HoverPane;
import hyenas.UI.PlayerInfoPane;
import hyenas.UI.SolarSystemImageView;
import java.awt.Dimension;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * FXML Controller class for the system ui
 *
 * @author Brian
 */
public class SystemUIController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private VBox boxPane;
    
    @FXML
    private Pane systemPane;
    
    private Button currentPlanetButton;
    
    private PlayerInfoPane playerInfoPane;
    
    private HoverPane planetNamePane;
    
    private Timer animationTimer;
    
    private HashMap<Planet, PlanetButton> planetMap = new HashMap<Planet, PlanetButton>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        Dimension screenSize = UIHelper.getScreenSize();

        SolarSystem currentSystem = Player.getInstance().getCurrentSystem();
        List<Planet> planets = currentSystem.getPlanets();
        Planet currentPlanet = Player.getInstance().getTradingPlanet();

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
        
        planetNamePane = new HoverPane();
        
        for(Planet planet : planets)  {
            PlanetButton button = new PlanetButton();
            button.setupForPlanet(planet);
            // button.relocate(systemCenterX + planet.getOrbitRadius() - (button.getPrefWidth() / 2.0), systemCenterY);
            button.setLayoutX(systemCenterX + planet.getOrbitRadius() - (button.getPrefWidth() / 2.0) - 10);
            button.setLayoutY(systemCenterY - 10);
            
            
            planetMap.put(planet, button);
            
            Circle circle = new Circle(systemCenterX, systemCenterY, planet.getOrbitRadius());
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.5));
            circle.setStrokeWidth(1);
            circle.setFill(Color.TRANSPARENT);
            circle.setDisable(true);

            if(currentPlanet == planet) {
                button.getStyleClass().add("currentPlanet");
                currentPlanetButton = button;
            }
            
            button.hoverProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (button.isHover()) {
                        planetNamePane.setLabelText(planet.getPlanetName());
                        planetNamePane.setLayoutX(button.getLayoutX() + 20);
                        planetNamePane.setLayoutY(button.getLayoutY() + 30);
                        systemPane.getChildren().add(planetNamePane);
                    } else {
                        systemPane.getChildren().remove(planetNamePane);
                    }
                }
            });

            EventHandler<ActionEvent> event = (ActionEvent e) -> {
                Button button1 = (Button)e.getSource();
                setCurrentPlanetButton(button1, planet);
                Player player = Player.getInstance();
                player.setTradingPlanet(planet);
            };
            button.setOnAction(event);
            systemPane.getChildren().addAll(circle, button);
        }
        
        int delay = 0;
        int period = 50;

        animationTimer = new Timer();
        animationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        for (Planet planet : planetMap.keySet()) {
                            PlanetButton button = planetMap.get(planet);
                            int radius = planet.getOrbitRadius();

                            double x = button.getLayoutX() + (button.getPrefWidth() / 2.0) + 10;
                            double y = button.getLayoutY() + (button.getPrefHeight() / 2.0) + 10;
                            double sysX = UIHelper.SYSTEM_WIDTH / 2.0;
                            double sysY = UIHelper.SYSTEM_HEIGHT / 2.0;

                            double deltaX = x - sysX;
                            double deltaY = y - sysY;

                            double curTheta = Math.atan2(deltaY, deltaX);

                            double arcLength = 3;
                            double deltaTheta = 2 * Math.asin(arcLength / (2 * radius));
                            
                            double newTheta;
                            if (planet.isClockwiseOrbit()) {
                                newTheta = curTheta + deltaTheta;
                            } else {
                                newTheta = curTheta - deltaTheta;
                            }
                            
                            double newDeltaX = radius*Math.cos(newTheta);
                            double newDeltaY = radius*Math.sin(newTheta);

                            double newX = sysX + newDeltaX;
                            double newY = sysY + newDeltaY;

                            button.setLayoutX(newX - (button.getPrefWidth() / 2.0) - 10);
                            button.setLayoutY(newY - (button.getPrefHeight() / 2.0) - 10);
                        }
                    }
                });
            }
        }, delay, period);
    }

    /**
     * Sets current planet button
     * @param button, the current planet button
     * @param planet, the current planet
     */
    private void setCurrentPlanetButton(Button button, Planet planet)  {
        currentPlanetButton.getStyleClass().remove("currentPlanet");
        button.getStyleClass().add("currentPlanet");
        currentPlanetButton = button;
    }
    
    /**
     * Changes screens to marketplace
     * @param e, unused
     */
    public void goToMarketplace(ActionEvent e) {
        HyenasLoader.getInstance().goToMarketplace();
        animationTimer.cancel();
        animationTimer.purge();
    }
    
    /**
     * Changes screens to map ui
     * @param e, unused
     */
    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToMapScreen();
        animationTimer.cancel();
        animationTimer.purge();
    }
    
    /**
     * Changes screens to settings
     * @param e, unused
     */
    public void goToSettings(ActionEvent e) {
        HyenasLoader.getInstance().goToSettingsScreen();
        animationTimer.cancel();
        animationTimer.purge();
    }
    
    /**
     * Changes screens to shipyard assuming planet has high enough tech level
     * @param e, unused
     */
    public void goToShipyard(ActionEvent e) {
        Player player = Player.getInstance();
        Planet planet = player.getTradingPlanet();
        
        if (planet.hasShipyard()) {
            HyenasLoader.getInstance().goToShipyard();
            animationTimer.cancel();
            animationTimer.purge();
        } else {
            displayAlert("Insufficient Tech Level", "Your planet does not have a high enough tech level for a shipyard.");
        }
    }
    
    /**
     * Convenience method for displaying alert
     * @param title,
     * @param message, the message to display on the label
     */
    private void displayAlert(String title, String message) {
        AlertPane alertPane = new AlertPane(AlertPane.AlertPaneType.OneButton);
        alertPane.setTitleText(title);
        alertPane.setMessageText(message);
        EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
            anchorPane.getChildren().remove(alertPane);
        };
        alertPane.getCloseButton().setOnAction(closeAction);
        anchorPane.getChildren().add(alertPane);
    }
}
