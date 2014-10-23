/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.Models.RandomEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Alex
 */
public class RandomEventResultPane extends AnchorPane {
    @FXML
    private Label resultLabel;
    
    @FXML
    private Button closeButton;
    
    private final int EVENT_RESULT_PANE_WIDTH = 300;
    private final int EVENT_RESULT_PANE_HEIGHT = 130;
    
    public RandomEventResultPane() {
        getStyleClass().add("infoPane");
        setPrefSize(EVENT_RESULT_PANE_WIDTH, EVENT_RESULT_PANE_HEIGHT);

        resultLabel = new Label();
        resultLabel.setWrapText(true);
        resultLabel.getStyleClass().add("infoPaneLabel");
        AnchorPane.setTopAnchor(resultLabel, 10.0);
        AnchorPane.setRightAnchor(resultLabel, 10.0);
        AnchorPane.setLeftAnchor(resultLabel, 10.0);
        
        closeButton = new Button("Close");
        closeButton.getStyleClass().add("infoPaneButton");
        AnchorPane.setBottomAnchor(closeButton, 10.0);
        AnchorPane.setRightAnchor(closeButton, 10.0);
        AnchorPane.setLeftAnchor(closeButton, 10.0);

        getChildren().addAll(resultLabel, closeButton);
    }
    
    public void setResultLabelText(String text) {
        resultLabel.setText(text);
    }
    
    public Button getCloseButton() {
        return closeButton;
    }
}
