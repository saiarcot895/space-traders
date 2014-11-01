package hyenas.UI;

import javafx.scene.control.Button;

/**
 * The standard button used throughout the ui.
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
        REGULAR,
        SMALL,
        MEDIUM
    }
    
    /**
     * Default initializer for standard button.
     */
    public StandardButton() {
        this("");
    }
    
    /**
     * Initializes a standard button with text.
     * @param text the text to set
     */
    public StandardButton(String text) {
        this(text, StandardButtonType.REGULAR);
    }
    
    /**
     * Initializes a standard button with text and given type.
     * @param text the text to set
     * @param type the type of the button
     */
    public StandardButton(String text, StandardButtonType type) {
        super(text);
        getStyleClass().add("standard-button");
        switch (type) {
            case SMALL:
                setPrefWidth(SMALL_PREF_WIDTH);
                setPrefHeight(SMALL_PREF_HEIGHT);
                break;
            case MEDIUM:
                setPrefWidth(MEDIUM_PREF_WIDTH);
                setPrefHeight(MEDIUM_PREF_HEIGHT);
                break;
            case REGULAR:
            default:
                setPrefWidth(REGULAR_PREF_WIDTH);
                setPrefHeight(REGULAR_PREF_HEIGHT);
                break;
        }
        
    }
}
