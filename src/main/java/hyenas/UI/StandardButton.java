package hyenas.UI;

import javafx.scene.control.Button;

/**
 * The standard button used throughout the ui
 * @author Alex
 */
public class StandardButton extends Button {
    private final double REGULAR_PREF_WIDTH = 143.0;
    private final double REGULAR_PREF_HEIGHT = 37.0;
    
    private final double MEDIUM_PREF_WIDTH = 120.0;
    private final double MEDIUM_PREF_HEIGHT = 31.0;
    
    private final double SMALL_PREF_WIDTH = 104.0;
    private final double SMALL_PREF_HEIGHT = 27.0;
    
    
    public enum StandardButtonType {
        Regular,
        Small,
        Medium
    }
    
    /**
     * Initializes a standard button
     */
    public StandardButton() {
        this("");
    }
    
    public StandardButton(String text) {
        this(text, StandardButtonType.Regular);
    }
    
    public StandardButton(String text, StandardButtonType type) {
        super(text);
        getStyleClass().add("standard-button");
        switch (type) {
            case Small:
                setPrefWidth(SMALL_PREF_WIDTH);
                setPrefHeight(SMALL_PREF_HEIGHT);
                break;
            case Medium:
                setPrefWidth(MEDIUM_PREF_WIDTH);
                setPrefHeight(MEDIUM_PREF_HEIGHT);
                break;
            case Regular:
            default:
                setPrefWidth(REGULAR_PREF_WIDTH);
                setPrefHeight(REGULAR_PREF_HEIGHT);
                break;
        }
        
    }
}
