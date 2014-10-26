/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.UI;

import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.Ship;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alex
 */
public class MarketInfoPane extends BorderPane {
    
    private Label planetNameLabel;
    private Label techLevelLabel;
    private Label creditsLabel;
    private Label fuelLabel;
    private Label freeCargoLabel;

    @FXML
    private Button travelButton;
    
    public MarketInfoPane() {
        getStyleClass().add("market-info-pane");
        setPrefWidth(300.0);
        setPrefHeight(300.0);
        setPadding(new Insets(10));
        
        VBox leftBox = new VBox();
        Label planetName = new Label("Planet:");
        planetName.getStyleClass().add("alertPaneTitleLabel");
        Label techLevel = new Label("Tech Level:");
        techLevel.getStyleClass().add("alertPaneTitleLabel");
        Label credits = new Label("Credits:");
        credits.getStyleClass().add("alertPaneTitleLabel");
        Label fuel = new Label("Fuel:");
        fuel.getStyleClass().add("alertPaneTitleLabel");
        Label freeCargo = new Label("Free Cargo:");
        freeCargo.getStyleClass().add("alertPaneTitleLabel");
        
        leftBox.getChildren().addAll(planetName, techLevel, credits, fuel, freeCargo);
        
        
        Player player = Player.getInstance();
        Planet planet = player.getTradingPlanet();
        Ship ship = player.getShip();
        
        VBox rightBox = new VBox();
        planetNameLabel = new Label(planet.getPlanetName());
        planetNameLabel.getStyleClass().add("alertPaneMessageLabel");
        techLevelLabel = new Label("" + planet.getTechLevel());
        techLevelLabel.getStyleClass().add("alertPaneMessageLabel");
        creditsLabel = new Label("" + player.getCredits());
        creditsLabel.getStyleClass().add("alertPaneMessageLabel");
        fuelLabel = new Label(String.format("%.0f", ship.getFuel()));
        fuelLabel.getStyleClass().add("alertPaneMessageLabel");
        freeCargoLabel = new Label("" + ship.getFreeCargo());
        freeCargoLabel.getStyleClass().add("alertPaneMessageLabel");
        rightBox.getChildren().addAll(planetNameLabel, techLevelLabel, creditsLabel, fuelLabel, freeCargoLabel);
        BorderPane.setMargin(rightBox, new Insets(0,0,0,20));
        
        
        BorderPane bottomBox = new BorderPane();
        bottomBox.setPrefWidth(300.0);
        bottomBox.setPrefHeight(85.0);
        Button resetButton = new Button("Reset");
        resetButton.getStyleClass().add("standard-button");
        resetButton.setPrefHeight(37.0);
        resetButton.setPrefWidth(143.0);
        EventHandler<ActionEvent> resetAction = (ActionEvent e2) -> {
            // TODO
        };
        resetButton.setOnAction(resetAction);
        
        Button confirmButton = new Button("Confirm");
        confirmButton.getStyleClass().add("standard-button");
        confirmButton.setPrefHeight(37.0);
        confirmButton.setPrefWidth(143.0);
        EventHandler<ActionEvent> confirmAction = (ActionEvent e2) -> {
            // TODO
        };
        resetButton.setOnAction(confirmAction);
        
        BorderPane resetPane = new BorderPane();
        resetPane.setCenter(resetButton);
        BorderPane confirmPane = new BorderPane();
        confirmPane.setCenter(confirmButton);
        bottomBox.setTop(resetPane);
        bottomBox.setBottom(confirmPane);
        
        
        setLeft(leftBox);
        setCenter(rightBox);
        setBottom(bottomBox);
    }
}
