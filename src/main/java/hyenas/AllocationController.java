package hyenas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Abhishek
 */
public class AllocationController implements Initializable {

    private int startingPoints = 8;

    @FXML
    private Button goBack;

    @FXML
    private Button reset;

    @FXML
    private Button create;

    @FXML
    private Button pilot;

    @FXML
    private Button fighter;

    @FXML
    private Button trader;

    @FXML
    private Button engineer;

    @FXML
    private Button investor;

    @FXML
    private TextField name;

    @FXML
    private TextField age;

    @FXML
    private ChoiceBox sex;

    @FXML
    private ChoiceBox color;

    @FXML
    private ChoiceBox race;

    @FXML
    private Label pCounter;

    @FXML
    private Label fCounter;

    @FXML
    private Label tCounter;

    @FXML
    private Label eCounter;

    @FXML
    private Label iCounter;

    @FXML
    private Label point;

    int pValue = 1;
    int fValue = 1;
    int tValue = 1;
    int eValue = 1;
    int iValue = 1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pilot.setOnAction((ActionEvent t) -> {
            if (startingPoints > 0) {
                pValue++;
                startingPoints--;
                pCounter.setText(Integer.toString(pValue));
                point.setText(Integer.toString(startingPoints));
            }
        });

        fighter.setOnAction((ActionEvent t) -> {
            if (startingPoints > 0) {
                fValue++;
                startingPoints--;
                fCounter.setText(Integer.toString(fValue));
                point.setText(Integer.toString(startingPoints));
            }
        });

        trader.setOnAction((ActionEvent t) -> {
            if (startingPoints > 0) {
                tValue++;
                startingPoints--;
                tCounter.setText(Integer.toString(tValue));
                point.setText(Integer.toString(startingPoints));
            }
        });

        engineer.setOnAction((ActionEvent t) -> {
            if (startingPoints > 0) {
                eValue++;
                startingPoints--;
                eCounter.setText(Integer.toString(eValue));
                point.setText(Integer.toString(startingPoints));
            }
        });

        investor.setOnAction((ActionEvent t) -> {
            if (startingPoints > 0) {
                iValue++;
                startingPoints--;
                iCounter.setText(Integer.toString(iValue));
                point.setText(Integer.toString(startingPoints));
            }
        });

        reset.setOnAction((ActionEvent t) -> {
           startingPoints = 8;
           pValue = 1;
           fValue = 1;
           tValue = 1;
           eValue = 1;
           iValue = 1;
           point.setText(Integer.toString(startingPoints));
           pCounter.setText(Integer.toString(pValue));
           fCounter.setText(Integer.toString(fValue));
           tCounter.setText(Integer.toString(tValue));
           eCounter.setText(Integer.toString(eValue));
           iCounter.setText(Integer.toString(iValue));
        });

        sex.setItems(FXCollections.observableArrayList("Male", "Female"));
    }

    public void create(ActionEvent e) {
        Player player = Player.getInstance();
        player.setName(name.getText());
        player.setPilotSkill(pValue);
        player.setFighterSkill(fValue);
        player.setTraderSkill(tValue);
        player.setEngineerSkill(eValue);
        player.setInvestorSkill(iValue);

        HyenasLoader.getInstance().goToMapScreen();
    }

    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToHomeScreen();
    }
}
