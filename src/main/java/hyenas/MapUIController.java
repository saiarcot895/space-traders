/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.Models.ABPair;
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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
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
            // TODO: Add x,y coordinates in SolarSystem table
            
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

        List<SolarSystem> solarSystemValues = new LinkedList<>(solarSystems.values());
        Random random = new Random();
        Map<SolarSystem, List<ABPair<SolarSystem, Double>>> distances =
                Galaxy.getInstance().getDistances();
        for (int i = 0; i < solarSystemValues.size(); i++) {
            SolarSystem solarSystem1 = solarSystemValues.get(i);
            for (int j = i; j < solarSystemValues.size(); j++) {
                SolarSystem solarSystem2 = solarSystemValues.get(j);

                double distance = getDistance(solarSystem1, solarSystem2);
                if (distance >= 300) {
                    continue;
                }

                if (random.nextDouble() >= 0.4) {
                    continue;
                }
                double weight = distance + (solarSystem1.getPlanets().length
                                - solarSystem2.getPlanets().length) * 10;
                ABPair<SolarSystem, Double> destination = new ABPair<>(solarSystem2,
                        weight);
                if (!distances.containsKey(solarSystem1)) {
                    distances.put(solarSystem1, new LinkedList<>());
                }
                distances.get(solarSystem1).add(destination);

                weight = distance + (solarSystem2.getPlanets().length
                                - solarSystem1.getPlanets().length) * 10;
                destination = new ABPair<>(solarSystem1, weight);
                if (!distances.containsKey(solarSystem2)) {
                    distances.put(solarSystem2, new LinkedList<>());
                }
                distances.get(solarSystem2).add(destination);
            }
        }

        /*int counter = 0;
        counter = solarSystemValues.parallelStream()
                .filter((solarSystem) -> (!distances.containsKey(solarSystem)))
                .map((solarSystem) -> {
                    Galaxy.getInstance().getSolarSystems().remove(solarSystem.getSystemName());
                    return solarSystem;
                }).map((_item) -> 1).reduce(counter, Integer::sum);
        System.out.println(counter + " items removed");*/

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
        double distance = getDjikstraDistance(currentSystem, solarSystem);
        if (distance == -1) {
            throw new RuntimeException("Unconnected node!!!");
        }
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

    /**
     * Go to the settings screen.
     */
    public void goToSettings() {
        HyenasLoader.getInstance().goToSettingsScreen();
    }

    /**
     * Check to see if the player can travel to the solar system.
     * @param solarSystem system to travel to
     * @return true if the player can travel there; otherwise, false
     */
    private boolean canTravelToSystem(SolarSystem solarSystem) {
        Player player = Player.getInstance();
        SolarSystem currentSystem = player.getCurrentSystem();
        double fuel = player.getShip().getFuel();
        double distance = getDjikstraDistance(currentSystem, solarSystem);
        if (distance == -1) {
            throw new RuntimeException("Unconnected node for " + solarSystem.getSystemName());
        }
        if (currentSystem == solarSystem) return false; // Can't travel to yourself
        return (fuel > distance);
    }

    /**
     * Get the linear distance between two systems.
     * @param system1 starting system
     * @param system2 ending system
     * @return distance between the two systems
     */
    private double getDistance(SolarSystem system1, SolarSystem system2) {
        int x1 = system1.getX();
        int y1 = system1.getY();
        int x2 = system2.getX();
        int y2 = system2.getY();
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    /**
     * Get the distance between two systems according to Djikstra's algorithm.
     * @param start starting system
     * @param goal ending system
     * @return distance between the two systems on the graph
     */
    private double getDjikstraDistance(SolarSystem start, SolarSystem goal) {
        Queue<ABPair<SolarSystem, Double>> distances = new PriorityQueue<>(
                (ABPair<SolarSystem, Double> o1, ABPair<SolarSystem, Double> o2)
                        -> (int) (o1.getB() - o2.getB()));
        Map<SolarSystem, List<ABPair<SolarSystem, Double>>> adjList = Galaxy
                .getInstance().getDistances();

        if (!adjList.containsKey(start)) {
                return -1;
        }
        distances.add(new ABPair<>(start, 0.0));

        Set<SolarSystem> visited = new HashSet<>();

        while (!distances.isEmpty()) {
                ABPair<SolarSystem, Double> node = distances.remove();
                if (node.getA().equals(goal)) {
                        return node.getB();
                }

                if (!visited.contains(node.getA())) {
                        visited.add(node.getA());
                        List<ABPair<SolarSystem, Double>> neighbors = adjList.get(node.getA());
                        if (neighbors != null) {
                            adjList.get(node.getA()).parallelStream().forEach((neighbor) -> {
                                double alternateDistance = node.getB() + neighbor.getB();
                                distances.add(new ABPair<>(neighbor.getA(), alternateDistance));
                            });
                        }
                }
        }

        return -1;
    }

    /**
     * Exit the game.
     */
    public void quitGame() {
        // TODO: Save game before quitting. (M7)
        Player.getInstance().setState(false); // They are not playing the game anymore.
        HyenasLoader.getInstance().goToHomeScreen();
    }

}