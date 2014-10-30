/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.database.ConnectionManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Alex
 */
public class HyenasLoader extends Application {

    private static HyenasLoader instance;
    
    private ConnectionManager connectionManager;

    private Stage stage;

    public static HyenasLoader getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;

        connectionManager = new ConnectionManager("jdbc:sqlite:database.db");
        connectionManager.openConnection();
        
        // Create the tables. If the tables are already created, this will
        // do nothing.
        connectionManager.getSolarSystemTable().createTable();
        connectionManager.getPlanetTable().createTable();
        connectionManager.getPlayerTable().createTable();
        connectionManager.getItemTable().createTable();
        connectionManager.getShipTable().createTable();
        connectionManager.getGadgetsTable().createTable();
        connectionManager.getWeaponsTable().createTable();

        this.stage = stage;
        stage.setFullScreen(true);
        Parent root = FXMLLoader.load(getClass().getResource(
                    "MainWindow.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("hyenas/Common.css");

        stage.setScene(scene);
        stage.show();
    }

    public void stop(Stage stage) throws Exception {
        stage.setOnCloseRequest((WindowEvent t) -> {
            try {
                stop(stage);
            } catch (Exception ex) {
                Logger.getLogger(HyenasLoader.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        });
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void goToStartGameScreen() {
        connectionManager.getWeaponsTable().clearTable();
        connectionManager.getGadgetsTable().clearTable();
        connectionManager.getShipTable().clearTable();
        connectionManager.getItemTable().clearTable();
        connectionManager.getPlayerTable().clearTable();
        connectionManager.getPlanetTable().clearTable();
        connectionManager.getSolarSystemTable().clearTable();

        loadScreen("AllocationUI.fxml");
    }

    public void goToMapScreen() {
        loadScreen("MapUI.fxml");
    }

    public void goToSettingsScreen() {
        loadScreen("Settings.fxml");
    }

    public void goToHomeScreen() {
        loadScreen("MainWindow.fxml");
    }

    public void goToMarketplace() {
        loadScreen("MarketUI.fxml");
    }

    public void goToSystemScreen() {
        loadScreen("SystemUI.fxml");
    }
    
    public void goToShipyard() {
        loadScreen("ShipyardUI.fxml");
    }
    
    private void loadScreen(String screen) {
        try {
            changePage(screen);
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private void changePage(String pageName) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource(pageName));

        stage.getScene().setRoot(page);
//      stage.sizeToScene();
    }

    /**
     * Restore last point in game
     * in SolarSystemView.
     */
    public void continueGame() {
        connectionManager.getSolarSystemTable().loadTable();
        connectionManager.getPlanetTable().loadTable();
        connectionManager.getPlayerTable().loadTable();
        connectionManager.getItemTable().loadTable();
        goToMapScreen();
    }

    /**
     * Close connection to database if not already closed
     * Exit application
     */
    public void closeGame() {
        // In the main menu the game should not be connected to the database!
        goToShipyard();
        
//        closeConnection(conn);
//        try {
//            // close application
//            stop(stage);
//        } catch (Exception ex) {
//            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

/**********************************************************************/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return stage;
    }

}