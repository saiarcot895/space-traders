package hyenas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private MenuButton sex;
    
    @FXML
    private MenuButton color;
    
    @FXML
    private MenuButton race;
    
    @FXML
    private TextField pCounter;
    
    @FXML
    private TextField fCounter;
    
    @FXML
    private TextField tCounter;
    
    @FXML
    private TextField eCounter;
    
    @FXML
    private TextField iCounter;
    
    @FXML
    private TextField point;
    
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
        assert pilot != null;
        assert fighter != null;
        assert trader != null;
        assert engineer != null;
        assert investor != null;
        
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
                pCounter.setText(Integer.toString(fValue));
                point.setText(Integer.toString(startingPoints));
            }
        });
        
        trader.setOnAction((ActionEvent t) -> {
            if (startingPoints > 0) {
                tValue++;
                startingPoints--;
                pCounter.setText(Integer.toString(tValue));
                point.setText(Integer.toString(startingPoints));
            }
        });
        
        engineer.setOnAction((ActionEvent t) -> {
            if (startingPoints > 0) {
                eValue++;
                startingPoints--;
                pCounter.setText(Integer.toString(eValue));
                point.setText(Integer.toString(startingPoints));
            }
        });
        
        investor.setOnAction((ActionEvent t) -> {
            if (startingPoints > 0) {
                iValue++;
                startingPoints--;
                pCounter.setText(Integer.toString(iValue));
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
    }
    
    public void create(ActionEvent e) {
        // TODO: Create character with all attributes user inputted.
    }
    
    public void goBack(ActionEvent e) {
        Hyenas.getInstance().goToHomeScreen();
    }
    
}
