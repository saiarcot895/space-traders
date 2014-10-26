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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Alex
 */
public class AlertPane extends BorderPane {
    
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
    private final int ALERT_PANE_HEIGHT = 135;
    private final double ALERT_PANE_BUTTON_WIDTH = 104.0;
    private final double ALERT_PANE_BUTTON_HEIGHT = 27.0;
    
    private final double PADDING = 10.0;
    
    public AlertPane(AlertPaneType type) {
        this.type = type;
        setPrefSize(ALERT_PANE_WIDTH, ALERT_PANE_HEIGHT);
        getStyleClass().add("alertPane");

        titleLabel = new Label();
        titleLabel.setWrapText(true);
        titleLabel.getStyleClass().add("alertPaneTitleLabel");
        titleLabel.setStyle("-fx-padding: 5 0 0 0;");
        BorderPane titlePane = new BorderPane();
        titlePane.setCenter(titleLabel);
        titlePane.setPrefHeight(10.0);
        setTop(titlePane);
        
        messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.getStyleClass().add("alertPaneMessageLabel");
        messageLabel.setPrefHeight(88.0);
        messageLabel.setStyle("-fx-padding: 0 10 0 10;");
        setCenter(messageLabel);
        
        if (type == AlertPaneType.OneButton) {
            cancelButton = new Button("Close");
            cancelButton.getStyleClass().add("standard-button");
            cancelButton.setPrefSize(ALERT_PANE_BUTTON_WIDTH, ALERT_PANE_BUTTON_HEIGHT);
            
            BorderPane buttonPane = new BorderPane();
            buttonPane.setCenter(cancelButton);
            buttonPane.setPrefHeight(35.0);
            setBottom(buttonPane);
        } else if (type == AlertPaneType.TwoButtons) {
            actionButton = new Button("Action");
            actionButton.getStyleClass().add("standard-button");
            actionButton.setPrefSize(ALERT_PANE_BUTTON_WIDTH, ALERT_PANE_BUTTON_HEIGHT);
            AnchorPane.setBottomAnchor(actionButton, 5.0);
            AnchorPane.setLeftAnchor(actionButton, 41.0);
            
            cancelButton = new Button("Close");
            cancelButton.getStyleClass().add("standard-button");
            cancelButton.setPrefSize(ALERT_PANE_BUTTON_WIDTH, ALERT_PANE_BUTTON_HEIGHT);
            AnchorPane.setBottomAnchor(cancelButton, 5.0);
            AnchorPane.setRightAnchor(cancelButton, 41.0);
            
            AnchorPane buttonPane = new AnchorPane();
            buttonPane.setPrefHeight(35.0);
            buttonPane.getChildren().addAll(actionButton, cancelButton);
            setBottom(buttonPane);
        }
        
        setLayoutX((UIHelper.getScreenSize().getWidth() / 2) - (getPrefWidth() / 2));
        setLayoutY((UIHelper.getScreenSize().getHeight() / 2) - (getPrefHeight() / 2));
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
