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
    private Label planetTypeLabel;
    private Label techLevelLabel;
    private Label creditsLabel;
    private Label fuelLabel;
    private Label freeCargoLabel;
    private Label planetEventLabel;
    private Button buyButton;
    private Button sellButton;
    
    public MarketInfoPane() {
        getStyleClass().add("market-info-pane");
        setPrefWidth(300.0);
        setPrefHeight(300.0);
        setPadding(new Insets(10));
        
        VBox leftBox = new VBox();
        Label planetName = new Label("Planet:");
        planetName.getStyleClass().add("alertPaneTitleLabel");
        Label planetType = new Label("Type:");
        planetType.getStyleClass().add("alertPaneTitleLabel");
        Label techLevel = new Label("Tech Level:");
        techLevel.getStyleClass().add("alertPaneTitleLabel");
        Label planetEvent = new Label("Event:");
        planetEvent.getStyleClass().add("alertPaneTitleLabel");
        Label credits = new Label("Credits:");
        credits.getStyleClass().add("alertPaneTitleLabel");
        Label fuel = new Label("Fuel:");
        fuel.getStyleClass().add("alertPaneTitleLabel");
        Label freeCargo = new Label("Free Cargo:");
        freeCargo.getStyleClass().add("alertPaneTitleLabel");
        
        leftBox.getChildren().addAll(planetName, planetType, techLevel,
                planetEvent, credits, fuel, freeCargo);
        
        
        VBox rightBox = new VBox();
        planetNameLabel = new Label("[Name]");
        planetNameLabel.getStyleClass().add("alertPaneMessageLabel");
        planetTypeLabel = new Label("[Type]");
        planetTypeLabel.getStyleClass().add("alertPaneMessageLabel");
        techLevelLabel = new Label("[Tech Level]");
        techLevelLabel.getStyleClass().add("alertPaneMessageLabel");
        planetEventLabel = new Label("[Event]");
        planetEventLabel.getStyleClass().add("alertPaneMessageLabel");
        creditsLabel = new Label("-1");
        creditsLabel.getStyleClass().add("alertPaneMessageLabel");
        fuelLabel = new Label("-1");
        fuelLabel.getStyleClass().add("alertPaneMessageLabel");
        freeCargoLabel = new Label("-1");
        freeCargoLabel.getStyleClass().add("alertPaneMessageLabel");
        rightBox.getChildren().addAll(planetNameLabel, planetTypeLabel, 
                techLevelLabel, planetEventLabel, creditsLabel, fuelLabel,
                freeCargoLabel);
        BorderPane.setMargin(rightBox, new Insets(0,0,0,20));
        updateInfo();
        
        BorderPane bottomBox = new BorderPane();
        bottomBox.setPrefWidth(300.0);
        bottomBox.setPrefHeight(85.0);
        buyButton = new Button("Buy");
        buyButton.setDisable(true);
        buyButton.getStyleClass().add("standard-button");
        buyButton.setPrefHeight(37.0);
        buyButton.setPrefWidth(143.0);
        EventHandler<ActionEvent> resetAction = (ActionEvent e2) -> {
            // TODO
        };
        buyButton.setOnAction(resetAction);
        
        sellButton = new Button("Sell");
        sellButton.setDisable(true);
        sellButton.getStyleClass().add("standard-button");
        sellButton.setPrefHeight(37.0);
        sellButton.setPrefWidth(143.0);
        EventHandler<ActionEvent> confirmAction = (ActionEvent e2) -> {
            // TODO
        };
        buyButton.setOnAction(confirmAction);
        
        BorderPane resetPane = new BorderPane();
        resetPane.setCenter(buyButton);
        BorderPane confirmPane = new BorderPane();
        confirmPane.setCenter(sellButton);
        bottomBox.setTop(resetPane);
        bottomBox.setBottom(confirmPane);
        
        
        setLeft(leftBox);
        setCenter(rightBox);
        setBottom(bottomBox);
        
    }
    
    public Button getBuyButton() {
        return buyButton;
    }
    
    public Button getSellButton() {
        return sellButton;
    }
    
    public void updateInfo() {
        Player player = Player.getInstance();
        Planet planet = player.getTradingPlanet();
        Ship ship = player.getShip();
        
        planetNameLabel.setText(planet.getPlanetName());
        planetTypeLabel.setText(planet.getPlanetTypeString());
        techLevelLabel.setText("" + planet.techLevelString() + " (" + 
                planet.getTechLevel() + ")");
        planetEventLabel.setText(planet.getPlanetEventString());
        creditsLabel.setText("" + player.getCredits());
        fuelLabel.setText(String.format("%.0f", ship.getFuel()) + "/" +
                String.format("%.0f", ship.getMaxFuel()));
        freeCargoLabel.setText("" + ship.getFreeCargo());
    }
}
