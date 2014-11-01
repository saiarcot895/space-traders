package hyenas;

import hyenas.Models.Player;
import hyenas.UI.AlertPane;
import hyenas.UI.AlertPane.AlertPaneType;
import hyenas.database.PlayerTable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class for allocating skill points and setting up the player
 *
 * @author Abhishek
 */
public class AllocationController implements Initializable {

    private int pointsRemaining = 8;
    
    @FXML
    private AnchorPane anchorPane;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        point.setText(Integer.toString(pointsRemaining) + " point(s) remaining");
        
        pilot.setOnAction((ActionEvent t) -> {
            updatePoints(++pValue, pCounter);
        });

        fighter.setOnAction((ActionEvent t) -> {
            updatePoints(++fValue, fCounter);
        });

        trader.setOnAction((ActionEvent t) -> {
            updatePoints(++tValue, tCounter);
        });

        engineer.setOnAction((ActionEvent t) -> {
            updatePoints(++eValue, eCounter);
        });

        investor.setOnAction((ActionEvent t) -> {
            updatePoints(++iValue, iCounter);
        });

        reset.setOnAction((ActionEvent t) -> {
           pointsRemaining = 8;
           pValue = 1;
           fValue = 1;
           tValue = 1;
           eValue = 1;
           iValue = 1;
           point.setText(Integer.toString(pointsRemaining) + " point(s) remaining");
           pCounter.setText(Integer.toString(pValue));
           fCounter.setText(Integer.toString(fValue));
           tCounter.setText(Integer.toString(tValue));
           eCounter.setText(Integer.toString(eValue));
           iCounter.setText(Integer.toString(iValue));
           name.setText("");
        });
    }

    /**
     * Updates the current skill points
     * @param points, the new skill points
     * @param label, the label to update
     */
    private void updatePoints(int points, Label label) {
        if (pointsRemaining > 0) {
            pointsRemaining--;
            label.setText(Integer.toString(points));
            point.setText(Integer.toString(pointsRemaining) + " point(s) remaining");
        }
    }

    /**
     * Creates the player object and sets up the database
     * @param e, an action event
     */
    public void create(ActionEvent e) throws SQLException {
        if (validInput()) {
            Player player = Player.getInstance();
            player.setName(name.getText());
            player.setPilotSkill(pValue);
            player.setFighterSkill(fValue);
            player.setTraderSkill(tValue);
            player.setEngineerSkill(eValue);
            player.setInvestorSkill(iValue);
            player.setState(true);
            PlayerTable newPlayers = HyenasLoader.getInstance()
                    .getConnectionManager().getPlayerTable();
            newPlayers.addRow(player, null);
            
            HyenasLoader.getInstance().getConnectionManager().getShipTable()
                    .addRow(player.getShip(), player);
            
            HyenasLoader.getInstance().goToMapScreen();
        } else {
            AlertPane alertPane = new AlertPane(AlertPaneType.ONEBUTTON);
            alertPane.setTitleText("Invalid Setup");
            alertPane.setMessageText("Please make sure you have set a player name and have allocated all skill points.");
            EventHandler<ActionEvent> closeAction = (ActionEvent e2) -> {
                anchorPane.getChildren().remove(alertPane);
            };
            alertPane.getCloseButton().setOnAction(closeAction);
            anchorPane.getChildren().add(alertPane);
            
        }
    }
    
    /**
     * Determines whether the input in the fields is valid
     * @return boolean, whether the input is valid
     */
    private boolean validInput() {
        if (name.getText().length() == 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Changes screens back to the home screen
     */
    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToHomeScreen();
    }
}
