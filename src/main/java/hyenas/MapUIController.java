/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.Models.Galaxy;
import hyenas.Models.Player;
import hyenas.Models.Ship;
import hyenas.Models.SolarSystem;
import hyenas.UI.SolarSystemButton;
import hyenas.UI.SolarSystemInfoPane;
import hyenas.UI.SolarSystemScrollPane;
import hyenas.UI.UIHelper;
import java.awt.Dimension;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

        Map<String, SolarSystem> solarSystems = Galaxy.getInstance().getSolarSystems();
        Set<String> solarSystemIDs = solarSystems.keySet();
        solarSystemIDs.stream().map((solarSystemID) -> {
            return solarSystems.get(solarSystemID);
        }).map((SolarSystem solarSystem) -> {
            SolarSystemButton button = new SolarSystemButton();
            button.setupForMapUI(solarSystem);
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
                newPane.getTravelButton().setDisable(!canTravelToSystem(solarSystem1));

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
            scrollContentPane.getChildren().add(button);
        });

        double x = currentSolarSystemButton.getLayoutX();
        double y = currentSolarSystemButton.getLayoutY();

        Player player = Player.getInstance();
        SolarSystem currentSystem = player.getCurrentSystem();
        Circle travelRange = new Circle(x, y, player.getShip().getFuel());
//        travelRange.setStrokeType(StrokeType.OUTSIDE);
//        travelRange.setStroke(Color.web("white", 0.5));
//        travelRange.setStrokeWidth(1);
        travelRange.setFill(Color.web("cyan", 0.1));
        travelRange.setDisable(true);
        scrollContentPane.getChildren().add(travelRange);



        Dimension screenSize = UIHelper.getScreenSize();
        scrollPane.setPrefSize(screenSize.getWidth(), screenSize.getHeight());
        scrollPane.setContent(scrollContentPane);



//        scrollPane.setOnScroll(
//            new EventHandler<ScrollEvent>() {
//                @Override
//                public void handle(ScrollEvent event) {
//                    double zoomFactor = 1.05;
//                    double deltaY = event.getDeltaY();
//                    if (deltaY < 0) {
//                        zoomFactor = 2.0 - zoomFactor;
//                    }
//                    System.out.println(zoomFactor);
//                    scrollContentPane.setScaleX(scrollContentPane.getScaleX() * zoomFactor);
//                    scrollContentPane.setScaleY(scrollContentPane.getScaleY() * zoomFactor);
//                    event.consume();
//                }
//            });

        anchor.getChildren().add(scrollPane);
    }

    private void travelToSystem(SolarSystem solarSystem, Button solarSystemButton) {
        Player player = Player.getInstance();
        SolarSystem currentSystem = player.getCurrentSystem();
        Ship ship = player.getShip();

        double startingFuel = ship.getFuel();
        double distance = getDistance(currentSystem, solarSystem);
        ship.setFuel(startingFuel - distance);
        player.setCurrentSystem(solarSystem);

        // Messing around with animations, save for later
        /*
        scrollPane.setPannable(false);
        Dimension screenSize = UIHelper.getScreenSize();

        Pane contentPane = (Pane)scrollPane.getContent();

        System.out.println("System: "+solarSystem.getX() +","+solarSystem.getY());
        double hvalue;
        if (solarSystem.getX() <= GALAXY_SIZE / 2) {
           double factor = (((-.004 * solarSystem.getX()) + 5) * solarSystem.getX());
           System.out.println("X less than: "+factor);
           hvalue = factor / GALAXY_SIZE;
        } else {
           double factor = (((.004 * solarSystem.getX()) - 3) * solarSystem.getX());
           System.out.println("X greater than: "+factor);
           hvalue = factor / GALAXY_SIZE;
        }

        System.out.println("HV: "+hvalue +","+vvalue);

        final Timeline timeline = new Timeline();
        final KeyValue kvScaleX = new KeyValue(contentPane.scaleXProperty(), 5.0);
        final KeyValue kvScaleY = new KeyValue(contentPane.scaleYProperty(), 5.0);
        final KeyValue kvH = new KeyValue(scrollPane.hvalueProperty(), hvalue);
        final KeyValue kvV = new KeyValue(scrollPane.vvalueProperty(), vvalue);
        final KeyFrame kfScaleX = new KeyFrame(Duration.millis(500), kvScaleX);
        final KeyFrame kfScaleY = new KeyFrame(Duration.millis(500), kvScaleY);
        final KeyFrame kfH = new KeyFrame(Duration.millis(500), kvH);
        final KeyFrame kfV = new KeyFrame(Duration.millis(500), kvV);
        timeline.getKeyFrames().add(kfScaleX);
        timeline.getKeyFrames().add(kfScaleY);
        timeline.getKeyFrames().add(kfH);
        timeline.getKeyFrames().add(kfV);
        timeline.play();*/

        currentSolarSystemButton.getStyleClass().remove("currentPlanet");
        solarSystemButton.getStyleClass().add("currentPlanet");
        currentSolarSystemButton = solarSystemButton;

        HyenasLoader.getInstance().goToSystemScreen();
    }

    public void goToSettings() {
        HyenasLoader.getInstance().goToSettingsScreen();
    }

    private boolean canTravelToSystem(SolarSystem solarSystem) {
        Player player = Player.getInstance();
        SolarSystem currentSystem = player.getCurrentSystem();
        double fuel = player.getShip().getFuel();
        double distance = getDistance(currentSystem, solarSystem);
        if (currentSystem == solarSystem) return false; // Can't travel to yourself
        return (fuel > distance);
    }

    private double getDistance(SolarSystem system1, SolarSystem system2) {
        int x1 = system1.getX();
        int y1 = system1.getY();
        int x2 = system2.getX();
        int y2 = system2.getY();
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    public void quitGame() {
        // TODO: Save game before quitting. (M7)
        // Don't write in methods you haven't implemented - or at least comment them out so we can still build
//        Player.getInstance().setCurrentState(false);

        HyenasLoader.getInstance().goToHomeScreen();
    }

}
