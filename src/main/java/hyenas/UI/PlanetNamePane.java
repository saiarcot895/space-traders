/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Alex
 */
public class PlanetNamePane extends AnchorPane {
    private Label planetNameLabel;
    private final double PADDING = 3.0;
    
    public PlanetNamePane() {
        getStyleClass().add("alertPane");
        setPrefSize(100, 30);
        
        planetNameLabel = new Label();
        planetNameLabel.getStyleClass().add("planetNamePaneLabel");
        AnchorPane.setTopAnchor(planetNameLabel, PADDING);
        AnchorPane.setBottomAnchor(planetNameLabel, PADDING);
        AnchorPane.setRightAnchor(planetNameLabel, PADDING);
        AnchorPane.setLeftAnchor(planetNameLabel, PADDING);
        getChildren().add(planetNameLabel);
    }
    
    public void setPlanetName(String name) {
        planetNameLabel.setText(name);
    }
}
