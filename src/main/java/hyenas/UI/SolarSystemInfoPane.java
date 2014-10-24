/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.Models.SolarSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Alex
 */
public class SolarSystemInfoPane extends AnchorPane {

    @FXML
    private Label systemNameLabel;

    @FXML
    private Label numPlanetsLabel;

    @FXML
    private Button travelButton;

    public SolarSystemInfoPane() {
        getStyleClass().add("infoPane");

        systemNameLabel = new Label("System:");
        systemNameLabel.getStyleClass().add("infoPaneLabel");
        AnchorPane.setTopAnchor(systemNameLabel, 10.0);
        AnchorPane.setRightAnchor(systemNameLabel, 10.0);
        AnchorPane.setLeftAnchor(systemNameLabel, 10.0);

        numPlanetsLabel = new Label("Planets:");
        numPlanetsLabel.getStyleClass().add("infoPaneLabel");
        AnchorPane.setTopAnchor(numPlanetsLabel, 40.0);
        AnchorPane.setRightAnchor(numPlanetsLabel, 10.0);
        AnchorPane.setLeftAnchor(numPlanetsLabel, 10.0);

        travelButton = new Button("Travel");
        travelButton.getStyleClass().add("infoPaneButton");
        AnchorPane.setBottomAnchor(travelButton, 10.0);
        AnchorPane.setRightAnchor(travelButton, 10.0);
        AnchorPane.setLeftAnchor(travelButton, 10.0);

        getChildren().addAll(travelButton, systemNameLabel, numPlanetsLabel);
    }

    public void setupForSolarSystem(SolarSystem solarSystem) {
        systemNameLabel.setText("System: " + solarSystem.getSystemName());
        numPlanetsLabel.setText("Planets: " + solarSystem.getPlanets().size());
    }

    public Button getTravelButton() {
        return travelButton;
    }
}
