/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.Models.SolarSystem;
import java.awt.Point;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Alex
 */
public class SolarSystemButton extends Button {
    public static final double SYSTEM_UI_SIZE_FACTOR = 10.0;

    public void setupForMapUI(SolarSystem solarSystem) {
        setUpSystemImage(solarSystem, 1);
        Point coordinates = getCoordinates();
        solarSystem.setX((int) coordinates.getX());
        solarSystem.setY((int) coordinates.getY());
        setLayoutX(coordinates.getX());
        setLayoutY(coordinates.getY());
        setId(solarSystem.getSystemName());
        setMnemonicParsing(false);
        getStyleClass().add("planet");
    }

    public void setupForSystemUI(SolarSystem solarSystem) {
        setUpSystemImage(solarSystem, SYSTEM_UI_SIZE_FACTOR);
        setMnemonicParsing(false);
        getStyleClass().add("planet");
    }

    public void setUpSystemImage(SolarSystem solarSystem, double sizeFactor) {
        Image image = new Image("hyenas/images/Planet.png");
        ImageView planetImageView = new ImageView(image);
        planetImageView.setFitWidth(solarSystem.getSize() * sizeFactor);
        planetImageView.setPreserveRatio(true);
        setPrefWidth(solarSystem.getSize() * sizeFactor);
        setPrefHeight(solarSystem.getSize() * sizeFactor);
        setGraphic(planetImageView);

        String styleString = String.format("-fx-effect: innershadow(gaussian, %s, 15, 0, 0, 0)", solarSystem.getColorString());
        setStyle(styleString);
    }

    private Point getCoordinates() {
        Random rand = new Random();
        int x = rand.nextInt(UIHelper.GALAXY_SIZE);
        int y = rand.nextInt(UIHelper.GALAXY_SIZE);

//        TODO: make sure coordinates don't overlap
//        TODO: make sure no planet is half off-screen

        Point coordinates = new Point(x, y);
        return coordinates;
    }
}
