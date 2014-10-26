
package hyenas;

import hyenas.Models.Good;
import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.Ship;
import hyenas.Models.Ware;
import hyenas.UI.AlertPane;
import hyenas.UI.AlertPaneType;
import hyenas.UI.MarketInfoPane;
import hyenas.UI.MarketTableColumn;
import java.net.URL;
import java.util.List;
import java.util.Random;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class MarketController implements Initializable {
    private int[] wares;
    private int[] tempWare;
    private Planet planet;
    private TableView planetTable = new TableView();
    private TableView playerTable = new TableView();
    private MarketInfoPane infoPane;
    private BorderPane emptyBottomTablePane;

    @FXML
    private BorderPane borderPane;
    
    @FXML
    private VBox boxPane;
    
    @FXML
    private Label titleLabel;

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
            new PropertyValueFactory<Ware, String>("name")
        );
        availableCol.setCellValueFactory(
            new PropertyValueFactory<Ware, Integer>("currentQuantity")
        );
        priceCol.setCellValueFactory(
            new PropertyValueFactory<Ware, Integer>("currentPrice")
        );
        contitionsCol.setCellValueFactory(
            new PropertyValueFactory<Ware, String>("currentCondition")
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
        
        emptyBottomTablePane = new BorderPane();
        emptyBottomTablePane.setPrefHeight(150.0);
        Pane emptyBottomBottomPane = new Pane();
        emptyBottomBottomPane.setPrefHeight(50.0);
        emptyBottomTablePane.setBottom(emptyBottomBottomPane);
        tablesPane.setBottom(emptyBottomTablePane);
        tablesPane.setMargin(emptyBottomTablePane, new Insets(0, 100, 0, 350));
        
        
        tablesPane.setCenter(planetTable);
        tablesPane.setRight(playerTable);
        borderPane.setCenter(tablesPane);
        
        
        VBox rightBox = new VBox();
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
        
        Pane emptyRightSpacingPane = new Pane();
        emptyRightSpacingPane.setPrefWidth(300.0);
        emptyRightSpacingPane.setPrefHeight(250.0);
        rightBox.getChildren().addAll(infoPane, emptyRightSpacingPane);
        borderPane.setRight(rightBox);
        
        
        borderPane.setPadding(new Insets(40));
        borderPane.setMargin(planetTable, new Insets(50, 50, 50, 20));
        borderPane.setMargin(playerTable, new Insets(50, 20, 50, 20));
        borderPane.setMargin(rightBox, new Insets(50, 0, 0, 0));
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
        emptyBottomTablePane.setCenter(null);
        Ware ware = (Ware) planetTable.getSelectionModel().getSelectedItem();
        int price = ware.getCurrentPrice();
        Player player = Player.getInstance();
        int credits = player.getCredits();
        if (price < credits) {
            int currentQuantity = ware.getCurrentQuantity();
            if (currentQuantity > 0) {
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
                displayAlert("No Items Remaining", "The planet has run out of this item.");
            }
        } else {
            displayAlert("Insufficient Credits", "You don't have enough credits to purchase this item.");
        }
    }

    public void sellItem(ActionEvent e) {
        emptyBottomTablePane.setCenter(null);
        Ware ware = (Ware) playerTable.getSelectionModel().getSelectedItem();
        Player player = Player.getInstance();
        
        int quantity = ware.getCurrentQuantity();
        if (quantity > 0) {
            int techLevel = planet.getTechLevel();
            int minTechLevelToUse = ware.getMTLU();
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
            emptyBottomTablePane.setCenter(null);
        };
        alertPane.getCloseButton().setOnAction(closeAction);
        emptyBottomTablePane.setCenter(alertPane);
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

    public void addFuel(ActionEvent e) {
        // if (fuelCount == player.getShip().getMaxFuel() || creditCount < fuelCost) {

        //     //TODO display message saying that they have hit limit on fuel
        // }
        // fuelCount++;
        // creditCount -= fuelCost;
        // fuelLeft.setText(String.format("%.0f", fuelCount));
        // currentCredits.setText("" + creditCount);
    }

    public void subtractFuel(ActionEvent e) {
        // if (fuelCount == 0) {
        //     return;
        //     //TODO display message saying that they have hit limit on fuel
        // }
        // fuelCount--;
        // creditCount += fuelCost;
        // fuelLeft.setText(String.format("%.0f", fuelCount));
        // currentCredits.setText("" + creditCount);
    }

    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToSystemScreen();
    }
}
