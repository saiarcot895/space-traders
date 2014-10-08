/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hyenas;

import hyenas.UI.UIHelper;
import java.awt.Dimension;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author saikrishna
 */
public class MainWindowController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label titleLabel;

    @FXML
    private Button startGameButton;

    @FXML
    private Button settingsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font titleFont = Font.loadFont(HyenasLoader.class.getResource("/hyenas/fonts/GALACTICVANGUARDIANNCV.ttf").toExternalForm(), 70);
        Font buttonFont = Font.loadFont(HyenasLoader.class.getResource("/hyenas/fonts/BlenderPro-Book.otf").toExternalForm(), 40);

        titleLabel.setFont(titleFont);
        titleLabel.setCache(true);

        Dimension screenSize = UIHelper.getScreenSize();
        double buttonPadding = screenSize.getWidth() / 2 - 200;
        double titleLabelPadding = screenSize.getWidth() / 2 - 400;

        AnchorPane.setTopAnchor(titleLabel, 150.0);
        AnchorPane.setLeftAnchor(titleLabel, titleLabelPadding);
        AnchorPane.setRightAnchor(titleLabel, titleLabelPadding);

        startGameButton.setFont(buttonFont);
        AnchorPane.setTopAnchor(startGameButton, 250.0);
        AnchorPane.setLeftAnchor(startGameButton, buttonPadding);
        AnchorPane.setRightAnchor(startGameButton, buttonPadding);

        settingsButton.setFont(buttonFont);
        AnchorPane.setTopAnchor(settingsButton, 350.0);
        AnchorPane.setLeftAnchor(settingsButton, buttonPadding);
        AnchorPane.setRightAnchor(settingsButton, buttonPadding);
    }

    public void onStartGameClicked(Event e)  {
        HyenasLoader.getInstance().goToStartGameScreen();
    }

    public void onSettingsClicked(ActionEvent e) {
        HyenasLoader.getInstance().goToSettingsScreen();
    }

}
