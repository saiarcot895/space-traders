package hyenas.UI;

import hyenas.Models.Player;
import hyenas.Models.Ship;
import hyenas.UI.AlertPane.AlertPaneMessageLabel;
import hyenas.UI.AlertPane.AlertPaneTitleLabel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * For use with Shipyard. Displays ship and player info
 * @author Alex
 */
public class ShipInfoPane extends BorderPane {
    private Label shipNameLabel;
    private Label shipCargoLabel;
    private Label shipFuelLabel;
    private Label shipWeaponsLabel;
    private Label shipShieldsLabel;
    private Label shipGadgetsLabel;
    private Label shipCrewLabel;
    private Label creditsLabel;
    private Button buyButton;
    private Button sellButton;
    
    /**
     * Initializer for ship info pane
     */
    public ShipInfoPane() {
        getStyleClass().add("market-info-pane");
        setPrefWidth(250.0);
        setPadding(new Insets(10));
        
        VBox leftBox = new VBox();
        Label shipName = new AlertPaneTitleLabel("Ship:");
        Label shipCargo = new AlertPaneTitleLabel("Cargo:");
        Label shipFuel = new AlertPaneTitleLabel("Fuel:");
        Label shipWeapons = new AlertPaneTitleLabel("Weapons:");
        Label shipShields = new AlertPaneTitleLabel("Shields:");
        Label shipGadgets = new AlertPaneTitleLabel("Gadgets:");
        Label shipCrew = new AlertPaneTitleLabel("Crew:");
        Label credits = new AlertPaneTitleLabel("Credits:");
        
        leftBox.getChildren().addAll(shipName, shipCargo, shipFuel, shipWeapons,
                shipShields, shipGadgets, shipCrew, credits);
        
        VBox rightBox = new VBox();
        shipNameLabel = new AlertPaneMessageLabel("[Name]");
        shipCargoLabel = new AlertPaneMessageLabel("[0/0]");
        shipFuelLabel = new AlertPaneMessageLabel("[0/0]");
        shipWeaponsLabel = new AlertPaneMessageLabel("[0/0]");
        shipShieldsLabel = new AlertPaneMessageLabel("[0/0]");
        shipGadgetsLabel = new AlertPaneMessageLabel("[0/0]");
        shipCrewLabel = new AlertPaneMessageLabel("[0/0]");
        creditsLabel = new AlertPaneMessageLabel("[0]");
        
        rightBox.getChildren().addAll(shipNameLabel, shipCargoLabel,
                shipFuelLabel, shipWeaponsLabel, shipShieldsLabel, 
                shipGadgetsLabel, shipCrewLabel, creditsLabel);
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
    
    /**
     * Get the buy button
     * @return buyButton, the buy button
     */
    public Button getBuyButton() {
        return buyButton;
    }
    
    /**
     * Get the sell button
     * @return sellButton, the sell button
     */
    public Button getSellButton() {
        return sellButton;
    }
    
    /**
     * Updates the info in the pane
     */
    public void updateInfo() {
        Player player = Player.getInstance();
        Ship ship = player.getShip();
        
        shipNameLabel.setText(ship.getName());
        shipCargoLabel.setText(ship.getCargo().size() + 
                "/" + ship.getCargoSlots());
        shipFuelLabel.setText(String.format("%.0f", ship.getFuel()) + "/" +
                String.format("%.0f", ship.getMaxFuel()));
        shipWeaponsLabel.setText(ship.getWeapons().size() + "/" +
                ship.getWeaponSlots());
        shipShieldsLabel.setText(ship.getShields().size() + "/" +
                ship.getShieldSlots());
        shipGadgetsLabel.setText(ship.getGadgets().size() + "/" +
                ship.getGadgetSlots());
        shipCrewLabel.setText(ship.getCrew().size() + "/" +
                ship.getCrewSlots());
        creditsLabel.setText("" + player.getCredits());
    }
}
