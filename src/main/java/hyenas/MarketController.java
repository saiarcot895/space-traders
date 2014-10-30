
package hyenas;

import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.Ship;
import hyenas.Models.Ware;
import hyenas.UI.AlertPane;
import hyenas.UI.AlertPane.AlertPaneType;
import hyenas.UI.MarketInfoPane;
import hyenas.UI.MarketTableColumn;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MarketController implements Initializable {
    private int[] wares;
    private int[] tempWare;
    private Planet planet;
    private TableView planetTable = new TableView();
    private TableView playerTable = new TableView();
    private MarketInfoPane infoPane;

    @FXML
    private BorderPane borderPane;
    
    @FXML
    private VBox boxPane;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label titleLabel;
    
    /**
     * Fuel Pane class for viewing the price of fuel and buying fuel
     */
    private class FuelPane extends BorderPane {
        private Button fuelButton;
        private Label fuelCostLabel;
        /*
         * Initializes an instance of FuelPane and sets up labels and buttons
         */
        public FuelPane() {
            getStyleClass().add("alertPane");
            
            int fuelCost = planet.getFuelCost();
            boolean canSellFuel = planet.canSellFuel();
            
            if (canSellFuel) {
                fuelCostLabel = new Label("Fuel Cost: " + fuelCost);
            } else {
                fuelCostLabel = new Label("Fuel Cost: N/A");
            }
            fuelCostLabel.getStyleClass().add("alertPaneTitleLabel");
            fuelCostLabel.setPadding(new Insets(10));
            setLeft(fuelCostLabel);

            BorderPane fuelButtonPane = new BorderPane();
            fuelButton = new Button("Buy");
            fuelButton.setPrefWidth(120.0);
            fuelButton.getStyleClass().add("standard-button");
            EventHandler<ActionEvent> buyFuelEvent = (ActionEvent e) -> {
                buyFuel(e);
            };
            fuelButton.setOnAction(buyFuelEvent);
            fuelButton.setDisable(!canSellFuel);
            fuelButtonPane.setCenter(fuelButton);
            setRight(fuelButtonPane);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Player player = Player.getInstance();
        planet = player.getTradingPlanet();
        
        Font titleFont = Font.loadFont(HyenasLoader.class.getResource("/hyenas/fonts/BlenderPro-Book.otf").toExternalForm(), 40);
        titleLabel.setFont(titleFont);
        titleLabel.setStyle("-fx-text-fill: rgba(0,231,255, .9); -fx-effect: dropshadow( gaussian, rgba(0,0,0,1), 0,0,2,2);");
        
        BorderPane tablesPane = new BorderPane();
        planetTable.setPrefHeight(400.0);
        planetTable.setPrefWidth(350.0);
        planetTable.setEditable(false);
        
        TableColumn wareCol = new MarketTableColumn("Ware");
        TableColumn availableCol = new MarketTableColumn("Available");
        TableColumn priceCol = new MarketTableColumn("Price");
        TableColumn contitionsCol = new MarketTableColumn("Conditions");
        wareCol.setCellValueFactory(
            new PropertyValueFactory<>("name")
        );
        availableCol.setCellValueFactory(
            new PropertyValueFactory<>("currentQuantity")
        );
        priceCol.setCellValueFactory(
            new PropertyValueFactory<>("currentPrice")
        );
        contitionsCol.setCellValueFactory(
            new PropertyValueFactory<>("currentCondition")
        );
        
        updatePlanetTable();
        planetTable.getColumns().addAll(wareCol, availableCol, priceCol, contitionsCol);
        planetTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> setSelectedBuyWare((Ware)newValue));
        
        playerTable.setPrefHeight(400.0);
        playerTable.setPrefWidth(202.0);
        playerTable.setEditable(false);
        TableColumn playerWareCol = new MarketTableColumn("Ware");
        TableColumn cargoCol = new MarketTableColumn("Cargo");
        playerWareCol.setCellValueFactory(
            new PropertyValueFactory<Ware, String>("name")
        );
        cargoCol.setCellValueFactory(
            new PropertyValueFactory<Ware, Integer>("currentQuantity")
        );
        
        updatePlayerTable();
        playerTable.getColumns().addAll(playerWareCol, cargoCol);
        playerTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> setSelectedSellWare((Ware)newValue));
        
        Pane emptyLeftTablePane = new Pane();
        emptyLeftTablePane.setPrefWidth(150.0);
        tablesPane.setLeft(emptyLeftTablePane);
        
        Pane emptyBottomTablePane = new Pane();
        emptyBottomTablePane.setPrefHeight(150.0);
        tablesPane.setBottom(emptyBottomTablePane);
        
        
        tablesPane.setCenter(planetTable);
        tablesPane.setRight(playerTable);
        borderPane.setCenter(tablesPane);
        
        
        VBox rightBox = new VBox();
        rightBox.setSpacing(10.0);
        infoPane = new MarketInfoPane();
        Button buyButton = infoPane.getBuyButton();
        EventHandler<ActionEvent> buyEvent = (ActionEvent e) -> {
            buyItem(e);
        };
        buyButton.setOnAction(buyEvent);
        Button sellButton = infoPane.getSellButton();
        EventHandler<ActionEvent> sellEvent = (ActionEvent e) -> {
            sellItem(e);
        };
        sellButton.setOnAction(sellEvent);
        
        
        FuelPane fuelPane = new FuelPane();
        rightBox.getChildren().addAll(infoPane, fuelPane);
        borderPane.setRight(rightBox);
        
        
        borderPane.setPadding(new Insets(40));
        BorderPane.setMargin(planetTable, new Insets(50, 50, 50, 20));
        BorderPane.setMargin(playerTable, new Insets(50, 20, 50, 20));
        BorderPane.setMargin(rightBox, new Insets(50, 0, 0, 0));
    }
    
    private void updatePlayerTable() {
        ObservableList<Ware> currentItems = playerTable.getItems();
        currentItems.removeAll(currentItems);
        
        Player player = Player.getInstance();
        Ship ship = player.getShip();
        List<Ware> playerWares = ship.getWares();
        ObservableList<Ware> playerTableData = FXCollections.observableArrayList(playerWares);
        playerTable.setItems(playerTableData);
    }
    
    private void updatePlanetTable() {
        ObservableList<Ware> currentItems = planetTable.getItems();
        currentItems.removeAll(currentItems);
        
        List<Ware> wares = planet.getWares();
        ObservableList<Ware> planetTableData = FXCollections.observableArrayList(wares);
        planetTable.setItems(planetTableData);
    }

    public void buyItem(ActionEvent e) {
        removeAlert();
        Ware ware = (Ware) planetTable.getSelectionModel().getSelectedItem();
        int price = ware.getCurrentPrice();
        Player player = Player.getInstance();
        int credits = player.getCredits();
        
        
        int currentQuantity = ware.getCurrentQuantity();
        if (currentQuantity > 0) {
            if (price <= credits) {
                Ship ship = player.getShip();
                boolean success = ship.addCargo(ware);
                if (success) {
                    int newCredits = credits - price;
                    player.setCredits(newCredits);
                    ware.setCurrentQuantity(--currentQuantity);

                    infoPane.updateInfo();
                    updatePlanetTable();
                    updatePlayerTable();
                } else {
                    displayAlert("Ship Cargo Full", "There is no room on your ship for more items.");
                }
            } else {
                displayAlert("Insufficient Credits", "You don't have enough credits to purchase this item.");
            }
        } else {
            displayAlert("No Items Remaining", "The planet has run out of this item.");
        }
    }

    public void sellItem(ActionEvent e) {
        removeAlert();
        Ware ware = (Ware) playerTable.getSelectionModel().getSelectedItem();
        Player player = Player.getInstance();
        
        int quantity = ware.getCurrentQuantity();
        if (quantity > 0) {
            int techLevel = planet.getTechLevel().ordinal();
            int minTechLevelToUse = ware.getMinimumTechLevelToUse();
            if (minTechLevelToUse <= techLevel) {
                Ship ship = player.getShip();
                ship.removeCargo(ware);
                planet.addWare(ware);

                int sellValue = (int) (ware.getBasePrice() * .8);
                int credits = player.getCredits();
                player.setCredits(credits + sellValue);

                infoPane.updateInfo();
                updatePlanetTable();
                updatePlayerTable();
            } else {
                displayAlert("Insufficient Tech Level", "The planet doesn't want to buy an item it can't use yet.");
            }
        } else {
            displayAlert("No Items", "You don't have any of this item to sell.");
        }
    }
    
    private void displayAlert(String title, String message) {
        AlertPane alertPane = new AlertPane(AlertPaneType.OneButton);
        alertPane.setTitleText(title);
        alertPane.setMessageText(message);
        EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
            anchorPane.getChildren().remove(alertPane);
        };
        alertPane.getCloseButton().setOnAction(closeAction);
        anchorPane.getChildren().add(alertPane);
    }
    
    private void removeAlert() {
        List children = anchorPane.getChildren();
        if (children.size() > 1) {
            children.remove(children.get(1));
        }
    }
    
    private void setSelectedSellWare(Ware ware) {
        if (ware == null) {
            Button sellButton = infoPane.getSellButton();
            sellButton.setDisable(true);
        } else {
            Button buyButton = infoPane.getBuyButton();
            buyButton.setDisable(true);
            Button sellButton = infoPane.getSellButton();
            sellButton.setDisable(false);
            planetTable.getSelectionModel().clearSelection();
        }
    }
    
    private void setSelectedBuyWare(Ware ware) {
        if (ware == null) {
            Button buyButton = infoPane.getBuyButton();
            buyButton.setDisable(true);
        } else {
            Button buyButton = infoPane.getBuyButton();
            buyButton.setDisable(false);
            Button sellButton = infoPane.getSellButton();
            sellButton.setDisable(true);
            playerTable.getSelectionModel().clearSelection();
        }
    }
    
    private final double FUEL_INCREMENT = 10.0;
    
    public void buyFuel(ActionEvent e) {
        Player player = Player.getInstance();
        Ship ship = player.getShip();
        int fuelCost = planet.getFuelCost();
        
        int credits = player.getCredits();
        
        double fuel = ship.getFuel();
        double maxFuel = ship.getMaxFuel();
        double fuelNeeded = maxFuel - fuel;

        if (fuelNeeded > 0) {
            int totalFuelCost = (int) (fuelCost * FUEL_INCREMENT);
            int newFuel = (int) (fuel + FUEL_INCREMENT);

            if (fuelNeeded < FUEL_INCREMENT) {
                totalFuelCost = (int) (fuelCost * fuelNeeded);
                newFuel = (int) (fuel + fuelNeeded);
            }

            if (credits >= totalFuelCost) {
                player.setCredits(credits - totalFuelCost);
                ship.setFuel(newFuel);
            } else {
                displayAlert("Insufficient Credits", "You don't have enough credits to purchase this item.");
            }
        } else {
            displayAlert("Ship Full", "Your ship can't carry any more fuel.");
        }
        
        infoPane.updateInfo();
        
        
        // if (fuelCount == player.getShip().getMaxFuel() || creditCount < fuelCost) {

        //     //TODO display message saying that they have hit limit on fuel
        // }
        // fuelCount++;
        // creditCount -= fuelCost;
        // fuelLeft.setText(String.format("%.0f", fuelCount));
        // currentCredits.setText("" + creditCount);
    }

    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToSystemScreen();
    }
}
