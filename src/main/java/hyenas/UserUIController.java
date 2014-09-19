/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abhishek
 */
public class UserUIController implements Initializable {

    @FXML
    private Button utopia;
    
    @FXML
    private Button earth616;
    
    @FXML
    private Button tantolos;
    
    @FXML
    private Button omega;
    
    @FXML
    private Button carzon;
    
    @FXML
    private Button destiny;
    
    @FXML
    private Button frolix;
    
    @FXML
    private Button exo;
    
    @FXML
    private Button random;
    
    @FXML
    private Button menu;
    
    @FXML
    private Button confirm;
    
    private final int level_0 = 60;
    
    private final int level_1 = 70;
    
    private final int level_2 = 80;
    
    private final int level_3 = 90;
    
    private final int level_4 = 100;
    
    private final int level_5 = 110;
    
    private final int level_6 = 120;
    
    private final int level_7 = 130;
    
    private final int level_8 = 140;
    
    private final int level_9 = 150;
    
    private final int level_10 = 160;
    
    private final int level_11 = 170;
    
    private static StringBuilder confirmText;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert utopia != null;
        assert earth616 != null;
        assert tantolos != null;
        assert carzon != null;
        assert omega != null;
        assert destiny != null;
        assert frolix != null;
        assert random != null;
        assert exo != null;
        assert menu != null;
        
        assert confirm != null;
        
        confirmText.append("Would you like to go to ");
        
        confirm = new Button("Confirm");
        
        utopia.setOnAction((ActionEvent t) -> {
            toUtopia(t);
        });
        
        earth616.setOnAction((ActionEvent t) -> {
            toEarth616(t);
        });
        
        tantolos.setOnAction((ActionEvent t) -> {
            toTantalos(t);
        });
        
        carzon.setOnAction((ActionEvent t) -> {
            toCarzon(t);
        });
        
        omega.setOnAction((ActionEvent t) -> {
            toOmega(t);
        });
        
        destiny.setOnAction((ActionEvent t) -> {
            toDestiny(t);
        });
        
        frolix.setOnAction((ActionEvent t) -> {
            toFrolix(t);
        });
        
        exo.setOnAction((ActionEvent t) -> {
            toExo(t);
        });
        
    }    
 
    public void toEarth616(ActionEvent t) {
        final Stage popup = new Stage();
        Button confirm = new Button();
        confirm.setText("Yes");
        popup.initModality(Modality.APPLICATION_MODAL);
        
    }
    
    public void toTantalos(ActionEvent t) {
        
    }
    
    public void toCarzon(ActionEvent t) {
        
    }
    
    public void toUtopia(ActionEvent t) {
        
    }
    
    public void toOmega(ActionEvent t) {
        
    }
    
    public void toDestiny(ActionEvent t) {
        
    }
    
    public void toFrolix(ActionEvent t) {
        
    }
    
    public void toExo(ActionEvent t) {
        
    }
}
