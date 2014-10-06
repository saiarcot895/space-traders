/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.HyenasLoader;
import hyenas.Planet;
import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Brian
 */
public class PlanetButton extends Button {
    public void setupForPlanet(Planet planet) {
        Image image = new Image("hyenas/images/Planet.png");
        ImageView planetImageView = new ImageView(image);
        planetImageView.setFitWidth(planet.getSize());
        planetImageView.setPreserveRatio(true);
        setGraphic(planetImageView);

        Point coordinates = getCoordinates();
        setLayoutX(coordinates.getX());
        setLayoutY(coordinates.getY());
        setId(planet.getPlanetName());
        setMnemonicParsing(false);
        
        String styleString = String.format("-fx-effect: innershadow(gaussian, %s, 15, 0, 0, 0)", planet.getColorString());
        setStyle(styleString);
        getStyleClass().add("planet");
    }
    
    private Point getCoordinates() {
        Random rand = new Random();
        int x = rand.nextInt(UIHelper.SYSTEM_SIZE);
        int y = rand.nextInt(UIHelper.SYSTEM_SIZE);

//        TODO: make sure coordinates don't overlap
//        TODO: make sure no planet is half off-screen
        
        Point coordinates = new Point(x, y);
        return coordinates;
    }
}
