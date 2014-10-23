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
public class RandomEventPane extends AnchorPane {
    
    @FXML
    private Label descriptionLabel;

    @FXML
    private Label questionLabel;

    @FXML
    private Button actionButton;
    
    @FXML
    private Button cancelButton;
    
    private final int EVENT_PANE_WIDTH = 300;
    private final int EVENT_PANE_HEIGHT = 115;
    
    public RandomEventPane() {
        getStyleClass().add("infoPane");
        setPrefSize(EVENT_PANE_WIDTH, EVENT_PANE_HEIGHT);

        descriptionLabel = new Label();
        descriptionLabel.getStyleClass().add("infoPaneLabel");
        AnchorPane.setTopAnchor(descriptionLabel, 10.0);
        AnchorPane.setRightAnchor(descriptionLabel, 10.0);
        AnchorPane.setLeftAnchor(descriptionLabel, 10.0);

        questionLabel = new Label();
        questionLabel.getStyleClass().add("infoPaneLabel");
        AnchorPane.setTopAnchor(questionLabel, 40.0);
        AnchorPane.setRightAnchor(questionLabel, 10.0);
        AnchorPane.setLeftAnchor(questionLabel, 10.0);
        
        actionButton = new Button();
        actionButton.getStyleClass().add("infoPaneButton");
        AnchorPane.setBottomAnchor(actionButton, 10.0);
        AnchorPane.setRightAnchor(actionButton, 160.0);
        AnchorPane.setLeftAnchor(actionButton, 10.0);
        
        cancelButton = new Button();
        cancelButton.getStyleClass().add("infoPaneButton");
        AnchorPane.setBottomAnchor(cancelButton, 10.0);
        AnchorPane.setRightAnchor(cancelButton, 10.0);
        AnchorPane.setLeftAnchor(cancelButton, 160.0);

        getChildren().addAll(descriptionLabel, questionLabel, actionButton, cancelButton);
    }
    
    public void setupForRandomEvent(RandomEvent randomEvent) {
        descriptionLabel.setText(randomEvent.getDescription());
        questionLabel.setText(randomEvent.getQuestion());
        actionButton.setText(randomEvent.getActionButtonText());
        cancelButton.setText(randomEvent.getCancelButtonText());
    }
    
    public Button getActionButton() {
        return actionButton;
    }
    
    public Button getCancelButton() {
        return cancelButton;
    }
}
