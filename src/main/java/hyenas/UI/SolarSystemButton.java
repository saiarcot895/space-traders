/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.HyenasLoader;
import hyenas.SolarSystem;
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
 * @author Alex
 */
public class SolarSystemButton extends Button {
    public void setupForSolarSystem(SolarSystem solarSystem) {
        Image image = new Image("Planet.png");
        ImageView planetImageView = new ImageView(image);
        planetImageView.setFitWidth(solarSystem.getSize());
        planetImageView.setPreserveRatio(true);
        setGraphic(planetImageView);

        Point coordinates = getCoordinates();
        solarSystem.setX((int)coordinates.getX());
        solarSystem.setY((int)coordinates.getY());
        setLayoutX(coordinates.getX());
        setLayoutY(coordinates.getY());
        setId(solarSystem.getSystemName());
        setMnemonicParsing(false);
        
        String styleString = String.format("-fx-effect: innershadow(gaussian, %s, 15, 0, 0, 0)", randomColorString());
        setStyle(styleString);
        getStyleClass().add("planet");
    }
    
    private String randomColorString() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return String.format("rgb(%d, %d, %d, 1)", r, g, b);
    }
    
    private Point getCoordinates() {
        Dimension screenSize = UIHelper.getScreenSize();
        Random rand = new Random();

        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();

        int x = rand.nextInt(screenWidth);
        int y = rand.nextInt(screenHeight);

//        TODO: make sure coordinates don't overlap
//        TODO: make sure no planet is half off-screen
        
        Point coordinates = new Point(x, y);
        return coordinates;
    }
}
