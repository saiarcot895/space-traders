package hyenas.UI;

import hyenas.Models.SolarSystem;
import hyenas.UI.AlertPane.AlertPaneMessageLabel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Displays info about solar system.
 * @author Alex
 */
public class SolarSystemInfoPane extends AnchorPane {
    /**
     * The solar system info pane's system name label.
     */
    private Label systemNameLabel;
    /**
     * The solar system info pane's planet number label.
     */
    private Label numPlanetsLabel;
    /**
     * The solar system info pane's travel button.
     */
    private Button travelButton;

    /**
     * Initializer for solar system Pane.
     */
    public SolarSystemInfoPane() {
        getStyleClass().add("alertPane");

        systemNameLabel = new AlertPaneMessageLabel("System:");
        AnchorPane.setTopAnchor(systemNameLabel, 10.0);
        AnchorPane.setRightAnchor(systemNameLabel, 10.0);
        AnchorPane.setLeftAnchor(systemNameLabel, 10.0);

        numPlanetsLabel = new AlertPaneMessageLabel("Planets:");
        AnchorPane.setTopAnchor(numPlanetsLabel, 40.0);
        AnchorPane.setRightAnchor(numPlanetsLabel, 10.0);
        AnchorPane.setLeftAnchor(numPlanetsLabel, 10.0);

        travelButton = new StandardButton("Travel");
        AnchorPane.setBottomAnchor(travelButton, 10.0);
        AnchorPane.setRightAnchor(travelButton, 10.0);
        AnchorPane.setLeftAnchor(travelButton, 10.0);

        getChildren().addAll(travelButton, systemNameLabel, numPlanetsLabel);
    }
    
    /**
     * Sets up info pane for a given solar system.
     * @param solarSystem the solar system
     */
    public void setupForSolarSystem(SolarSystem solarSystem) {
        systemNameLabel.setText("System: " + solarSystem.getSystemName());
        numPlanetsLabel.setText("Planets: " + solarSystem.getPlanets().size());
    }
    
    /**
     * Get the travel button.
     * @return the travel button
     */
    public Button getTravelButton() {
        return travelButton;
    }
}
