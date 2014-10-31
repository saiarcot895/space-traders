package hyenas.UI;

import hyenas.UI.StandardButton.StandardButtonType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Alert Panes, for use when displaying alerts/messages to user
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
    private Button closeButton;
    
    private AlertPaneType type;
    
    private final int ALERT_PANE_WIDTH = 300;
    private final int ALERT_PANE_HEIGHT = 135;
    
    private final double PADDING = 10.0;
    
    /**
     * A AlertPaneType, used to distinguish between alert pane types
     */
    public enum AlertPaneType {
        OneButton,
        TwoButtons,
        Loading
    }
    
    /**
     * Title label for Alert Pane
     */
    public static class AlertPaneTitleLabel extends Label {
        /**
         * Initializes an AlertPaneTitleLabel
         */
        public AlertPaneTitleLabel() {
            this("");
        }
        
        /**
         * Initializes an AlertPaneTitleLabel
         * @param text, the default text to set
         */
        public AlertPaneTitleLabel(String text) {
            super(text);
            getStyleClass().add("alertPaneTitleLabel");
        }
    }
    
    /**
     * Message label for Alert Pane
     */
    public static class AlertPaneMessageLabel extends Label {
        /**
         * Initializes an AlertPaneMessageLabel
         */
        public AlertPaneMessageLabel() {
            this("");
        }
        
        /**
         * Initializes an AlertPaneMessageLabel
         * @param text, the default text to set
         */
        public AlertPaneMessageLabel(String text) {
            super(text);
            getStyleClass().add("alertPaneMessageLabel");
        }
    }
    
    /**
     * Initializes an AlertPane and creates required label/button elements
     * @param type, the alert pane type
     */
    public AlertPane(AlertPaneType type) {
        this.type = type;
        setPrefSize(ALERT_PANE_WIDTH, ALERT_PANE_HEIGHT);
        getStyleClass().add("alertPane");

        titleLabel = new AlertPaneTitleLabel();
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-padding: 5 0 0 0;");
        BorderPane titlePane = new BorderPane();
        titlePane.setCenter(titleLabel);
        titlePane.setPrefHeight(10.0);
        setTop(titlePane);
        
        messageLabel = new AlertPaneMessageLabel();
        messageLabel.setWrapText(true);
        messageLabel.setPrefHeight(88.0);
        messageLabel.setStyle("-fx-padding: 0 10 0 10;");
        setCenter(messageLabel);
        
        if (type == AlertPaneType.OneButton) {
            closeButton = new StandardButton("Close", StandardButtonType.Small);
            
            BorderPane buttonPane = new BorderPane();
            buttonPane.setCenter(closeButton);
            buttonPane.setPrefHeight(35.0);
            setBottom(buttonPane);
        } else if (type == AlertPaneType.TwoButtons) {
            actionButton = new StandardButton("Action", StandardButtonType.Small);
            AnchorPane.setBottomAnchor(actionButton, 5.0);
            AnchorPane.setLeftAnchor(actionButton, 41.0);
            
            closeButton = new StandardButton("Close", StandardButtonType.Small);
            AnchorPane.setBottomAnchor(closeButton, 5.0);
            AnchorPane.setRightAnchor(closeButton, 41.0);
            
            AnchorPane buttonPane = new AnchorPane();
            buttonPane.setPrefHeight(35.0);
            buttonPane.getChildren().addAll(actionButton, closeButton);
            setBottom(buttonPane);
        } else if (type == AlertPaneType.Loading) {
            BorderPane progressPane = new BorderPane();
            progressPane.setPrefHeight(50.0);
            ProgressBar progressBar = new ProgressBar();
            progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
            progressBar.setPrefWidth(150.0);
            progressPane.setCenter(progressBar);
            setBottom(progressPane);
        }
        
        setLayoutX((UIHelper.getScreenSize().getWidth() / 2) - (getPrefWidth() / 2));
        setLayoutY((UIHelper.getScreenSize().getHeight() / 2) - (getPrefHeight() / 2));
    }
    
    /**
     * Set the alert pane's title text
     * @param text, the title text
     */
    public void setTitleText(String text) {
        titleLabel.setText(text);
    }
    
    /**
     * Set the alert pane's message text
     * @param text, the message text
     */
    public void setMessageText(String text) {
        messageLabel.setText(text);
        
        // Move the message label down to make room for title
        if (titleLabel.getText().length() == 0) {
            setCenter(null);
            setTop(messageLabel);
        }
    }
    
    /**
     * Get the alert pane's action button
     * @param actionButton, the action button
     */
    public Button getActionButton() {
        return actionButton;
    }
    
    /**
     * Get the alert pane's close button
     * @param closeButton, the close button
     */
    public Button getCloseButton() {
        return closeButton;
    }
}
