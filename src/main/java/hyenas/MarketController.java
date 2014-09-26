
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
        private int[] tempWare;
        
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
        
    }
    
    public MarketController(Planet planet) {
        this.planet = planet;
        wares = planet.getItems();
        freeCargo = Player.getInstance().getShip().getFreeCargo();
        creditCount = Player.getInstance().getCredits();
        tempWare = new int[10];
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
        if (e.getSource() == sWater) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Water))) {
                freeCargo++;
                wares[0]++;
                creditCount += waterPrice;
            }
        } else if (e.getSource() == sFurs) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Furs))) {
                freeCargo++;
                wares[1]++;
                creditCount += fursPrice;
            }
        } else if (e.getSource() == sFood) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Food))) {
                freeCargo++;
                wares[2]++;
                creditCount += waterPrice;
            }
        } else if (e.getSource() == sOre) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Ore))) {
                freeCargo++;
                wares[3]++;
                creditCount += orePrice;
            }
        } else if (e.getSource() == sGames) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Games))) {
                freeCargo++;
                wares[4]++;
                creditCount += gamesPrice;
            }
        } else if (e.getSource() == sFirearms) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Firearms))) {
                freeCargo++;
                wares[5]++;
                creditCount += firearmsPrice;
            }
        } else if (e.getSource() == sMachines) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Machines))) {
                freeCargo++;
                wares[6]++;
                creditCount += machinesPrice;
            }
        } else if (e.getSource() == sMedicine) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Medicine))) {
                freeCargo++;
                wares[7]++;
                creditCount += medicinePrice;
            }
        } else if (e.getSource() == sNarcotics) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Narcotics))) {
                freeCargo++;
                wares[8]++;
                creditCount += narcoticsPrice;
            }
        } else if (e.getSource() == sRobots) {
            if (Player.getInstance().getShip()
                .getCargo().contains(new Wares(Wares.Good.Robots))) {
                freeCargo++;
                wares[9]++;
                creditCount += robotsPrice;
            }
        }
    }
    
    public void confirmTrade(ActionEvent e) {
        planet.changeWares(wares);
        
        // Add the Cargo
        for (int i = 0; i < -wares[0]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Water);
        }
        for (int i = 0; i < -wares[1]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Furs);
        }
        for (int i = 0; i < -wares[2]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Food);
        }
        for (int i = 0; i < -wares[3]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Ore);
        }
        for (int i = 0; i < -wares[4]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Games);
        }
        for (int i = 0; i < -wares[5]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Firearms);
        }
        for (int i = 0; i < -wares[6]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Machines);
        }
        for (int i = 0; i < -wares[7]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Medicine);
        }
        for (int i = 0; i < -wares[8]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Narcotics);
        }
        for (int i = 0; i < -wares[9]; i++) {
            Player.getInstance().getShip().addCargo(Wares.Good.Robots);
        }
        
        // Remove the Cargo
        for (int i = 0; i < -wares[0]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Water);
        }
        for (int i = 0; i < -wares[1]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Furs);
        }
        for (int i = 0; i < -wares[2]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Food);
        }
        for (int i = 0; i < -wares[3]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Ore);
        }
        for (int i = 0; i < -wares[4]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Games);
        }
        for (int i = 0; i < -wares[5]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Firearms);
        }
        for (int i = 0; i < -wares[6]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Machines);
        }
        for (int i = 0; i < -wares[7]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Medicine);
        }
        for (int i = 0; i < -wares[8]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Narcotics);
        }
        for (int i = 0; i < -wares[9]; i++) {
            Player.getInstance().getShip().removeCargo(Wares.Good.Robots);
        }
    }
    
    public void cancelTrade(ActionEvent e) {
        
    }
    
}
