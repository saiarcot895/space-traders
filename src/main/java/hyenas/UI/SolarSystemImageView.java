/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.Models.SolarSystem;
import static hyenas.UI.SolarSystemButton.SYSTEM_UI_SIZE_FACTOR;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Alex
 */
public class SolarSystemImageView extends ImageView {
    public void setupForSystemUI(SolarSystem solarSystem) {
        getStyleClass().add("planet");
        
        setImage(new Image("hyenas/images/Planet.png"));
        setFitWidth(solarSystem.getSize() * SYSTEM_UI_SIZE_FACTOR);
        setFitHeight(solarSystem.getSize() * SYSTEM_UI_SIZE_FACTOR);
        setPreserveRatio(true);
        
        String styleString = String.format("-fx-effect: innershadow(gaussian, %s, 15, 0, 0, 0)", solarSystem.getColorString());
        setStyle(styleString);
    }
    
}
