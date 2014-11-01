package hyenas.UI;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Use for displaying hover text over a UI element.
 * @author Alex
 */
public class HoverPane extends AnchorPane {
    private Label label;
    private final double PADDING = 3.0;
    private final double WIDTH = 110.0;
    private final double HEIGHT = 30.0;
    
    /**
     * Initializes a HoverPane.
     */
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
    
    /**
     * Sets the text of the hover pane.
     * @param text the text to set
     */
    public void setLabelText(String text) {
        label.setText(text);
    }
}
