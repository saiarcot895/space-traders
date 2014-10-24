
package hyenas;

import hyenas.Models.Good;
import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.Ware;
import java.net.URL;
import java.util.Random;
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

    @FXML
    private Label tPlanet;

    @FXML
    private Label tTechLevel;

    @FXML
    private Label tFree;

    @FXML
    private Label fuelLeft;

    @FXML
    private Label fuelPrice;

    @FXML
    private Button fuelAdd;

    @FXML
    private Button fuelSub;

    @FXML
    private Label currentCredits;

    private Player player;

    /**
     * These variables are so that the user can make changes without having the ship
     */
        private int freeCargo;
        private int creditCount;
        private double fuelCount;
        private int[] wares;
        private Planet planet;
        private int[] tempWare;

        int waterPrice;
        int fursPrice;
        int foodPrice;
        int orePrice;
        int gamesPrice;
        int firearmsPrice;
        int medicinePrice;
        int machinesPrice;
        int narcoticsPrice;
        int robotsPrice;
        int fuelCost;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player = Player.getInstance();
        freeCargo = player.getShip().getFreeCargo();
        creditCount = player.getCredits();
        planet = player.getTradingPlanet();
        wares = planet.getItems();
        tempWare = new int[10];
        fuelCost = 140-planet.getTechLevel()*10;
        fuelCount = player.getShip().getFuel();
        Random rand = new Random();
        waterPrice = 30 + 3*(planet.getTechLevel()-0) + planet.getWareEvents()[0] + (rand.nextInt(3)-1)*rand.nextInt(5);
        fursPrice = 250 + 10*(planet.getTechLevel()-0) + planet.getWareEvents()[1] + (rand.nextInt(3)-1)*rand.nextInt(11);
        foodPrice = 100 + 5*(planet.getTechLevel()-1) + planet.getWareEvents()[2] + (rand.nextInt(3)-1)*rand.nextInt(6);
        orePrice = 350 + 20*(planet.getTechLevel()-2) + planet.getWareEvents()[3] + (rand.nextInt(3)-1)*rand.nextInt(11);
        gamesPrice = 250 + -10*(planet.getTechLevel()-3) + planet.getWareEvents()[4] + (rand.nextInt(3)-1)*rand.nextInt(6);
        firearmsPrice = 1250 + -75*(planet.getTechLevel()-3) + planet.getWareEvents()[5] + (rand.nextInt(3)-1)*rand.nextInt(101);
        medicinePrice = 650 + -20*(planet.getTechLevel()-4) + planet.getWareEvents()[6] + (rand.nextInt(3)-1)*rand.nextInt(11);
        machinesPrice = 900 + -30*(planet.getTechLevel()-4) + planet.getWareEvents()[7] + (rand.nextInt(3)-1)*rand.nextInt(6);
        narcoticsPrice = 3500 + -125*(planet.getTechLevel()-5) + planet.getWareEvents()[8] + (rand.nextInt(3)-1)*rand.nextInt(151);
        robotsPrice = 5000 + -150*(planet.getTechLevel()-6) + planet.getWareEvents()[9] + (rand.nextInt(3)-1)*rand.nextInt(101);

        //tPlanet.setText(planet.getName());
        tFree.setText("" + freeCargo);

        pWater.setText("" + waterPrice);
        pFurs.setText("" + fursPrice);
        pFood.setText("" + foodPrice);
        pOre.setText("" + orePrice);
        pGames.setText("" + gamesPrice);
        pFirearms.setText("" + firearmsPrice);
        pMedicine.setText("" + medicinePrice);
        pMachines.setText("" + machinesPrice);
        pNarcotics.setText("" + narcoticsPrice);
        pRobots.setText("" + robotsPrice);
        fuelPrice.setText("" + fuelCost);
        fuelLeft.setText("" + fuelCount);
        currentCredits.setText("" + creditCount);

        for (Ware good : player.getShip().getCargo()) {
            switch (good.getName()) {
                case "Water":
                    aWater.setText(""
                            + (Integer.parseInt(aWater.getText()) + 1));
                    break;
                case "Furs":
                    aFurs.setText(""
                            + (Integer.parseInt(aFurs.getText()) + 1));
                    break;
                case "Food":
                    aFood.setText(""
                            + (Integer.parseInt(aFood.getText()) + 1));
                    break;
                case "Ore":
                    aOre.setText(""
                            + (Integer.parseInt(aOre.getText()) + 1));
                    break;
                case "Games":
                    aGames.setText(""
                            + (Integer.parseInt(aGames.getText()) + 1));
                    break;
                case "Firearms":
                    aFirearms.setText(""
                            + (Integer.parseInt(aFirearms.getText()) + 1));
                    break;
                case "Medicine":
                    aMedicine.setText(""
                            + (Integer.parseInt(aMedicine.getText()) + 1));
                    break;
                case "Machines":
                    aMachines.setText(""
                            + (Integer.parseInt(aMachines.getText()) + 1));
                    break;
                case "Narcotics":
                    aNarcotics.setText(""
                            + (Integer.parseInt(aNarcotics.getText()) + 1));
                    break;
                case "Robots":
                    aRobots.setText(""
                            + (Integer.parseInt(aRobots.getText()) + 1));
                    break;
            }
        }

        aWater.setText("" + wares[0]);
        aFurs.setText("" + wares[1]);
        aFood.setText("" + wares[2]);
        aOre.setText("" + wares[3]);
        aGames.setText("" + wares[4]);
        aFirearms.setText("" + wares[5]);
        aMedicine.setText("" + wares[6]);
        aMachines.setText("" + wares[7]);
        aNarcotics.setText("" + wares[8]);
        aRobots.setText("" + wares[9]);
    }

    public void buyItem(ActionEvent e) {
        if (freeCargo <= 0) {
            return;
        }

        if (e.getSource() == bWater) {
            if (creditCount >= waterPrice && wares[0] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= waterPrice;
                wares[0]--;
            }
        } else if (e.getSource() == bFurs) {
            if (creditCount >= fursPrice && wares[1] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= fursPrice;
                wares[1]--;
            }
        } else if (e.getSource() == bFood) {
            if (creditCount >= foodPrice && wares[2] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= foodPrice;
                wares[2]--;
            }
        } else if (e.getSource() == bOre) {
            if (creditCount >= orePrice && wares[3] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= orePrice;
                wares[3]--;
            }
        } else if (e.getSource() == bGames) {
            if (creditCount >= gamesPrice && wares[4] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= gamesPrice;
                wares[4]--;
            }
        } else if (e.getSource() == bFirearms) {
            if (creditCount >= firearmsPrice && wares[5] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= firearmsPrice;
                wares[5]--;
            }
        } else if (e.getSource() == bMachines) {
            if (creditCount >= machinesPrice && wares[6] > 0) {
                freeCargo--;
                creditCount -= machinesPrice;
                wares[6]--;
            }
        } else if (e.getSource() == bMedicine) {
            if (creditCount >= medicinePrice && wares[7] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= medicinePrice;
                wares[7]--;
            }
        } else if (e.getSource() == bNarcotics) {
            if (creditCount >= narcoticsPrice && wares[8] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= narcoticsPrice;
                wares[8]--;
            }
        } else if (e.getSource() == bRobots) {
            if (creditCount >= robotsPrice && wares[9] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= robotsPrice;
                wares[9]--;
            }
        }
        currentCredits.setText("" + creditCount);
    }

    public void sellItem(ActionEvent e) {
        if (e.getSource() == sWater) {
            if (player.getShip().getCargo().contains(Good.Water)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[0]++;
                creditCount += waterPrice;
            }
        } else if (e.getSource() == sFurs) {
            if (player.getShip().getCargo().contains(Good.Furs)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[1]++;
                creditCount += fursPrice;
            }
        } else if (e.getSource() == sFood) {
            if (player.getShip().getCargo().contains(Good.Food)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[2]++;
                creditCount += waterPrice;
            }
        } else if (e.getSource() == sOre) {
            if (player.getShip().getCargo().contains(Good.Ore)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[3]++;
                creditCount += orePrice;
            }
        } else if (e.getSource() == sGames) {
            if (player.getShip().getCargo().contains(Good.Games)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[4]++;
                creditCount += gamesPrice;
            }
        } else if (e.getSource() == sFirearms) {
            if (player.getShip().getCargo().contains(Good.Firearms)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[5]++;
                creditCount += firearmsPrice;
            }
        } else if (e.getSource() == sMachines) {
            if (player.getShip().getCargo().contains(Good.Machines)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[6]++;
                creditCount += machinesPrice;
            }
        } else if (e.getSource() == sMedicine) {
            if (player.getShip().getCargo().contains(Good.Medicine)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[7]++;
                creditCount += medicinePrice;
            }
        } else if (e.getSource() == sNarcotics) {
            if (player.getShip().getCargo().contains(Good.Narcotics)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[8]++;
                creditCount += narcoticsPrice;
            }
        } else if (e.getSource() == sRobots) {
            if (player.getShip().getCargo().contains(Good.Robots)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[9]++;
                creditCount += robotsPrice;
            }
        }
        currentCredits.setText("" + creditCount);
    }

    public void confirmTrade(ActionEvent e) {
        planet.changeWares(wares);
        player.setCredits(creditCount);
        player.getShip().setFuel(fuelCount);
        // Add the Cargo
        for (int i = 0; i < -wares[0]; i++) {
            player.getShip().addCargo(new Ware(Good.Water));
        }
        for (int i = 0; i < -wares[1]; i++) {
            player.getShip().addCargo(new Ware(Good.Furs));
        }
        for (int i = 0; i < -wares[2]; i++) {
            player.getShip().addCargo(new Ware(Good.Food));
        }
        for (int i = 0; i < -wares[3]; i++) {
            player.getShip().addCargo(new Ware(Good.Ore));
        }
        for (int i = 0; i < -wares[4]; i++) {
            player.getShip().addCargo(new Ware(Good.Games));
        }
        for (int i = 0; i < -wares[5]; i++) {
            player.getShip().addCargo(new Ware(Good.Firearms));
        }
        for (int i = 0; i < -wares[6]; i++) {
            player.getShip().addCargo(new Ware(Good.Machines));
        }
        for (int i = 0; i < -wares[7]; i++) {
            player.getShip().addCargo(new Ware(Good.Medicine));
        }
        for (int i = 0; i < -wares[8]; i++) {
            player.getShip().addCargo(new Ware(Good.Narcotics));
        }
        for (int i = 0; i < -wares[9]; i++) {
            player.getShip().addCargo(new Ware(Good.Robots));
        }

        // Remove the Cargo
        for (int i = 0; i < -wares[0]; i++) {
            player.getShip().removeCargo(new Ware(Good.Water));
        }
        for (int i = 0; i < -wares[1]; i++) {
            player.getShip().removeCargo(new Ware(Good.Furs));
        }
        for (int i = 0; i < -wares[2]; i++) {
            player.getShip().removeCargo(new Ware(Good.Food));
        }
        for (int i = 0; i < -wares[3]; i++) {
            player.getShip().removeCargo(new Ware(Good.Ore));
        }
        for (int i = 0; i < -wares[4]; i++) {
            player.getShip().removeCargo(new Ware(Good.Games));
        }
        for (int i = 0; i < -wares[5]; i++) {
            player.getShip().removeCargo(new Ware(Good.Firearms));
        }
        for (int i = 0; i < -wares[6]; i++) {
            player.getShip().removeCargo(new Ware(Good.Machines));
        }
        for (int i = 0; i < -wares[7]; i++) {
            player.getShip().removeCargo(new Ware(Good.Medicine));
        }
        for (int i = 0; i < -wares[8]; i++) {
            player.getShip().removeCargo(new Ware(Good.Narcotics));
        }
        for (int i = 0; i < -wares[9]; i++) {
            player.getShip().removeCargo(new Ware(Good.Robots));
        }
    }

    public void addFuel(ActionEvent e) {
        if (fuelCount == player.getShip().getMaxFuel() || creditCount < fuelCost) {

            //TODO display message saying that they have hit limit on fuel
        }
        fuelCount++;
        creditCount -= fuelCost;
        fuelLeft.setText("" + fuelCount);
        currentCredits.setText("" + creditCount);
    }

    public void subtractFuel(ActionEvent e) {
        if (fuelCount == 0) {
            return;
            //TODO display message saying that they have hit limit on fuel
        }
        fuelCount--;
        creditCount += fuelCost;
        fuelLeft.setText("" + fuelCount);
        currentCredits.setText("" + creditCount);
    }

    public void cancelTrade(ActionEvent e) {
        HyenasLoader.getInstance().goToSystemScreen();
    }

}
