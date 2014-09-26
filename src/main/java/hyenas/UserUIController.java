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
import javafx.scene.control.Button;
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

    private final int level0 = 60;

    private final int level1 = 70;

    private final int level2 = 80;

    private final int level3 = 90;

    private final int level4 = 100;

    private final int level5 = 110;

    private final int level6 = 120;

    private final int level7 = 130;

    private final int level8 = 140;

    private final int level9 = 150;

    private final int level10 = 160;

    private final int level11 = 170;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void goToMarketplace(ActionEvent e) {
        Hyenas.getInstance().goToMarketplace();
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
