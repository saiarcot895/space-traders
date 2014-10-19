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
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
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

    private Stage stage;
    
    private Connection conn;
    private final String username = "Hyenas";
    private final String password = "Team20";
    private final String host = "jdbc:derby://localhost:1527/Hyenas";

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
        Connection connect = connectToDB(false);
        if (connect == null) {
            connect = connectToDB(true);
            players = new PlayerTable(connect, "Hyenas");
            planets = new PlanetTable(connect, "Hyenas");
            items = new ItemsTable(connect, "Hyenas");
            solarSystem = new SolarSystemTable(connect, "Hyenas");
            // Create the tables
            solarSystem.createTable();
            planets.createTable();
            players.createTable();
            items.createTable();
        }
        
        this.stage = stage;
        stage.setFullScreen(true);
//        stage.setResizable(false);
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
        try {
            changePage("Allocation.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    /*
     * DO NOT TOUCH!
     * This generates the systems randomly on start!
     */
    public void goToMapScreen() {
        try {
            changePage("MapUI.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void goToSettingsScreen() {
        try {
            changePage("Settings.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void goToHomeScreen() {
        try {
            changePage("MainWindow.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void goToMarketplace() {
        try {
            changePage("MarketUI.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void goToSystemScreen() {
        try {
            changePage("InSystemView.fxml");
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
        // TODO: Go back to SolarSystem view from database.
        // TODO: Make sure to save state of player.
        // Get the (x,y) coordinates from SolarSystem table
    }
    
    /**
     * Close connection to database if not already closed
     * Exit application
     */
    public void closeGame() {
        // In the main menu the game should not be connected to the database!
        closeConnection(conn);
        try {
            // close application
            stop(stage);
        } catch (Exception ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Wat?
    public void confirmSelection() {

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
    
    public Connection connectToDB(boolean create) throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("username", username);
        connectionProps.put("password", password);
        String createParam = "";
        if (create == true) {
            createParam = ";create=true";
        }
        try {
            conn = DriverManager.getConnection(host + createParam, connectionProps);
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