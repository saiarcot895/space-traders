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

	Wares ware = new Wares();

	private int freeCargo;
	private int creditCount;
	private int[] wares;
	private Planet planet;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO: Will implement the buying and selling here.
	}

	public MarketController(Planet planet){
        	this.planet = planet;
        	freeCargo = /player/./ship/.freeCargo();
        	wares = planet.getItems();
        	creditCount = /player/.getCredits();
    }

	public void buyItem(ActionEvent e) {

	}

	public void sellItem(ActionEvent e) {

	}

	public void confirmTrade(ActionEvent e) {

	}

	public void cancelTrade(ActionEvent e) {

	}

}
