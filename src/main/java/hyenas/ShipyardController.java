/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.Models.Ship;
import hyenas.Models.Ware;
import hyenas.UI.MarketTableColumn;
import hyenas.UI.ShipTypeTable;
import hyenas.UI.WeaponTypeTable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * For buying ship
 * @author Alex
 */
public class ShipyardController implements Initializable {
    
    @FXML
    private VBox boxPane;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label titleLabel;
    
    @FXML
    private BorderPane centerPane;
    
    private TableView shipTable = new ShipTypeTable();
    private TableView weaponTable = new WeaponTypeTable();
    private TableView shieldTable = new ShipTypeTable();
    private TableView gadgetTable = new ShipTypeTable();
    
    private final int TAB_PANE_WIDTH = 600;
    private final int TAB_PANE_HEIGHT= 400;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font titleFont = Font.loadFont(HyenasLoader.class.getResource("/hyenas/fonts/BlenderPro-Book.otf").toExternalForm(), 40);
        titleLabel.setFont(titleFont);
        titleLabel.setStyle("-fx-text-fill: rgba(0,231,255, .9); -fx-effect: dropshadow( gaussian, rgba(0,0,0,1), 0,0,2,2);");
        
        TabPane tabPane = new TabPane();
        tabPane.setPrefWidth(TAB_PANE_WIDTH);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        Tab shipTab = new Tab("Ships");
        shipTab.setContent(shipTable);
        Tab weaponsTab = new Tab("Weapons");
        weaponsTab.setContent(weaponTable);
        Tab shieldTab = new Tab("Shields");
        shieldTab.setContent(shieldTable);
        Tab gadgetTab = new Tab("Gadgets");
        gadgetTab.setContent(gadgetTable);
        tabPane.getTabs().addAll(shipTab, weaponsTab, shieldTab, gadgetTab);
        
        
//        shipTable.getSelectionModel().selectedItemProperty().addListener(
//            (observable, oldValue, newValue) -> setSelectedSellWare((Ware)newValue));
        
        
        
        centerPane.setCenter(tabPane);
        
        BorderPane.setMargin(tabPane, new Insets(50, 50, 50, 50));
    }
    
    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToSystemScreen();
    }
    
}
