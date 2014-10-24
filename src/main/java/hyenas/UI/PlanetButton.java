/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.Models.Planet;
import java.awt.Point;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Brian
 */
public class PlanetButton extends Button {

    public void setupForPlanet(Planet planet) {
        Image image = new Image("hyenas/images/Planet.png");
        ImageView planetImageView = new ImageView(image);
        planetImageView.setFitWidth(planet.getSize());
        planetImageView.setFitHeight(planet.getSize());
        planetImageView.setPreserveRatio(true);
        setGraphic(planetImageView);
        setId(planet.getPlanetName());
        setMnemonicParsing(false);
        setPrefWidth(planet.getSize());
        setPrefHeight(planet.getSize());

        String styleString = String.format("-fx-effect: innershadow(gaussian, %s, 15, 0, 0, 0)", planet.getColorString());
        setStyle(styleString);
        getStyleClass().add("planet");
    }

    private Point getCoordinates() {
        Random rand = new Random();
        int x = rand.nextInt(1160);
        int y = rand.nextInt(680);

//        TODO: make sure coordinates don't overlap
//        TODO: make sure no planet is half off-screen

        Point coordinates = new Point(x, y);
        return coordinates;
    }
}
