
package hyenas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MarketController implements Initializable {

	@FXML
	private Label pWater;
	
	@FXML
	private Label pFurs;

	@FXML
	private Label pFood;
	
	@FXML
	private Label pOre;
	
	@FXML
	private Label pGames;
	
	@FXML
	private Label pFirearms;
	
	@FXML
	private Label pMedicine;
	
	@FXML
	private Label pMachines;

	@FXML
	private Label pNarcotics;
	
	@FXML
	private Label pRobots;
	
	@FXML
	private Label nWater;
	
	@FXML
	private Label nFurs;

	@FXML
	private Label nFood;
	
	@FXML
	private Label nOre;
	
	@FXML
	private Label nGames;
	
	@FXML
	private Label nFirearms;
	
	@FXML
	private Label nMedicine;
	
	@FXML
	private Label nMachines;

	@FXML
	private Label nNarcotics;
	
	@FXML
	private Label nRobots;

	@FXML
	private Button bWater;
	
	@FXML
	private Button bFurs;

	@FXML
	private Button bFood;
	
	@FXML
	private Button bOre;
	
	@FXML
	private Button bGames;
	
	@FXML
	private Button bFirearms;
	
	@FXML
	private Button bMedicine;
	
	@FXML
	private Button bMachines;

	@FXML
	private Button bNarcotics;
	
	@FXML
	private Button bRobots;
	
	@FXML
	private Button sWater;
	
	@FXML
	private Button sFurs;

	@FXML
	private Button sFood;
	
	@FXML
	private Button sOre;
	
	@FXML
	private Button sGames;
	
	@FXML
	private Button sFirearms;
	
	@FXML
	private Button sMedicine;
	
	@FXML
	private Button sMachines;

	@FXML
	private Button sNarcotics;
	
	@FXML
	private Button sRobots;
	
	@FXML
	private Label eWater;
	
	@FXML
	private Label eFurs;

	@FXML
	private Label eFood;
	
	@FXML
	private Label eOre;
	
	@FXML
	private Label eGames;
	
	@FXML
	private Label eFirearms;
	
	@FXML
	private Label eMedicine;
	
	@FXML
	private Label eMachines;

	@FXML
	private Label eNarcotics;
	
	@FXML
	private Label eRobots;
	
	@FXML
	private Label aWater;
	
	@FXML
	private Label aFurs;

	@FXML
	private Label aFood;
	
	@FXML
	private Label aOre;
	
	@FXML
	private Label aGames;
	
	@FXML
	private Label aFirearms;
	
	@FXML
	private Label aMedicine;
	
	@FXML
	private Label aMachines;

	@FXML
	private Label aNarcotics;
	
	@FXML
	private Label aRobots;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button confirm;
        
        private int freeCargo;
        private int creditCount;
        private int[] wares;
        private Planet planet;
        
        int waterPrice = 30;
        int fursPrice = 250;
        int foodPrice = 100;
        int orePrice = 350;
        int gamesPrice = 250;
        int firearmsPrice = 1250;
        int medicinePrice = 650;
        int machinesPrice = 900;
        int narcoticsPrice = 3500;
        int robotsPrice = 5000;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	// TODO: Will implement the buying and selling here.
    }
    
    public MarketController(Planet planet) {
        this.planet = planet;
        wares = planet.getItems();
        freeCargo = Player.getInstance().getShip().getFreeCargo();
        creditCount = Player.getInstance().getCredits();
    }
    
    public void buyItem(ActionEvent e) {
        if (freeCargo > 0) {
            if (e.getSource() == bWater) {
                if (creditCount >= waterPrice) {
                    if (wares[0] > 0) {
                        freeCargo--;
                        creditCount -= waterPrice;
                        wares[0]--;
                    }
                }
            } else if (e.getSource() == bFurs) {
                if (creditCount >= fursPrice) {
                    if (wares[1] > 0) {
                        freeCargo--;
                        creditCount -= fursPrice;
                        wares[1]--;
                    }
                }
            } else if (e.getSource() == bFood) {
                if (creditCount >= foodPrice) {
                    if (wares[2] > 0) {
                        freeCargo--;
                        creditCount -= foodPrice;
                        wares[2]--;
                    }
                }
            } else if (e.getSource() == bOre) {
                if (creditCount >= orePrice) {
                    if (wares[3] > 0) {
                        freeCargo--;
                        creditCount -= orePrice;
                        wares[3]--;
                    }
                }
            } else if (e.getSource() == bGames) {
                if (creditCount >= gamesPrice) {
                    if (wares[4] > 0) {
                        freeCargo--;
                        creditCount -= gamesPrice;
                        wares[4]--;
                    }
                }
            } else if (e.getSource() == bFirearms) {
                if (creditCount >= firearmsPrice) {
                    if (wares[5] > 0) {
                        freeCargo--;
                        creditCount -= firearmsPrice;
                        wares[5]--;
                    }
                }
            } else if (e.getSource() == bMachines) {
                if (creditCount >= machinesPrice) {
                    if (wares[6] > 0) {
                        freeCargo--;
                        creditCount -= machinesPrice;
                        wares[6]--;
                    }
                }
            } else if (e.getSource() == bMedicine) {
                if (creditCount >= medicinePrice) {
                    if (wares[7] > 0) {
                        freeCargo--;
                        creditCount -= medicinePrice;
                        wares[7]--;
                    }
                }
            } else if (e.getSource() == bNarcotics) {
                if (creditCount >= narcoticsPrice) {
                    if (wares[8] > 0) {
                        freeCargo--;
                        creditCount -= narcoticsPrice;
                        wares[8]--;
                    }
                }
            } else if (e.getSource() == bRobots) {
                if (creditCount >= robotsPrice) {
                    if (wares[9] > 0) {
                        freeCargo--;
                        creditCount -= robotsPrice;
                        wares[9]--;
                    }
                }
            }
        }
    }
    
    public void sellItem(ActionEvent e) {
        
    }    
}
