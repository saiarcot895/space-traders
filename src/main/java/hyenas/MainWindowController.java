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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * Main window controller. This class handles the display and contains the logic
 * for the title screen.
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

    @FXML
    private Button continueButton;

    @FXML
    private Button closeButton;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font titleFont = Font.loadFont("/hyenas/fonts/GALACTICVANGUARDIANNCV.ttf", 70);
        Font buttonFont = Font.loadFont("/hyenas/fonts/BlenderPro-Book.otf", 40);

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

        continueButton.setFont(buttonFont);
        AnchorPane.setTopAnchor(continueButton, 350.0);
        AnchorPane.setLeftAnchor(continueButton, buttonPadding);
        AnchorPane.setRightAnchor(continueButton, buttonPadding);

        settingsButton.setFont(buttonFont);
        AnchorPane.setTopAnchor(settingsButton, 450.0);
        AnchorPane.setLeftAnchor(settingsButton, buttonPadding);
        AnchorPane.setRightAnchor(settingsButton, buttonPadding);

        closeButton.setFont(buttonFont);
        AnchorPane.setTopAnchor(closeButton, 550.0);
        AnchorPane.setLeftAnchor(closeButton, buttonPadding);
        AnchorPane.setRightAnchor(closeButton, buttonPadding);

    }

    /**
     * Display the new game screen.
     * @param e unused
     */
    public void onStartGameClicked(ActionEvent e)  {
        HyenasLoader.getInstance().goToStartGameScreen();
    }

    /**
     * Load a previous game.
     * @param e unused
     */
    public void continueGame(ActionEvent e) {
        HyenasLoader.getInstance().continueGame();
    }

    /**
     * Display the settings screen
     * @param e unused
     */
    public void onSettingsClicked(ActionEvent e) {
        HyenasLoader.getInstance().goToSettingsScreen();
    }

    /**
     * Exit the game.
     * @param e unused
     */
    public void closeGame(ActionEvent e) {
        HyenasLoader.getInstance().closeGame();
    }

}
