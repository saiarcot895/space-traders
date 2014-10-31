package hyenas.UI;

import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.Ship;
import hyenas.UI.AlertPane.AlertPaneMessageLabel;
import hyenas.UI.AlertPane.AlertPaneTitleLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * For use with Marketplace. Displays planet and player info
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
    
    /**
     * Initializes a MarketInfoPane, sets up ui elements
     */
    public MarketInfoPane() {
        getStyleClass().add("market-info-pane");
        setPrefWidth(300.0);
        setPrefHeight(300.0);
        setPadding(new Insets(10));
        
        VBox leftBox = new VBox();
        Label planetName = new AlertPaneTitleLabel("Planet:");
        Label planetType = new AlertPaneTitleLabel("Type:");
        Label techLevel = new AlertPaneTitleLabel("Tech Level:");
        Label planetEvent = new AlertPaneTitleLabel("Event:");
        Label credits = new AlertPaneTitleLabel("Credits:");
        Label fuel = new AlertPaneTitleLabel("Fuel:");
        Label freeCargo = new AlertPaneTitleLabel("Free Cargo:");
        
        leftBox.getChildren().addAll(planetName, planetType, techLevel,
                planetEvent, credits, fuel, freeCargo);
        
        
        VBox rightBox = new VBox();
        planetNameLabel = new AlertPaneMessageLabel("[Name]");
        planetTypeLabel = new AlertPaneMessageLabel("[Type]");
        techLevelLabel = new AlertPaneMessageLabel("[Tech Level]");
        planetEventLabel = new AlertPaneMessageLabel("[Event]");
        creditsLabel = new AlertPaneMessageLabel("-1");
        fuelLabel = new AlertPaneMessageLabel("-1");
        freeCargoLabel = new AlertPaneMessageLabel("-1");
        rightBox.getChildren().addAll(planetNameLabel, planetTypeLabel, 
                techLevelLabel, planetEventLabel, creditsLabel, fuelLabel,
                freeCargoLabel);
        BorderPane.setMargin(rightBox, new Insets(0,0,0,20));
        updateInfo();
        
        BorderPane bottomBox = new BorderPane();
        bottomBox.setPrefWidth(300.0);
        bottomBox.setPrefHeight(85.0);
        buyButton = new StandardButton("Buy");
        buyButton.setDisable(true);
        
        sellButton = new StandardButton("Sell");
        sellButton.setDisable(true);
        
        BorderPane buyPane = new BorderPane();
        buyPane.setCenter(buyButton);
        BorderPane sellPane = new BorderPane();
        sellPane.setCenter(sellButton);
        bottomBox.setTop(buyPane);
        bottomBox.setBottom(sellPane);
        
        
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
        techLevelLabel.setText("" + planet.techLevelString());
        planetEventLabel.setText(planet.getPlanetEventString());
        creditsLabel.setText("" + player.getCredits());
        fuelLabel.setText(String.format("%.0f", ship.getFuel()) + "/" +
                String.format("%.0f", ship.getMaxFuel()));
        freeCargoLabel.setText("" + ship.getFreeCargo());
    }
}
