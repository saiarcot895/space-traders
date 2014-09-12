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

/**
 *
 * @author Alex
 */
public class Hyenas extends Application {
    
    private static Hyenas instance;
    
    private Stage stage;
    
    public static Hyenas getInstance() {
        return instance;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        this.stage = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void goToStartGameScreen() {
        try {
            changePage("Allocation.fxml");
        } catch (IOException ex) {
            Logger.getLogger(Hyenas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToSettingsScreen() {
        
    }

    public void goToHomeScreen() {
        try {
            changePage("MainWindow.fxml");
        } catch (IOException ex) {
            Logger.getLogger(Hyenas.class.getName()).log(Level.SEVERE, null, ex);
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
