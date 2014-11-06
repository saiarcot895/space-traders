package hyenas;

import hyenas.Models.ABPair;
import hyenas.Models.DijkstraHelper;
import hyenas.Models.Galaxy;
import hyenas.Models.Journey;
import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.RandomEvent;
import hyenas.Models.RandomEvent.RandomEventType;
import hyenas.Models.Ship;
import hyenas.Models.SolarSystem;
import hyenas.UI.PlayerInfoPane;
import hyenas.UI.AlertPane;
import hyenas.UI.AlertPane.AlertPaneType;
import hyenas.UI.SolarSystemButton;
import hyenas.UI.SolarSystemInfoPane;
import hyenas.UI.SolarSystemScrollPane;
import hyenas.UI.UIHelper;
import hyenas.database.PlanetTable;
import hyenas.database.PlayerTable;
import hyenas.database.SolarSystemTable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import javafx.scene.shape.Line;

/**
 * FXML Controller class for map of solar systems.
 *
 * @author Abhishek
 */
public class MapUIController implements Initializable {
    /**
     * The map UI controller main anchor pane.
     */
    @FXML
    private AnchorPane anchorPane;
    /**
     * The map UI controller current solar system button.
     */
    private Button currentSolarSystemButton;
    /**
     * The map UI controller scroll pane.
     */
    private SolarSystemScrollPane scrollPane = new SolarSystemScrollPane();
    /**
     * The map UI controller player info pane.
     */
    private PlayerInfoPane playerInfoPane;
    /**
     * The CSS style class for current planet.
     */
    private static final String CURRENT_PLANET_STYLE_CLASS = "currentPlanet";
    
    
    /**
     * The map UI controller current journey. For use in case player encounters
     * random event
     */
    private Journey currentJourney;
    /**
     * The visible range the player can travel.
     */
    private Circle travelRange;
    /**
     * The solar system database table.
     */
    private SolarSystemTable ssTable;
    /**
     * The player database table.
     */
    private PlayerTable playerTable;
    /**
     * The scroll pane padding. Prevents solar systems from being too close to
     * edge.
     */
    private static final double SCROLL_PANE_PADDING = 60.0;
    /**
     * The tint color for the travel range.
     */
    private static final String TINT_COLOR = "cyan";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane scrollContentPane = new Pane();
        scrollContentPane.setPrefSize(UIHelper.GALAXY_SIZE, UIHelper.GALAXY_SIZE);
        scrollContentPane.setStyle("-fx-background-color: transparent;");
        ssTable = HyenasLoader.getInstance().getConnectionManager()
                .getSolarSystemTable();
        
        Player player = Player.getInstance();
        Map<String, SolarSystem> solarSystems = Galaxy.getInstance().getSolarSystems();
        Set<String> solarSystemIDs = solarSystems.keySet();
        solarSystemIDs.stream().map((solarSystemID) -> {
                return solarSystems.get(solarSystemID);
            }).map((SolarSystem solarSystem) -> {
                    SolarSystemButton button = new SolarSystemButton();
                    button.setupForMapUI(solarSystem);
                    SolarSystem currentSystem = player.getCurrentSystem();
                    if (currentSystem == solarSystem) {
                        button.getStyleClass().add(CURRENT_PLANET_STYLE_CLASS);
                        currentSolarSystemButton = button;
                    }
            
                    return button;
                }).map((button) -> {
                        EventHandler<ActionEvent> event = (ActionEvent e) -> {
                            Button button1 = (Button) e.getSource();
                            String solarSystemID1 = button1.getId();
                            SolarSystem solarSystem1 = Galaxy.getInstance().getSolarSystemForName(solarSystemID1);

                            SolarSystemInfoPane systemInfoPane = new SolarSystemInfoPane();
                            systemInfoPane.setupForSolarSystem(solarSystem1);

                            EventHandler<ActionEvent> travelEvent = (ActionEvent e1) -> {
                                travelToSystem(solarSystem1, button1);
                            };
                            systemInfoPane.getTravelButton().setOnAction(travelEvent);
                            systemInfoPane.getTravelButton().setDisable(!player.canTravelToSystem(solarSystem1));

                            scrollPane.setInfoPane(systemInfoPane);
                            scrollContentPane.getChildren().addAll(systemInfoPane);
                        };
                        button.setOnAction(event);
                        return button;
                    }).forEach((button) -> {
                            scrollContentPane.getChildren().add(button);
                        });

        List<SolarSystem> solarSystemValues = new LinkedList<>(solarSystems.values());
        Map<SolarSystem, List<ABPair<SolarSystem, Double>>> distances =
                Galaxy.getInstance().getDistances();

        if (!Galaxy.getInstance().isLocationsSet()) {
            HyenasLoader.getInstance().getConnectionManager()
                    .beginTransaction();

            PlanetTable planetTable = HyenasLoader.getInstance()
                    .getConnectionManager().getPlanetTable();

            for (int i = 0; i < solarSystemValues.size(); i++) {
                SolarSystem ss = solarSystemValues.get(i);
                ssTable.addRow(ss, null);
                ss.getPlanets().stream().forEach((planet) -> {
                        planetTable.addRow(planet, ss);
                    });
            }
            
            HyenasLoader.getInstance().getConnectionManager().getPlayerTable()
                    .update(player, null);
            
            HyenasLoader.getInstance().getConnectionManager()
                    .commitTransaction();
        }
        
        if (distances.isEmpty()) {
            Random random = new Random();
            for (int i = 0; i < solarSystemValues.size(); i++) {
                SolarSystem solarSystem1 = solarSystemValues.get(i);
                for (int j = i; j < solarSystemValues.size(); j++) {
                    SolarSystem solarSystem2 = solarSystemValues.get(j);

                    double distance = DijkstraHelper.getDistance(solarSystem1, solarSystem2);
                    if (solarSystem1 != player.getCurrentSystem()
                            && solarSystem2 != player.getCurrentSystem()) {
                        if (distance >= 400) {
                            continue;
                        }

                        if (random.nextDouble() >= 0.35) {
                            continue;
                        }
                    } else {
                        if (distance >= 250) {
                            continue;
                        }
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
        }
        
        for (int i = 0; i < solarSystemValues.size(); i++) {
            SolarSystem solarSystem1 = solarSystemValues.get(i);
            double system1Size = solarSystem1.getSize();
            
            List<ABPair<SolarSystem, Double>> connections = distances.get(solarSystem1);
            
            if (connections == null) {
                continue;
            }
            
            for (int j = 0; j < connections.size(); j++) {
                SolarSystem solarSystem2 = distances.get(solarSystem1).get(j).getA();
                
                double system2Size = solarSystem2.getSize();
                Line connection = new Line(solarSystem1.getX() + system1Size,
                        solarSystem1.getY() + system1Size, solarSystem2.getX() + system2Size,
                        solarSystem2.getY() + system2Size);
                connection.setStroke(Color.web(TINT_COLOR, 1));
                scrollContentPane.getChildren().add(0, connection);
            }
        }

        double x = currentSolarSystemButton.getLayoutX();
        double y = currentSolarSystemButton.getLayoutY();

        SolarSystem currentSystem = player.getCurrentSystem();
        travelRange = new Circle(x, y, player.getShip().getFuel());
        travelRange.setFill(Color.web(TINT_COLOR, 0.1));
        travelRange.setDisable(true);
        scrollContentPane.getChildren().add(travelRange);
        
        AnchorPane.setTopAnchor(scrollPane, SCROLL_PANE_PADDING);
        AnchorPane.setBottomAnchor(scrollPane, SCROLL_PANE_PADDING);
        AnchorPane.setRightAnchor(scrollPane, SCROLL_PANE_PADDING);
        AnchorPane.setLeftAnchor(scrollPane, SCROLL_PANE_PADDING);
        scrollPane.setContent(scrollContentPane);
        playerInfoPane = new PlayerInfoPane();
        AnchorPane.setBottomAnchor(playerInfoPane, 0.0);
        AnchorPane.setLeftAnchor(playerInfoPane, 10.0);
        
        anchorPane.getChildren().addAll(scrollPane, playerInfoPane);
    }
    
    /**
     * Manages what happens when a random event occurs.
     * @param eventType the type of random event
     */
    private void handleRandomEvent(RandomEventType eventType) {
        scrollPane.setInfoPane(null); // Remove system info pane
        AlertPane eventPane = new AlertPane(AlertPaneType.TWOBUTTONS);
        RandomEvent event = new RandomEvent(eventType);
        eventPane.setTitleText(event.getDescription());
        eventPane.setMessageText(event.getQuestion());
        eventPane.getActionButton().setText(event.getActionButtonText());
        eventPane.getCloseButton().setText(event.getCancelButtonText());

        AlertPane resultPane = new AlertPane(AlertPaneType.ONEBUTTON);

        EventHandler<ActionEvent> actionEvent = (ActionEvent e1) -> {
            boolean success = event.performAction();
            if (eventType == RandomEventType.PIRATE) {
                SolarSystem solarSystem = currentJourney.getDestinationSolarSystem();
                if (!success) {
                    Ship ship = Player.getInstance().getShip();
                    // Deduct fuel for half the journey
                    ship.setFuel(ship.getFuel() - (currentJourney.getDistance() / 2));
                    solarSystem = currentJourney.getStartingSolarSystem();
                }
            }
            resultPane.setMessageText(event.getActionResultText());
            showResultAlertPane(eventPane, resultPane, success);
        };
        eventPane.getActionButton().setOnAction(actionEvent);
        
        EventHandler<ActionEvent> cancelEvent = (ActionEvent e1) -> {
            boolean success = event.performCancel();
            if (eventType == RandomEventType.PIRATE || eventType == RandomEventType.POLICE) {
                if (!success) {
                    Ship ship = Player.getInstance().getShip();
                    // Deduct fuel for half the journey
                    ship.setFuel(ship.getFuel() - (currentJourney.getDistance() / 2));
                }
            } else if (eventType == RandomEventType.TRADER) {
                makeJourney(currentJourney);
            }
            resultPane.setMessageText(event.getCancelResultText());
            showResultAlertPane(eventPane, resultPane, success);
        };
        eventPane.getCloseButton().setOnAction(cancelEvent);
        anchorPane.getChildren().add(eventPane);
        Galaxy.getInstance().setLocationsSet(true);
    }

    /**
     * Displays the random event result alert pane.
     * @param eventPane the random event pane
     * @param resultPane the result alert pane to display
     * @param success whether the random event succeeded
     */
    private void showResultAlertPane(AlertPane eventPane, AlertPane resultPane, boolean success) {
        EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
            if (success) {
                makeJourney(currentJourney);
            }
            anchorPane.getChildren().remove(resultPane);
            updateDatabase();
        };
        resultPane.getCloseButton().setOnAction(closeAction);
        playerInfoPane.updateInfo();
        anchorPane.getChildren().remove(eventPane);
        anchorPane.getChildren().add(resultPane);
    }
    
    /**
     * Randomly determines whether a random event occurred.
     * @return true if a random event occurred; false otherwise
     */
    private boolean randomEventOccurred() {
        Random rand = new Random();
        int roll = rand.nextInt(5);
        RandomEventType eventType;
        
        if (roll == 1) {
            handleRandomEvent(RandomEventType.PIRATE);
            return true;
        } else if (roll == 2) {
            handleRandomEvent(RandomEventType.TRADER);
            return true;
        } else if (roll == 3) {
            handleRandomEvent(RandomEventType.POLICE);
            return true;
        }
        else if (roll == 4) {
            int randSysIndex = rand.nextInt(Galaxy.getInstance().getSolarSystems().size());
            SolarSystem randSys = (SolarSystem) Galaxy.getInstance().getSolarSystems().values().toArray()[randSysIndex];
            List<Planet> planets = randSys.getPlanets();
            int randPlanetIndex = rand.nextInt(planets.size());
            Planet randPlanet = planets.get(randPlanetIndex);
            
            
            AlertPane resultPane = new AlertPane(AlertPaneType.ONEBUTTON);
            // TODO: How to replace [ware] with the ware that was affected??
            resultPane.setMessageText("The [ware] on planet " + randPlanet.getPlanetName()
                    + " in system " + randSys.getSystemName() + " has been affected.");
            EventHandler<ActionEvent> closeAction = (ActionEvent e1) -> {
                anchorPane.getChildren().remove(resultPane);
            };
            resultPane.getCloseButton().setOnAction(closeAction);
            
            return true;
        }
        return false;
    }

    /**
     * Updates the database.
     */
    private void updateDatabase() {
        Player player = Player.getInstance();
        HyenasLoader.getInstance().getConnectionManager()
                .getPlayerTable().update(player, null);
        HyenasLoader.getInstance().getConnectionManager().getShipTable()
                .update(player.getShip(), player);
    }
    
    /**
     * Completes a journey by moving the player, deducting fuel, and setting the
     * current system and planet.
     * @param journey the journey the player wishes to make
     */
    private void makeJourney(Journey journey) {
        Player player = Player.getInstance();
        Ship ship = player.getShip();
        
        double startingFuel = ship.getFuel();
        ship.setFuel(startingFuel - journey.getDistance());
        
        SolarSystem destination = journey.getDestinationSolarSystem();
        player.setCurrentSystem(destination);
        player.setCurrentPlanet(destination.getPlanets().get(0));

        journey.getStartingSystemButton().getStyleClass().remove(CURRENT_PLANET_STYLE_CLASS);
        journey.getDestinationSystemButton().getStyleClass().add(CURRENT_PLANET_STYLE_CLASS);
        currentSolarSystemButton = journey.getDestinationSystemButton();

        HyenasLoader.getInstance().goToSystemScreen();
    }
    
    /**
     * Handles a player traveling to a system and checks for random events.
     * @param solarSystem the system being traveled to
     * @param solarSystemButton the corresponding button of the system being
     * traveled to
     */
    private void travelToSystem(SolarSystem solarSystem, Button solarSystemButton) {
        Player player = Player.getInstance();
        SolarSystem currentSystem = player.getCurrentSystem();
        
        if (currentSystem == solarSystem) {
            // If the player wants to 'travel' to their current system, take them to the system view
            HyenasLoader.getInstance().goToSystemScreen();
        } else {
            double distance = DijkstraHelper.getDijkstraDistance(currentSystem, solarSystem);
            if (distance == -1) {
                throw new RuntimeException("Unconnected node!!!");
            }

            currentJourney = new Journey(currentSystem, solarSystem, currentSolarSystemButton, solarSystemButton, distance);
            if (!randomEventOccurred()) {
                makeJourney(currentJourney);

                playerTable = HyenasLoader.getInstance().getConnectionManager()
                        .getPlayerTable();
                playerTable.update(player, null);
                HyenasLoader.getInstance().getConnectionManager().getShipTable()
                        .update(player.getShip(), player);
            }
        }
    }

    /**
     * Go to the home screen.
     */
    public void goToHome() {
        Player.getInstance().setState(false);
        HyenasLoader.getInstance().goToHomeScreen();
    }
}
