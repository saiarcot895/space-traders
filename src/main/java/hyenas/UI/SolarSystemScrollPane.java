package hyenas.UI;

import java.awt.Dimension;
import javafx.event.Event;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Scroll pane for displaying solar systems.
 * @author Alex
 */
public class SolarSystemScrollPane extends ScrollPane {

    private SolarSystemInfoPane infoPane;

    /**
     * Initializer for solar system scroll pane.
     * @author Alex
     */
    public SolarSystemScrollPane() {
        getStyleClass().add("scroll-pane");
        setPannable(true);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        Dimension screenSize = UIHelper.getScreenSize();
        setPrefSize(screenSize.getWidth(), screenSize.getHeight());

        setOnMousePressed((Event event) -> {
            if (infoPane != null) {
                ((Pane) getContent()).getChildren().remove(infoPane);
                infoPane = null;
            }
        });
    }
    
    /**
     * Sets the info pane for the scroll pane
     * @param infoPane the info pane
     */
    public void setInfoPane(SolarSystemInfoPane infoPane) {
        ((Pane) getContent()).getChildren().remove(this.infoPane);
        this.infoPane = infoPane;
    }

}
