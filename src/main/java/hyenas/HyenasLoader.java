/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Alex
 */
public class HyenasLoader extends Application {

    private static HyenasLoader instance;

    private Stage stage;

    public static HyenasLoader getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource(
                    "MainWindow.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("hyenas/Common.css");

        stage.setScene(scene);
        stage.show();
    }

    public void goToStartGameScreen() {
        try {
            changePage("Allocation.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void goToMapScreen() {
    	private AnchorPane anchor;		//id for anchorpane
        try {
            changePage("UserUI.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        for(int i=0; i<Galaxy.getInstance().getSolarSystems().length; i++)	{
        	Button button = new Button(Galaxy.getInstance().getSolarSystems()[i].getSystemName());
        	button.setLayoutX(Galaxy.getInstance().getSolarSystems()[i].getX());
        	button.setLayoutY(Galaxy.getInstance().getSolarSystems()[i].getY());
        	button.setId(Galaxy.getInstance().getSolarSystems()[i].getSystemName());
        	button.setMnemonicParsing(false);
        	button.setOnAction(jump());		//may cause errors, don't know
        	button.getStyleClass().add("planet");
        	AnchorPane.getChildren().add(button);		//pray this works
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
            changePage("marketUI.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HyenasLoader.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private void changePage(String pageName) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource(pageName));

        stage.getScene().setRoot(page);
        stage.sizeToScene();
    }

    public void confirmSelection() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
