/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import java.awt.Dimension;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Alex
 */
public class SolarSystemScrollPane extends ScrollPane {
    
    @FXML
    private SolarSystemInfoPane infoPane;
    
    public SolarSystemScrollPane() {
        getStyleClass().add("scroll-pane");
        setPannable(true);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        AnchorPane.setTopAnchor(this, 60.0);
        AnchorPane.setBottomAnchor(this, 60.0);
        AnchorPane.setRightAnchor(this, 60.0);
        AnchorPane.setLeftAnchor(this, 60.0);
        
        setOnMousePressed(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (infoPane != null) {
                    ((Pane)getContent()).getChildren().remove(infoPane);
                    infoPane = null;
                }
            }
        });
    }
    
    public void setInfoPane(SolarSystemInfoPane infoPane) {
        ((Pane)getContent()).getChildren().remove(this.infoPane);
        this.infoPane = infoPane;
    }
    
}
