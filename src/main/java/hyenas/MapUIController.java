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

/**
 * FXML Controller class
 *
 * @author Abhishek
 */
public class MapUIController implements Initializable {

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
        HyenasLoader.getInstance().goToMarketplace();
    }

    public void jump(ActionEvent t) {
        // TODO: Jump to planet, randomized.
    }
    
}
