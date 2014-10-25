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
public class HoverPane extends AnchorPane {
    private Label label;
    private final double PADDING = 3.0;
    private final double WIDTH = 110.0;
    private final double HEIGHT = 30.0;
    
    public HoverPane() {
        getStyleClass().add("alertPane");
        setPrefSize(WIDTH, HEIGHT);
        
        label = new Label();
        label.getStyleClass().add("hoverPaneLabel");
        AnchorPane.setTopAnchor(label, PADDING);
        AnchorPane.setBottomAnchor(label, PADDING);
        AnchorPane.setRightAnchor(label, PADDING);
        AnchorPane.setLeftAnchor(label, PADDING);
        getChildren().add(label);
    }
    
    public void setLabelText(String name) {
        label.setText(name);
    }
}
