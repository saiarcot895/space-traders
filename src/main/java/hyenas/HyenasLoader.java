/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import hyenas.database.ItemsTable;
import hyenas.database.PlanetTable;
import hyenas.database.PlayerTable;
import hyenas.database.SolarSystemTable;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.sqlite.JDBC;

/**
 *
 * @author Alex
 */
public class HyenasLoader extends Application {

    private static HyenasLoader instance;

    private Stage stage;

    private Connection conn;
    private final String host = "jdbc:sqlite:database.db";

    private PlayerTable players;
    private PlanetTable planets;
    private ItemsTable items;
    private SolarSystemTable solarSystem;

    public static HyenasLoader getInstance() {
        return instance;
    }

    public SolarSystemTable getSSTable() {
        return solarSystem;
    }

    public PlanetTable getPlanetTable() {
        return planets;
    }

    public PlayerTable getPlayerTable() {
        return players;
    }

    public ItemsTable getItemsTable() {
        return items;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;

        // TODO: If connected Yay!
        // Else create new database!
        Connection connect = connectToDB();
        
        players = new PlayerTable(connect);
        planets = new PlanetTable(connect);
        items = new ItemsTable(connect);
        solarSystem = new SolarSystemTable(connect);

        // Create the tables. If the tables are already created, this will
        // do nothing.
        solarSystem.createTable();
        planets.createTable();
        players.createTable();
        items.createTable();

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
                Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void goToStartGameScreen() {
        items.clearTable();
        players.clearTable();
        planets.clearTable();
        solarSystem.clearTable();

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
        solarSystem.loadTable();
        planets.loadTable();
        players.loadTable();
        items.loadTable();
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

    private boolean ignoreSQLException(String state) {
        if (state == null) {
            System.out.println("State undefined");
            return false;
        }
        return state.equalsIgnoreCase("X0Y32")
                || state.equalsIgnoreCase("42Y55");
    }

    private void printException(SQLException ex){
        for (Throwable e : ex){
            if (e instanceof SQLException){
                if (ignoreSQLException(((SQLException)e).getSQLState()) == false) {
                    e.printStackTrace(System.err);
                    System.err.println("State: " + ((SQLException)e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }

    public Connection connectToDB() throws SQLException {
        try {
            conn = DriverManager.getConnection(host);
            conn.createStatement().execute("PRAGMA foreign_keys = ON");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("UnusedAssignment")
    public void closeConnection(Connection connArgs){
        System.out.println("Releasing sources...");
        try {
            if (connArgs != null) {
                connArgs.close();
                connArgs = null;
            }
        } catch (SQLException e) {
            printException(e);
        }
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