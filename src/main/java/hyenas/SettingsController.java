/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hyenas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author saikrishna
 */
public class SettingsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void onCancelClicked(ActionEvent e) {
        Hyenas.getInstance().goToHomeScreen();
    }

    public void onSaveClicked(ActionEvent e) {
        Hyenas.getInstance().goToHomeScreen();
    }

}