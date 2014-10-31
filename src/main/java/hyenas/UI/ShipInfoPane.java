package hyenas.UI;

import hyenas.Models.Player;
import hyenas.Models.Ship;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    
    public ShipInfoPane() {
        getStyleClass().add("market-info-pane");
        setPrefWidth(250.0);
        setPadding(new Insets(10));
        
        VBox leftBox = new VBox();
        Label shipName = new Label("Ship:");
        shipName.getStyleClass().add("alertPaneTitleLabel");
        Label shipCargo = new Label("Cargo:");
        shipCargo.getStyleClass().add("alertPaneTitleLabel");
        Label shipFuel = new Label("Fuel:");
        shipFuel.getStyleClass().add("alertPaneTitleLabel");
        Label shipWeapons = new Label("Weapons:");
        shipWeapons.getStyleClass().add("alertPaneTitleLabel");
        Label shipShields = new Label("Shields:");
        shipShields.getStyleClass().add("alertPaneTitleLabel");
        Label shipGadgets = new Label("Gadgets:");
        shipGadgets.getStyleClass().add("alertPaneTitleLabel");
        Label shipCrew = new Label("Crew:");
        shipCrew.getStyleClass().add("alertPaneTitleLabel");
        Label credits = new Label("Credits:");
        credits.getStyleClass().add("alertPaneTitleLabel");
        
        leftBox.getChildren().addAll(shipName, shipCargo, shipFuel, shipWeapons,
                shipShields, shipGadgets, shipCrew, credits);
        
        VBox rightBox = new VBox();
        shipNameLabel = new Label("[Name]");
        shipNameLabel.getStyleClass().add("alertPaneMessageLabel");
        shipCargoLabel = new Label("[0/0]");
        shipCargoLabel.getStyleClass().add("alertPaneMessageLabel");
        shipFuelLabel = new Label("[0/0]");
        shipFuelLabel.getStyleClass().add("alertPaneMessageLabel");
        shipWeaponsLabel = new Label("[0/0]");
        shipWeaponsLabel.getStyleClass().add("alertPaneMessageLabel");
        shipShieldsLabel = new Label("[0/0]");
        shipShieldsLabel.getStyleClass().add("alertPaneMessageLabel");
        shipGadgetsLabel = new Label("[0/0]");
        shipGadgetsLabel.getStyleClass().add("alertPaneMessageLabel");
        shipCrewLabel = new Label("[0/0]");
        shipCrewLabel.getStyleClass().add("alertPaneMessageLabel");
        creditsLabel = new Label("[0]");
        creditsLabel.getStyleClass().add("alertPaneMessageLabel");
        
        rightBox.getChildren().addAll(shipNameLabel, shipCargoLabel,
                shipFuelLabel, shipWeaponsLabel, shipShieldsLabel, 
                shipGadgetsLabel, shipCrewLabel, creditsLabel);
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
        
        sellButton = new Button("Sell");
        sellButton.setDisable(true);
        sellButton.getStyleClass().add("standard-button");
        sellButton.setPrefHeight(37.0);
        sellButton.setPrefWidth(143.0);
        
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
