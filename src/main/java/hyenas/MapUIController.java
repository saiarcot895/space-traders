/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.Models.ABPair;
import hyenas.Models.Galaxy;
import hyenas.Models.Journey;
import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.RandomEvent;
import hyenas.Models.RandomEventType;
import hyenas.Models.Ship;
import hyenas.Models.SolarSystem;
import hyenas.UI.PlayerInfoPane;
import hyenas.UI.AlertPane;
import hyenas.UI.AlertPaneType;
import hyenas.UI.HoverPane;
import hyenas.UI.SolarSystemButton;
import hyenas.UI.SolarSystemInfoPane;
import hyenas.UI.SolarSystemScrollPane;
import hyenas.UI.UIHelper;
import hyenas.database.PlanetTable;
import hyenas.database.PlayerTable;
import hyenas.database.SolarSystemTable;
import java.awt.Dimension;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @FXML
    private PlayerInfoPane playerInfoPane;
    
    @FXML
    private Journey currentJourney;
    
    @FXML
    private Circle travelRange;

    private SolarSystemTable ssTable;
    
    private PlayerTable playerTable;
    
    private HoverPane systemNamePane;
    
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
        ssTable = HyenasLoader.getInstance().getSSTable();
        systemNamePane = new HoverPane();
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

        List<SolarSystem> solarSystemValues = new LinkedList<>(solarSystems.values());

        if (!Galaxy.getInstance().isLocationsSet()) {
            ssTable.beginTransaction();

            PlanetTable planetTable = HyenasLoader.getInstance().getPlanetTable();

            for (int i = 0; i < solarSystemValues.size(); i++) {
                SolarSystem ss = solarSystemValues.get(i);
                ssTable.populateTable(ss.getSystemName(), ss.getX(), ss.getY(), i);
                ss.getPlanets().stream().forEach((planet) -> {
                    planetTable.populateTable(planet, ss);
                });
            }
            
            try {
                HyenasLoader.getInstance().getPlayerTable().updateLocation(
                        Player.getInstance().getCurrentSystem());
            } catch (SQLException ex) {
                Logger.getLogger(MapUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ssTable.commitTransaction();
        }

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
                double weight = distance + (solarSystem1.getPlanets().size()
                                - solarSystem2.getPlanets().size()) * 10;
                ABPair<SolarSystem, Double> destination = new ABPair<>(solarSystem2,
                        weight);
                if (!distances.containsKey(solarSystem1)) {
                    distances.put(solarSystem1, new LinkedList<>());
                }
                distances.get(solarSystem1).add(destination);

                weight = distance + (solarSystem2.getPlanets().size()
                                - solarSystem1.getPlanets().size()) * 10;
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
        travelRange = new Circle(x, y, player.getShip().getFuel());
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
        playerInfoPane = new PlayerInfoPane();
        AnchorPane.setBottomAnchor(playerInfoPane, 0.0);
        AnchorPane.setLeftAnchor(playerInfoPane, 10.0);
        
        anchor.getChildren().addAll(scrollPane, playerInfoPane);
    }
    
    private void handleRandomEvent(RandomEventType eventType) {
        scrollPane.setInfoPane(null); // Remove system info pane
        AlertPane eventPane = new AlertPane(AlertPaneType.TwoButtons);
        RandomEvent event = new RandomEvent(eventType);
        eventPane.setTitleText(event.getDescription());
        eventPane.setMessageText(event.getQuestion());
        eventPane.getActionButton().setText(event.getActionButtonText());
        eventPane.getCloseButton().setText(event.getCancelButtonText());
        
        //
        // police
        // action: pay fine
        // cancel: run away
        // action result: deduct 5% of credits, continue to planet
        // cancel result 1: run away successfully, fuel deducted arrive at planet
        // cancel result 2: run away unnsuccessfully, double fine deducted, half fuel deducted, dont arrive at planet
        //
        
        // pirate
        // action: fight
        // cancel: run away
        // action result 1: successfully fend off pirates, ship slightly damaged fuel deducted, arrive at planet
        // action result 2: fight unsuccessfully, ship half damaged, half fuel deducted, dont arrive at planet
        // cancel result 1: run away successfully, fuel deducted arrive at planet
        // cancel result 2: run away unnsuccessfully, ship half damaged, half fuel deducted, dont arrive at planet
        
        // trader
        // action: trade
        // cancel: ignore
        // action result: sell unused parts, credits added, fuel deducted, arrive at planet
        // cancel result: fuel deducted, arrive at planet
        
        EventHandler<ActionEvent> actionEvent = (ActionEvent e1) -> {
            boolean success = event.performAction();
            String resultText = event.getActionResultText();
            switch (eventType) {
                case Police: break;
                case Trader: break;
                case Pirate:
                    SolarSystem solarSystem = currentJourney.getDestinationSolarSystem();
                    if (!success) {
                        Player player = Player.getInstance();
                        Ship ship = player.getShip();
                        // Deduct fuel for half the journey
                        ship.setFuel(ship.getFuel() - (currentJourney.getDistance() / 2));
                        solarSystem = currentJourney.getStartingSolarSystem();
                    }
                    
                    resultText = resultText + solarSystem.getSystemName() + ".";
                    break;
            }
            AlertPane resultPane = new AlertPane(AlertPaneType.OneButton);
            resultPane.setMessageText(resultText);
            
            EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
                switch (eventType) {
                    case Police:
                    case Trader:
                        makeJourney(currentJourney);
                        break;
                    case Pirate:
                        if (success) {
                            makeJourney(currentJourney);
                        }
                        break;
                }
                
                anchor.getChildren().remove(resultPane);
                
                playerTable = HyenasLoader.getInstance().getPlayerTable();
                try {
                    playerTable.updateLocation(currentJourney.getDestinationSolarSystem());
                } catch (SQLException ex) {
                    Logger.getLogger(MapUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            resultPane.getCloseButton().setOnAction(closeAction);
            playerInfoPane.updateInfo();
            anchor.getChildren().remove(eventPane);
            anchor.getChildren().add(resultPane);
        };
        eventPane.getActionButton().setOnAction(actionEvent);
        
        EventHandler<ActionEvent> cancelEvent = (ActionEvent e1) -> {
            boolean success = event.performCancel();
            String resultText = event.getCancelResultText();
            switch (eventType) {
                case Police:
                    if (!success) {
                        Player player = Player.getInstance();
                        Ship ship = player.getShip();
                        // Deduct fuel for half the journey
                        ship.setFuel(ship.getFuel() - (currentJourney.getDistance() / 2));
                    }
                    break;
                case Trader:
                    makeJourney(currentJourney);
                    break;
                case Pirate:
                    if (!success) {
                        Player player = Player.getInstance();
                        Ship ship = player.getShip();
                        // Deduct fuel for half the journey
                        ship.setFuel(ship.getFuel() - (currentJourney.getDistance() / 2));
                        resultText = resultText + currentJourney.getStartingSolarSystem().getSystemName() + ".";
                    }
                    break;
            }
            AlertPane resultPane = new AlertPane(AlertPaneType.OneButton);
            resultPane.setMessageText(resultText);
            
            EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
                switch (eventType) {
                    case Police:
                    case Pirate:
                        if (success) {
                            makeJourney(currentJourney);
                        }
                        break;
                    case Trader:
                        makeJourney(currentJourney);
                        break;
                }
                
                anchor.getChildren().remove(resultPane);
                
                playerTable = HyenasLoader.getInstance().getPlayerTable();
                try {
                    playerTable.updateLocation(currentJourney.getDestinationSolarSystem());
                } catch (SQLException ex) {
                    Logger.getLogger(MapUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            resultPane.getCloseButton().setOnAction(closeAction);
            playerInfoPane.updateInfo();
            anchor.getChildren().remove(eventPane);
            anchor.getChildren().add(resultPane);
        };
        eventPane.getCloseButton().setOnAction(cancelEvent);
        
        anchor.getChildren().add(eventPane);
        
        Galaxy.getInstance().setLocationsSet(true);
    }

    private boolean randomEventOccurred() {
        Random rand = new Random();
        int roll = rand.nextInt(5);
        RandomEventType eventType;
        
        if (roll == 1) {
            handleRandomEvent(RandomEventType.Pirate);
            return true;
        } else if (roll == 2) {
            handleRandomEvent(RandomEventType.Trader);
            return true;
        } else if (roll == 3) {
            handleRandomEvent(RandomEventType.Police);
            return true;
        }
        else if (roll == 4) {
            int randSysIndex = rand.nextInt(Galaxy.getInstance().getSolarSystems().size());
            SolarSystem randSys = (SolarSystem) Galaxy.getInstance().getSolarSystems().values().toArray()[randSysIndex];
            List<Planet> planets = randSys.getPlanets();
            int randPlanetIndex = rand.nextInt(planets.size());
            Planet randPlanet = planets.get(randPlanetIndex);
            
            
            AlertPane resultPane = new AlertPane(AlertPaneType.OneButton);
            // TODO: How to replace [ware] with the ware that was affected??
            resultPane.setMessageText("The [ware] on planet " + randPlanet.getPlanetName() + 
                    " in system " + randSys.getSystemName() + " has been affected.");
            EventHandler<ActionEvent> closeAction = (ActionEvent e1) -> {
                anchor.getChildren().remove(resultPane);
            };
            resultPane.getCloseButton().setOnAction(closeAction);
            
            return true;
        }
        return false;
    }
    
    private void makeJourney(Journey journey) {
        Player player = Player.getInstance();
        Ship ship = player.getShip();
        
        double startingFuel = ship.getFuel();
        ship.setFuel(startingFuel - journey.getDistance());
        
        SolarSystem destination = journey.getDestinationSolarSystem();
        player.setCurrentSystem(destination);
        player.setTradingPlanet(destination.getPlanets().get(0));

        journey.getStartingSystemButton().getStyleClass().remove("currentPlanet");
        journey.getDestinationSystemButton().getStyleClass().add("currentPlanet");
        currentSolarSystemButton = journey.getDestinationSystemButton();

        HyenasLoader.getInstance().goToSystemScreen();
    }
    
    private void travelToSystem(SolarSystem solarSystem, Button solarSystemButton) {
        Player player = Player.getInstance();
        SolarSystem currentSystem = player.getCurrentSystem();
        
        if (currentSystem == solarSystem) {
            // If the player wants to 'travel' to their current system, take them to the system view
            HyenasLoader.getInstance().goToSystemScreen();
        } else {
            double distance = getDjikstraDistance(currentSystem, solarSystem);
            if (distance == -1) {
                throw new RuntimeException("Unconnected node!!!");
            }

            currentJourney = new Journey(currentSystem, solarSystem, currentSolarSystemButton, solarSystemButton, distance);
            if (!randomEventOccurred()) {
                makeJourney(currentJourney);

                playerTable = HyenasLoader.getInstance().getPlayerTable();
                try {
                    playerTable.updateLocation(solarSystem);
                } catch (SQLException ex) {
                    Logger.getLogger(MapUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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
        }
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
