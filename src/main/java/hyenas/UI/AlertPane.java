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
public class AlertPane extends AnchorPane {
    
    @FXML
    private Label titleLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private Button actionButton;
    
    @FXML
    private Button cancelButton;
    
    private AlertPaneType type;
    
    private final int ALERT_PANE_WIDTH = 300;
    private final int ALERT_PANE_HEIGHT = 130;
    
    private final double PADDING = 10.0;
    
    public AlertPane(AlertPaneType type) {
        this.type = type;
        setPrefSize(ALERT_PANE_WIDTH, ALERT_PANE_HEIGHT);
        getStyleClass().add("alertPane");

        titleLabel = new Label();
        titleLabel.setWrapText(true);
        titleLabel.getStyleClass().add("alertPaneTitleLabel");
        AnchorPane.setTopAnchor(titleLabel, PADDING);
        AnchorPane.setRightAnchor(titleLabel, PADDING);
        AnchorPane.setLeftAnchor(titleLabel, PADDING);
        
        messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.getStyleClass().add("alertPaneMessageLabel");
        AnchorPane.setTopAnchor(messageLabel, PADDING);
        AnchorPane.setRightAnchor(messageLabel, PADDING);
        AnchorPane.setLeftAnchor(messageLabel, PADDING);
        
        if (type == AlertPaneType.OneButton) {
            cancelButton = new Button("Close");
            cancelButton.getStyleClass().add("alertPaneButton");
            AnchorPane.setBottomAnchor(cancelButton, PADDING);
            AnchorPane.setRightAnchor(cancelButton, PADDING);
            AnchorPane.setLeftAnchor(cancelButton, PADDING);
        } else if (type == AlertPaneType.TwoButtons) {
            actionButton = new Button();
            actionButton.getStyleClass().add("alertPaneButton");
            AnchorPane.setBottomAnchor(actionButton, PADDING);
            AnchorPane.setRightAnchor(actionButton, 160.0);
            AnchorPane.setLeftAnchor(actionButton, PADDING);
            getChildren().add(actionButton);
            
            cancelButton = new Button("Close");
            cancelButton.getStyleClass().add("alertPaneButton");
            AnchorPane.setBottomAnchor(cancelButton, PADDING);
            AnchorPane.setRightAnchor(cancelButton, PADDING);
            AnchorPane.setLeftAnchor(cancelButton, 160.0);
        }
        
        setLayoutX((UIHelper.getScreenSize().getWidth() / 2) - (getPrefWidth() / 2));
        setLayoutY((UIHelper.getScreenSize().getHeight() / 2) - (getPrefHeight() / 2));
        
        getChildren().addAll(titleLabel, messageLabel, cancelButton);
    }
    
    public void setTitleText(String text) {
        titleLabel.setText(text);
        // Move the message label down to make room for title
        if (text.length() > 0) {
            AnchorPane.setTopAnchor(messageLabel, 40.0);
        } else {
            AnchorPane.setTopAnchor(messageLabel, PADDING);
        }
    }
    
    public void setMessageText(String text) {
        messageLabel.setText(text);
    }
    
    public Button getActionButton() {
        return actionButton;
    }
    
    public Button getCloseButton() {
        return cancelButton;
    }
}
